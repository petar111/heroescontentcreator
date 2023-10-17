package com.springpj.heroescontentcreator.security.user;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.springpj.heroescontentcreator.model.authorization.DatabaseGrantedAuthority;
import com.springpj.heroescontentcreator.model.user.User;
import com.springpj.heroescontentcreator.security.authorization.AuthorizationContext;

import java.util.Collection;
import java.util.stream.Collectors;

public class UserPrincipal implements UserDetails {
    private static final long serialVersionUID = 4889114430550460076L;
	private final User user;

    public UserPrincipal(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
    	
        return user.getRole().getAuthorities()
        		.stream()
        			.map(a -> AuthorizationContext.INSTANCE.provideAuthorityString(a.getResourceId(), a.getAccessTypeId()))
        			.map(DatabaseGrantedAuthority::new)
        			.collect(Collectors.toSet());
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return !user.getAccountStatus().isExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return !user.getAccountStatus().isLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return !user.isCredentialsExpired();
    }

    @Override
    public boolean isEnabled() {
        return user.getAccountStatus().isActive();
    }    
}
