package com.mysportpesa.surebetsclient.model;

public class GlobalModel {
    public String match_id;
    public String country_name;
    public String league_name;
    public String match_date;
    public String match_time;
    public String match_hometeam_name;
    public String match_awayteam_name;
    public String match_status;
    public String match_hometeam_score;
    public String match_awayteam_score;

    public String prob_HW;
    public String prob_AW;
    public String prob_D;
    public String prob_HW_D;
    public String prob_AW_D;
    public String prob_HW_AW;
    public String prob_btts;
    public String prob_otts;
    public String prob_ov25; //should be checked
    public String prob_u25;

    public String prob_o_1;
    public String prob_u_1;
    public String prob_o_3;
    public String prob_u_3;


    public GlobalModel(){
    }

    public GlobalModel(String match_id, String country_name, String league_name, String match_date, String match_time, String match_hometeam_name, String match_awayteam_name, String match_status, String match_hometeam_score, String match_awayteam_score, String prob_HW, String prob_AW, String prob_D, String prob_HW_D, String prob_AW_D, String prob_HW_AW, String prob_btts, String prob_otts, String prob_ov25, String prob_u25, String prob_o_1, String prob_u_1, String prob_o_3, String prob_u_3) {
        this.match_id = match_id;
        this.country_name = country_name;
        this.league_name = league_name;
        this.match_date = match_date;
        this.match_time = match_time;
        this.match_hometeam_name = match_hometeam_name;
        this.match_awayteam_name = match_awayteam_name;
        this.match_status = match_status;
        this.match_hometeam_score = match_hometeam_score;
        this.match_awayteam_score = match_awayteam_score;
        this.prob_HW = prob_HW;
        this.prob_AW = prob_AW;
        this.prob_D = prob_D;
        this.prob_HW_D = prob_HW_D;
        this.prob_AW_D = prob_AW_D;
        this.prob_HW_AW = prob_HW_AW;
        this.prob_btts = prob_btts;
        this.prob_otts = prob_otts;
        this.prob_ov25 = prob_ov25;
        this.prob_u25 = prob_u25;
        this.prob_o_1 = prob_o_1;
        this.prob_u_1 = prob_u_1;
        this.prob_o_3 = prob_o_3;
        this.prob_u_3 = prob_u_3;
    }

    public String getMatch_id() {
        return match_id;
    }

    public void setMatch_id(String match_id) {
        this.match_id = match_id;
    }

    public String getCountry_name() {
        return country_name;
    }

    public void setCountry_name(String country_name) {
        this.country_name = country_name;
    }

    public String getLeague_name() {
        return league_name;
    }

    public void setLeague_name(String league_name) {
        this.league_name = league_name;
    }

    public String getMatch_date() {
        return match_date;
    }

    public void setMatch_date(String match_date) {
        this.match_date = match_date;
    }

    public String getMatch_time() {
        return match_time;
    }

    public void setMatch_time(String match_time) {
        this.match_time = match_time;
    }

    public String getMatch_hometeam_name() {
        return match_hometeam_name;
    }

    public void setMatch_hometeam_name(String match_hometeam_name) {
        this.match_hometeam_name = match_hometeam_name;
    }

    public String getMatch_awayteam_name() {
        return match_awayteam_name;
    }

    public void setMatch_awayteam_name(String match_awayteam_name) {
        this.match_awayteam_name = match_awayteam_name;
    }

    public String getMatch_status() {
        return match_status;
    }

    public void setMatch_status(String match_status) {
        this.match_status = match_status;
    }

    public String getMatch_hometeam_score() {
        return match_hometeam_score;
    }

    public void setMatch_hometeam_score(String match_hometeam_score) {
        this.match_hometeam_score = match_hometeam_score;
    }

    public String getMatch_awayteam_score() {
        return match_awayteam_score;
    }

    public void setMatch_awayteam_score(String match_awayteam_score) {
        this.match_awayteam_score = match_awayteam_score;
    }

    public String getProb_HW() {
        return prob_HW;
    }

    public void setProb_HW(String prob_HW) {
        this.prob_HW = prob_HW;
    }

    public String getProb_AW() {
        return prob_AW;
    }

    public void setProb_AW(String prob_AW) {
        this.prob_AW = prob_AW;
    }

    public String getProb_D() {
        return prob_D;
    }

    public void setProb_D(String prob_D) {
        this.prob_D = prob_D;
    }

    public String getProb_HW_D() {
        return prob_HW_D;
    }

    public void setProb_HW_D(String prob_HW_D) {
        this.prob_HW_D = prob_HW_D;
    }

    public String getProb_AW_D() {
        return prob_AW_D;
    }

    public void setProb_AW_D(String prob_AW_D) {
        this.prob_AW_D = prob_AW_D;
    }

    public String getProb_HW_AW() {
        return prob_HW_AW;
    }

    public void setProb_HW_AW(String prob_HW_AW) {
        this.prob_HW_AW = prob_HW_AW;
    }

    public String getProb_btts() {
        return prob_btts;
    }

    public void setProb_btts(String prob_btts) {
        this.prob_btts = prob_btts;
    }

    public String getProb_otts() {
        return prob_otts;
    }

    public void setProb_otts(String prob_otts) {
        this.prob_otts = prob_otts;
    }

    public String getProb_ov25() {
        return prob_ov25;
    }

    public void setProb_ov25(String prob_ov25) {
        this.prob_ov25 = prob_ov25;
    }

    public String getProb_u25() {
        return prob_u25;
    }

    public void setProb_u25(String prob_u25) {
        this.prob_u25 = prob_u25;
    }

    public String getProb_o_1() {
        return prob_o_1;
    }

    public void setProb_o_1(String prob_o_1) {
        this.prob_o_1 = prob_o_1;
    }

    public String getProb_u_1() {
        return prob_u_1;
    }

    public void setProb_u_1(String prob_u_1) {
        this.prob_u_1 = prob_u_1;
    }

    public String getProb_o_3() {
        return prob_o_3;
    }

    public void setProb_o_3(String prob_o_3) {
        this.prob_o_3 = prob_o_3;
    }

    public String getProb_u_3() {
        return prob_u_3;
    }

    public void setProb_u_3(String prob_u_3) {
        this.prob_u_3 = prob_u_3;
    }
}
