package com.springpj.heroescontentcreator.security.user;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.springpj.heroescontentcreator.model.user.User;

import java.util.Collection;
import java.util.HashSet;

public class UserPrincipal implements UserDetails {
    private static final long serialVersionUID = 4889114430550460076L;
	private final User user;

    public UserPrincipal(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return new HashSet<>();
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
