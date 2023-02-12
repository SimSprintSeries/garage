package com.sss.garage.model.elo.history;

import java.util.Date;

import com.sss.garage.model.elo.Elo;

import com.sss.garage.model.race.Race;

import jakarta.persistence.*;

@Entity
public class EloHistory extends Elo {

    public EloHistory(final Elo elo, final Date validUntil, final Race race) {
        super(elo);
        this.validUntil = validUntil;
        this.race = race;
    }

    public EloHistory() {
    }

    @ManyToOne
    private Race race;

    private Date validUntil;

    public Race getRace() {
        return race;
    }

    public void setRace(final Race race) {
        this.race = race;
    }

    public Date getValidUntil() {
        return validUntil;
    }

    public void setValidUntil(final Date validUntil) {
        this.validUntil = validUntil;
    }

}
