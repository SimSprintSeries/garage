package com.sss.garage.dto.image;

public class ImageDTO {

    private byte[] banner;

    private byte[] logo;

    public byte[] getBanner() {
        return banner;
    }

    public void setBanner(final byte[] banner) {
        this.banner = banner;
    }

    public byte[] getLogo() {
        return logo;
    }

    public void setLogo(final byte[] logo) {
        this.logo = logo;
    }
}
