package com.login.demo;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

public class SecurityUser extends User implements Serializable {

    @Override
    public String toString() {
        return super.toString()+"SecurityUser{" +
                "userId=" + userId +
                ", lastPasswordResetDate=" + lastPasswordResetDate +
                '}';
    }

    private Integer userId;
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
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

}

