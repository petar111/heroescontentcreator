package com.springpj.heroescontentcreator.model.authorization;

public class Authority {

	private final Resource resource;
	private final AccessType accessType;
	
	public Authority(Resource resource, AccessType accessType) {
		super();
		this.resource = resource;
		this.accessType = accessType;
	}
	public Resource getResource() {
		return resource;
	}
	public AccessType getAccessType() {
		return accessType;
	}
	
}
