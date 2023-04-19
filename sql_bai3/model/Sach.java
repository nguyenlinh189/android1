package com.example.sql_bai3.model;

import java.io.Serializable;

public class Sach implements Serializable {
    private int id;
    private String ten,tacgia,ngayxb,nhaxb;
    private float gia;

    public Sach(int id, String ten, String tacgia, String ngayxb, String nhaxb, float gia) {
        this.id = id;
        this.ten = ten;
        this.tacgia = tacgia;
        this.ngayxb = ngayxb;
        this.nhaxb = nhaxb;
        this.gia = gia;
    }

    public Sach(String ten, String tacgia, String ngayxb, String nhaxb, float gia) {
        this.ten = ten;
        this.tacgia = tacgia;
        this.ngayxb = ngayxb;
        this.nhaxb = nhaxb;
        this.gia = gia;
    }

    public Sach() {
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

    public String getTacgia() {
        return tacgia;
    }

    public void setTacgia(String tacgia) {
        this.tacgia = tacgia;
    }

    public String getNgayxb() {
        return ngayxb;
    }

    public void setNgayxb(String ngayxb) {
        this.ngayxb = ngayxb;
    }

    public String getNhaxb() {
        return nhaxb;
    }

    public void setNhaxb(String nhaxb) {
        this.nhaxb = nhaxb;
    }

    public float getGia() {
        return gia;
    }

    public void setGia(float gia) {
        this.gia = gia;
    }
}
