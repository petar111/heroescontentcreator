package com.springpj.heroescontentcreator.model.authorization;

import org.springframework.security.core.GrantedAuthority;

import com.springpj.heroescontentcreator.security.authorization.AuthorizationProvider;

public class DatabaseGrantedAuthority implements GrantedAuthority{
	
	private static final long serialVersionUID = 7841412166752461255L;
	
	private final String authority;
	
	public DatabaseGrantedAuthority(String authority) {
		super();
		this.authority = authority;
	}

	@Override
	public String getAuthority() {
	    	return authority;
	}

}
