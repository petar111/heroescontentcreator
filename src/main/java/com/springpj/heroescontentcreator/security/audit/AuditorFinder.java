package com.springpj.heroescontentcreator.security.audit;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import com.springpj.heroescontentcreator.security.constants.SecurityConstants;



public class AuditorFinder implements AuditorAware<Long> {

	private static final Logger log = LoggerFactory.getLogger(AuditorFinder.class);

	@Override
	public Optional<Long> getCurrentAuditor() {

		return Optional.ofNullable(SecurityContextHolder.getContext())
				.map(SecurityContext::getAuthentication)
				.filter(Authentication::isAuthenticated)
				.map(Authentication::getPrincipal)
				.map(String.class::cast)
				.map(s -> s.split(SecurityConstants.JWT_SUBJECT_DELIMITER)[1])
				.map(Long::valueOf);
	}
}
