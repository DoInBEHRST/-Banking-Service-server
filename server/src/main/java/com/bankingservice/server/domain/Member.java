package com.bankingservice.server.domain;

import java.text.SimpleDateFormat;

public class Member {

    private long mbno;
    private String stcd;
    private String id;
    private String pw;
    private String regNum;
    private long prtMbno;
    private long pnt;
    private SimpleDateFormat lstMdfMbno;
    private SimpleDateFormat lstMdfDatetime;

    public long getMbno() {
        return mbno;
    }

    public void setMbno(long mbno) {
        this.mbno = mbno;
    }

    public String getStcd() {
        return stcd;
    }

    public void setStcd(String stcd) {
        this.stcd = stcd;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public String getRegNum() {
        return regNum;
    }

    public void setRegNum(String regNum) {
        this.regNum = regNum;
    }

    public long getPrtMbno() {
        return prtMbno;
    }

    public void setPrtMbno(long prtMbno) {
        this.prtMbno = prtMbno;
    }

    public long getPnt() {
        return pnt;
    }

    public void setPnt(long pnt) {
        this.pnt = pnt;
    }

    public SimpleDateFormat getLstMdfMbno() {
        return lstMdfMbno;
    }

    public void setLstMdfMbno(SimpleDateFormat lstMdfMbno) {
        this.lstMdfMbno = lstMdfMbno;
    }

    public SimpleDateFormat getLstMdfDatetime() {
        return lstMdfDatetime;
    }

    public void setLstMdfDatetime(SimpleDateFormat lstMdfDatetime) {
        this.lstMdfDatetime = lstMdfDatetime;
    }
}
