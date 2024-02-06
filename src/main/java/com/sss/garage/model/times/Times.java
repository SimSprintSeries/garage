package com.sss.garage.model.times;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "times")
public class Times implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    @Column(name = "nick")
    private String nick;

    @Column(name = "date")
    private String date;

    @Column(name = "time")
    private String time;

    public Times() {}

    public Times(String nick, String date, String time) {
        this.setNick(nick);
        this.setDate(date);
        this.setTime(time);
    }
    public Times(int id, String nick, String date, String time) {
        this.setId(id);
        this.setNick(nick);
        this.setDate(date);
        this.setTime(time);
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id=id;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
