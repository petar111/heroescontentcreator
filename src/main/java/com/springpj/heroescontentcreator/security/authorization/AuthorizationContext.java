package com.springpj.heroescontentcreator.security.authorization;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import com.springpj.heroescontentcreator.errorhandler.exception.AccessTypeNotFoundByNameException;
import com.springpj.heroescontentcreator.errorhandler.exception.ResourceNotFoundByNameException;
import com.springpj.heroescontentcreator.model.authorization.AccessType;
import com.springpj.heroescontentcreator.model.authorization.Authority;
import com.springpj.heroescontentcreator.model.authorization.Resource;

public enum AuthorizationContext {
	INSTANCE;
	
	private Map<Long, Authority> authorities;
	private Map<Long, Resource> resources;
	private Map<Long, AccessType> accessTypes;
	
	public void initialzieAurhoritiesAchitecture(
			Map<Long, Authority> authorities,
			Map<Long, Resource> resources,
			Map<Long, AccessType> accessTypes
			) {
		this.authorities = authorities;
		this.accessTypes = accessTypes;
		this.resources = resources;
	}
	
	public String provideAuthorityString(Long resourceId, Long accessTypeId) {
		return resources.get(resourceId).getName() + "_" + accessTypes.get(accessTypeId).getName();
	}
	
	public String provideAuthorityString(String resource, String accessType) {
		return provideAuthorityString(findResourceByName(resource).getId(), findAccessTypeByName(accessType).getId());
	}
	
	public Set<String> findAllAuthoritiesByResourceId(Long resourceId){
		return authorities.values().stream()
			.filter(a -> a.getResourceId().equals(resourceId))
			.map(a -> provideAuthorityString(a.getResourceId(), a.getAccessTypeId()))
			.collect(Collectors.toSet());
	}
	
	public Resource findResourceByName(String resourceName) {
		return resources.values().stream()
					.filter(r -> r.getName().equals(resourceName))
					.findAny()
					.orElseThrow(() -> new ResourceNotFoundByNameException(resourceName));
	}
	
	public Set<String> findAllAuthoritiesByAccessTypeId(Long id) {
		return authorities.values().stream()
				.filter(a -> a.getAccessTypeId().equals(id))
				.map(a -> provideAuthorityString(a.getResourceId(), a.getAccessTypeId()))
				.collect(Collectors.toSet());
	}
	
	public AccessType findAccessTypeByName(String accessTypeName) {
		return accessTypes.values().stream()
					.filter(a -> a.getName().equals(accessTypeName))
					.findAny()
					.orElseThrow(() -> new AccessTypeNotFoundByNameException(accessTypeName));
	}
	

	public Map<Long, Authority>  provideAuthorities() {
		return authorities;
	}

	public Map<Long, Resource> provideResources() {
		return resources;
	}

	public Map<Long, AccessType> provideAccessTypes() {
		return accessTypes;
	}

	
}
