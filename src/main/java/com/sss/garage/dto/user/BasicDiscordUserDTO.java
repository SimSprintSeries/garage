package com.sss.garage.dto.user;

public class BasicDiscordUserDTO {

    private String id;

    private String username;

    private String displayName;

    private String bilew;

    private String avatar;

    private Long driverId;

    public String getId() {
        return id;
    }

    public void setId(final String id) {
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

    public Long getDriverId() {
        return driverId;
    }

    public void setDriverId(final Long driverId) {
        this.driverId = driverId;
    }
}
