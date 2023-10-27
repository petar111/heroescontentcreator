package com.springpj.heroescontentcreator.persistence.configuration;

import java.util.Optional;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class AuditConfiguration {

	@Bean
	public AuditorAware<Long> auditorAware() {
		return new AuditorAware<Long>() {

			@Override
			public Optional<Long> getCurrentAuditor() {
				return Optional.of(1L);
			}
		};
	}

}
