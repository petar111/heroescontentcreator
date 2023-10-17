package com.springpj.heroescontentcreator.security.configuration;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer.AuthorizationManagerRequestMatcherRegistry;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import com.springpj.heroescontentcreator.model.authorization.AccessType;
import com.springpj.heroescontentcreator.model.authorization.Authority;
import com.springpj.heroescontentcreator.model.authorization.EmbededAccessType;
import com.springpj.heroescontentcreator.model.authorization.EmbededResource;
import com.springpj.heroescontentcreator.model.authorization.Resource;
import com.springpj.heroescontentcreator.repository.AccessTypeRepository;
import com.springpj.heroescontentcreator.repository.AuthorityRepository;
import com.springpj.heroescontentcreator.repository.ResourceRepository;
import com.springpj.heroescontentcreator.security.authorization.AuthorizationContext;
import com.springpj.heroescontentcreator.security.jwt.JwtAccessDeniedHandler;
import com.springpj.heroescontentcreator.security.jwt.JwtAuthenticationEntryPoint;
import com.springpj.heroescontentcreator.security.jwt.JwtAuthorizationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
	
	private static final Logger log = LoggerFactory.getLogger(SecurityConfiguration.class);

    private final UserDetailsService userDetailsService;
    private final JwtAuthorizationFilter jwtAuthorizationFilter;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    
    private final RequestMappingHandlerMapping requestMapping;
    
    private final AuthorityRepository authorityRepository;
	private final ResourceRepository resourceRepository;
	private final AccessTypeRepository accessTypeRepository;

    @Autowired
    public SecurityConfiguration(
            @Qualifier("userDetailsServiceImpl") UserDetailsService userDetailsService,
            JwtAuthorizationFilter jwtAuthorizationFilter,
            JwtAccessDeniedHandler jwtAccessDeniedHandler,
            JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint,
            @Qualifier("requestMappingHandlerMapping") RequestMappingHandlerMapping requestMapping,
            
            AuthorityRepository authorityRepository, 
            ResourceRepository resourceRepository,
			AccessTypeRepository accessTypeRepository
            ) {
    	
        this.userDetailsService = userDetailsService;
        this.jwtAuthorizationFilter = jwtAuthorizationFilter;
        this.jwtAccessDeniedHandler = jwtAccessDeniedHandler;
        this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
        this.requestMapping = requestMapping;
        
        this.authorityRepository = authorityRepository;
		this.resourceRepository = resourceRepository;
		this.accessTypeRepository = accessTypeRepository;
    }
    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    	
    	log.info("Retrieve authorization metadata from database - STARTED");
		Map<Long, Resource> resourcesMap = resourceRepository.findAll().stream()
				.collect(Collectors.toMap(Resource::getId, r -> r));
		Map<Long, AccessType> accessTypesMap = accessTypeRepository.findAll().stream()
				.collect(Collectors.toMap(AccessType::getId, a -> a));
		Map<Long, Authority> authoritiesMap = authorityRepository.findAll().stream()
				.collect(Collectors.toMap(Authority::getId, a -> a));
		log.info("Retrieve authorization metadata from database - FINISHED");

		log.info("Initialize authorization architecture - STARTED");
		AuthorizationContext.INSTANCE.initialzieAurhoritiesAchitecture(authoritiesMap, resourcesMap, accessTypesMap);
		log.info("Initialize authorization architecture - FINISHED");

    	Resource documentationResource = AuthorizationContext.INSTANCE
    			.findResourceByName(EmbededResource.DOCUMENTATION.name());
    	AccessType manageAllAceessType = AuthorizationContext.INSTANCE
    			.findAccessTypeByName(EmbededAccessType.MANAGE_ALL.name());
    	
    	String documentationAuthority = AuthorizationContext.INSTANCE
    			.provideAuthorityString(documentationResource.getName(), manageAllAceessType.getName());
    
    	Collection<Resource> resources = AuthorizationContext.INSTANCE.provideResources().values();
    	
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(
                		request -> {
                			request.requestMatchers("/auth/**").permitAll();
                			request.requestMatchers("/api-docs/**", 
                						"/api-docs", 
                						"/api-docs.json",
                						"/api-docs.yaml", 
                						"/swagger-ui.html").hasAuthority(documentationAuthority);
                			
                			resources.stream().forEach(r -> {
                				
                				String manageAllAuthority = AuthorizationContext.INSTANCE
                						.provideAuthorityString(r.getName(), manageAllAceessType.getName());
                				
                				request.requestMatchers("/" + r.getName().toLowerCase() + "/**").hasAuthority(manageAllAuthority);
                				
                				
                				AccessType readOnlyAccessType = AuthorizationContext.INSTANCE
                		    			.findAccessTypeByName(EmbededAccessType.READ_ONLY.name());
                				String readOnlyAuthority = AuthorizationContext.INSTANCE
                						.provideAuthorityString(r.getName(), readOnlyAccessType.getName());
                				
                				request.requestMatchers(HttpMethod.GET, "/" + r.getName().toLowerCase() + "/**").hasAuthority(readOnlyAuthority);
                			});
                			
                			request.anyRequest().authenticated();
                		})
                .sessionManagement(manager -> manager.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider()).addFilterBefore(
                		jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }


    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config)
            throws Exception {
        return config.getAuthenticationManager();
    }



    @Bean
    public PasswordEncoder passwordEncoder(){
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
    
    
}
