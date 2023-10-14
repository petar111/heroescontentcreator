package com.springpj.heroescontentcreator.security.authorization;

import java.util.Collection;

import com.springpj.heroescontentcreator.model.authorization.Authority;

public class DatabaseAuthorizationProvider implements AuthorizationProvider {
	
	private final Collection<Authority> authorities;
	
	
	public DatabaseAuthorizationProvider(Collection<Authority> authorities) {
		super();
		this.authorities = authorities;
	}


	@Override
	public Collection<Authority> provideAuthorities() {
		return authorities;
	}

}
