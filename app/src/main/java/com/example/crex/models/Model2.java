package com.example.crex.models;

public class Model2 {

    public String club_date,team1,team2,t1Flag,t2Flag,match_no, score1,score2, overs1, overs2, winner, winnerInfo;
    private int viewType;


    public Model2(int viewType, String team1, String team2, String t1Flag, String t2Flag, String match_no, String score1, String score2, String overs1, String overs2, String winner, String winnerInfo) {
        this.viewType = viewType;
        this.team1 = team1;
        this.team2 = team2;
        this.t1Flag = t1Flag;
        this.t2Flag = t2Flag;
        this.match_no = match_no;
        this.score1 = score1;
        this.score2 = score2;
        this.overs1 = overs1;
        this.overs2 = overs2;
        this.winner = winner;
        this.winnerInfo = winnerInfo;

    }

    public Model2(int viewType, String club_date) {
        this.viewType = viewType;
        this.club_date = club_date;
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

    public String getScore1() {
        return score1;
    }

    public void setScore1(String score1) {
        this.score1 = score1;
    }

    public String getScore2() {
        return score2;
    }

    public void setScore2(String score2) {
        this.score2 = score2;
    }

    public String getOvers1() {
        return overs1;
    }

    public void setOvers1(String overs1) {
        this.overs1 = overs1;
    }

    public String getOvers2() {
        return overs2;
    }

    public void setOvers2(String overs2) {
        this.overs2 = overs2;
    }

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }

    public String getWinnerInfo() {
        return winnerInfo;
    }

    public void setWinnerInfo(String winnerInfo) {
        this.winnerInfo = winnerInfo;
    }

    public int getViewType() {
        return viewType;
    }

    public void setViewType(int viewType) {
        this.viewType = viewType;
    }
}
