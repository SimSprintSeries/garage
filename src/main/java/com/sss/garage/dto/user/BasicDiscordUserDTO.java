package com.sss.garage.dto.user;

public class BasicDiscordUserDTO {

    private Long id;

    private String username;

    private String displayName;

    private String bilew;

    private String avatar;

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(final String username) {
        this.username = username;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(final String displayName) {
        this.displayName = displayName;
    }

    public String getBilew() {
        return bilew;
    }

    public void setBilew(final String bilew) {
        this.bilew = bilew;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(final String avatar) {
        this.avatar = avatar;
    }
}
