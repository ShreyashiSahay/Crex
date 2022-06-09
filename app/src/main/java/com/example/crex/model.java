package com.example.crex;
//Model class for multiple layout recycler view


import android.net.Uri;

public class model {
public String comdate;



    public static final int LayoutOne = 0;
    public static final int LayoutTwo = 1;
    public int viewType;

    public String place;

    public String team1;
    public String team2;
    public String t1im;  //This is the first image
    public String t2im;  //This is the second image
    public String date;
    public Long time;
    public String rate_team="";
    public String rate="";
    public String rate2="";


    //Creating constructor for first layout

    public model(){

    }

    public model(int viewType, String comdate){
        this.viewType= viewType;
        this.comdate=comdate;
    }

    public String getComdate() {
        return comdate;
    }

    public void setComdate(String comdate) {
        this.comdate = comdate;
    }


    public String getT1im() {
        return t1im;
    }

    public void setT1im(String t1im) {
        this.t1im = t1im;
    }

    public String getT2im() {
        return t2im;
    }

    public void setT2im(String t2im) {
        this.t2im = t2im;
    }

    //creating constructor for second layout
    public model(int viewType,String place,  String team1, String team2, String t1im, String t2im, String date, Long time, String rate_team, String rate, String rate2){
        this.viewType= viewType;
        this.place= place;
        this.team1= team1;
        this.team2= team2;
        this.t1im= t1im;
        this.t2im= t2im;
        this.date= date;
        this.time= time;
        this.rate_team= rate_team;
        this.rate= rate;
        this.rate2= rate2;


    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public int getViewType() {
        return viewType;
    }

    public void setViewType(int viewType) {
        this.viewType = viewType;
    }

    public String getRate_team() {
        return rate_team;
    }

    public void setRate_team(String rate_team) {
        this.rate_team = rate_team;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getRate2() {
        return rate2;
    }

    public void setRate2(String rate2) {
        this.rate2 = rate2;
    }

    public String getTeam1() {
        return team1;
    }



    public void setTeam1(String team1) {
        this.team1 = team1;
    }

    public String getTeam2() {
        return team2;
    }

    public void setTeam2(String team2) {
        this.team2 = team2;
    }











    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }
}
