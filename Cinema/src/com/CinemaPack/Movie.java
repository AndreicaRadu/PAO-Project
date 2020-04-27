package com.CinemaPack;

public class Movie {
    private String name;
    private int hour;

    public Movie(String n, int h){
        this.hour = h;
        this.name = n;
    }
    //==========================================
    public int getHour() {
        return hour;
    }
    public String getName() {
        return name;
    }
//==========================================
}

