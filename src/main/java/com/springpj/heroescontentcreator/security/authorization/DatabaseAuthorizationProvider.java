package com.springpj.heroescontentcreator.security.authorization;

import java.util.List;
import java.util.Map;

import com.springpj.heroescontentcreator.model.authorization.AccessType;
import com.springpj.heroescontentcreator.model.authorization.Authority;
import com.springpj.heroescontentcreator.model.authorization.Resource;

public class DatabaseAuthorizationProvider implements AuthorizationProvider {
	
	private final Map<Long, Authority> authorities;
	private final Map<Long, Resource> resources;
	private final Map<Long, AccessType> accessTypes;
	
	
	public DatabaseAuthorizationProvider(Map<Long, Authority> authorities,
			Map<Long, Resource> resources,
			Map<Long, AccessType> accessTypes) {
		this.authorities = authorities;
		this.accessTypes = accessTypes;
		this.resources = resources;
	}


	@Override
	public Map<Long, Authority>  provideAuthorities() {
		return authorities;
	}


	@Override
	public Map<Long, Resource> provideResources() {
		return resources;
	}


	@Override
	public Map<Long, AccessType> provideAccessTypes() {
		return accessTypes;
	}

}
