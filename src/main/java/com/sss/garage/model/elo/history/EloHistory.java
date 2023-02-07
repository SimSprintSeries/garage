package com.sss.garage.model.elo.history;

import java.util.Date;

import com.sss.garage.model.elo.Elo;

import jakarta.persistence.Entity;

@Entity
public class EloHistory extends Elo {

    public EloHistory(final Elo elo, final Date validUntil) {
        super(elo);
        this.validUntil = validUntil;
    }

    public EloHistory() {
    }

    private Date validUntil;

    public Date getValidUntil() {
        return validUntil;
    }

    public void setValidUntil(final Date validUntil) {
        this.validUntil = validUntil;
    }
}
