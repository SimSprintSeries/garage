package com.sss.garage.model.image;

import com.sss.garage.model.league.League;
import jakarta.persistence.*;

@Entity
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private League league;

    @Lob
    @Column(length = 2147483647)
    private byte[] banner;

    @Lob
    @Column(length = 2147483647)
    private byte[] logo;

    public Image() {
    }

    public Image(League league, byte[] banner, byte[] logo) {
        this.league = league;
        this.banner = banner;
        this.logo = logo;
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public League getLeague() {
        return league;
    }

    public void setLeague(final League league) {
        this.league = league;
    }

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
