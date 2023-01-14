package com.sss.garage.data.auth;

import java.util.Date;

public class JwtTokenData {
    private String token;
    private Date expiresAt;

    public String getToken() {
        return token;
    }

    public void setToken(final String token) {
        this.token = token;
    }

    public Date getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(final Date expiresAt) {
        this.expiresAt = expiresAt;
    }
}
