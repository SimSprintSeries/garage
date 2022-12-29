package com.sss.garage.model.role;

import java.util.Set;

import com.sss.garage.model.user.User;

import org.springframework.security.core.GrantedAuthority;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String code;

    /**
     * All roles that this role is a member of
     */
    @ManyToMany(mappedBy = "roles", fetch = FetchType.EAGER)
    private Set<Role> members;

    /**
     * All roles that are included in this role
     */
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles;

    @ManyToMany(mappedBy = "roles", fetch = FetchType.EAGER)
    private Set<User> users;

    @Override
    public String getAuthority() {
        return getCode();
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(final String code) {
        this.code = code;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(final Set<User> users) {
        this.users = users;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(final Set<Role> roles) {
        this.roles = roles;
    }

    public Set<Role> getMembers() {
        return members;
    }

    public void setMembers(final Set<Role> members) {
        this.members = members;
    }
}
