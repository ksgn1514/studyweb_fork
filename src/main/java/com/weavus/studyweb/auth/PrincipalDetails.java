package com.weavus.studyweb.auth;

import com.weavus.studyweb.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

public class PrincipalDetails implements UserDetails {

    private User loginUser;

    public PrincipalDetails(User user) {
        this.loginUser = user;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collection = new ArrayList<>();
        GrantedAuthority grant = new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return loginUser.getRole();
            }
        };

        collection.add(grant);

        return collection;
    }
    public User getUser(){
        return loginUser;
    }
    @Override
    public String getPassword() {
        return loginUser.getPassword();
    }

    @Override
    public String getUsername() {
        return loginUser.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
