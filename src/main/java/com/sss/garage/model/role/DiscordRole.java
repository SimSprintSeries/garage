package com.sss.garage.model.role;

import org.springframework.security.core.GrantedAuthority;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class DiscordRole implements GrantedAuthority {

    @Id
    private String id;

    private String code;

    @Override
    public String getAuthority() {
        return getId();
    }

    public String getId() {
        return id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(final String code) {
        this.code = code;
    }

}
