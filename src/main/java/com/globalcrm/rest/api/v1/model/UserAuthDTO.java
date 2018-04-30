package com.globalcrm.rest.api.v1.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.validation.constraints.NotNull;
import java.util.Collection;

/**
 * Created by Hugo Lezama on April - 2018
 */
@Data
public class UserAuthDTO implements UserDetails {
    private static final long serialVersionUID = 6713555369928042367L;
    private String password;
    @NotNull
    private String email;
    private String firstName;
    Collection<GrantedAuthority> authorities;
    private String jwtToken;
    private boolean enabled;

    @JsonIgnore
    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @JsonIgnore
    @Override
    public String getUsername() {
        return email;
    }

    @JsonIgnore
    @Override
    public String getPassword() {
        return password;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
