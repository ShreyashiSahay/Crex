package com.example.crex.models;

public class Model {

    public String club_date,team1,team2,t1Flag,t2Flag,match_no,time_stamp,match_date;
    public String odds_rate,odds_rate2,rate_team;

    private int viewType;
    private int odds;

    public int getOdds() {
        return odds;
    }

    public void setOdds(int odds) {
        this.odds = odds;
    }


    public int getViewType() {
        return viewType;
    }

    public void setViewType(int viewType) {
        this.viewType = viewType;
    }

    public Model(int viewType, int odds, String team1, String team2, String t1Flag, String t2Flag, String match_no, String club_date, String time_stamp, String odds_rate, String odds_rate2, String rate_team) {
        this.viewType = viewType;
        this.odds = odds;
        this.team1 = team1;
        this.team2 = team2;
        this.t1Flag = t1Flag;
        this.t2Flag = t2Flag;
        this.match_no = match_no;
        this.club_date = club_date;
        this.time_stamp = time_stamp;
        this.odds_rate = odds_rate;
        this.odds_rate2 = odds_rate2;
        this.rate_team = rate_team;
    }

    public Model(int viewType, int odds, String team1, String team2, String t1Flag, String t2Flag, String match_no, String club_date, String time_stamp) {
        this.viewType = viewType;
        this.odds = odds;
        this.team1 = team1;
        this.team2 = team2;
        this.t1Flag = t1Flag;
        this.t2Flag = t2Flag;
        this.match_no = match_no;
        this.club_date = club_date;
        this.time_stamp = time_stamp;
    }

    public Model(int viewType, String club_date) {
        this.viewType = viewType;
        this.club_date = club_date;
    }

    public String getOdds_rate() {
        return odds_rate;
    }

    public void setOdds_rate(String odds_rate) {
        this.odds_rate = odds_rate;
    }

    public String getOdds_rate2() {
        return odds_rate2;
    }

    public void setOdds_rate2(String odds_rate2) {
        this.odds_rate2 = odds_rate2;
    }

    public String getRate_team() {
        return rate_team;
    }

    public void setRate_team(String rate_team) {
        this.rate_team = rate_team;
    }


    public String getClub_date() {
        return club_date;
    }

    public void setClub_date(String club_date) {
        this.club_date = club_date;
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

    public String getT1Flag() {
        return t1Flag;
    }

    public void setT1Flag(String t1Flag) {
        this.t1Flag = t1Flag;
    }

    public String getT2Flag() {
        return t2Flag;
    }

    public void setT2Flag(String t2Flag) {
        this.t2Flag = t2Flag;
    }

    public String getMatch_no() {
        return match_no;
    }

    public void setMatch_no(String match_no) {
        this.match_no = match_no;
    }

    public String getMatch_date() {
        return match_date;
    }

    public void setMatch_date(String match_date) {
        this.match_date = match_date;
    }

    public String getTime_stamp() {
        return time_stamp;
    }

    public void setTime_stamp(String time_stamp) {
        this.time_stamp = time_stamp;
    }

}