package com.example.sql_bai2.model;

import java.io.Serializable;

public class BaiHat implements Serializable {
    private int id;
    private String ten,tencasi,album,theloai;
    private boolean yeuthich;

    public BaiHat(int id, String ten, String tencasi, String album, String theloai, boolean yeuthich) {
        this.id = id;
        this.ten = ten;
        this.tencasi = tencasi;
        this.album = album;
        this.theloai = theloai;
        this.yeuthich = yeuthich;
    }

    public BaiHat() {
    }

    public BaiHat(String ten, String tencasi, String album, String theloai, boolean yeuthich) {
        this.ten = ten;
        this.tencasi = tencasi;
        this.album = album;
        this.theloai = theloai;
        this.yeuthich = yeuthich;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getTencasi() {
        return tencasi;
    }

    public void setTencasi(String tencasi) {
        this.tencasi = tencasi;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getTheloai() {
        return theloai;
    }

    public void setTheloai(String theloai) {
        this.theloai = theloai;
    }

    public boolean isYeuthich() {
        return yeuthich;
    }

    public void setYeuthich(boolean yeuthich) {
        this.yeuthich = yeuthich;
    }
}
