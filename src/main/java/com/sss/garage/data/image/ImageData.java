package com.sss.garage.data.image;

import com.sss.garage.data.league.LeagueData;

public class ImageData {
    private byte[] banner;

    private byte[] logo;

    private LeagueData league;

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

    public LeagueData getLeague() {
        return league;
    }

    public void setLeague(final LeagueData league) {
        this.league = league;
    }
}
