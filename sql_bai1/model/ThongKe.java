package com.example.sql_bai1.model;

import java.io.Serializable;

public class ThongKe implements Serializable {
    private String ten;
    private int sl;

    public ThongKe(String ten, int sl) {
        this.ten = ten;
        this.sl = sl;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public int getSl() {
        return sl;
    }

    public void setSl(int sl) {
        this.sl = sl;
    }
}
