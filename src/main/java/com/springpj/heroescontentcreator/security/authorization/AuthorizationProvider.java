package com.springpj.heroescontentcreator.security.authorization;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.springpj.heroescontentcreator.model.authorization.AccessType;
import com.springpj.heroescontentcreator.model.authorization.Authority;
import com.springpj.heroescontentcreator.model.authorization.Resource;

public interface AuthorizationProvider {
	
	List<Authority> provideAuthorities();
	Map<Long, Resource> provideResources();
	Map<Long, AccessType> provideAccessTypes();
	

}
