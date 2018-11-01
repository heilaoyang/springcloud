package com.login.demo;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.Set;

public class SecurityUser extends User implements Serializable {


    private Date lastPasswordResetDate;
    public SecurityUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }
    public Date getLastPasswordResetDate() {
        return lastPasswordResetDate;
    }

    public void setLastPasswordResetDate(Date lastPasswordResetDate) {
        this.lastPasswordResetDate = lastPasswordResetDate;
    }
    public void setAuthorities(Collection<GrantedAuthority> authoritiesset) {
        getAuthorities().add(authoritiesset.iterator().next());
    }

}

