package com.springpj.heroescontentcreator.security.authorization;

import java.util.Collection;

import com.springpj.heroescontentcreator.model.authorization.Authority;

public interface AuthorizationProvider {
	
	Collection<Authority> provideAuthorities();

}
