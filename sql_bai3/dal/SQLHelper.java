package com.example.sql_bai3.dal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.sql_bai3.model.Sach;

import java.util.ArrayList;
import java.util.List;

public class SQLHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME="sachDB.db";
    private static int DATABASE_VERSION=1;
    public SQLHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    // tao bang
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql="create table sach (" +
                "id integer primary key autoincrement," +
                "ten text," +
                "tacgia text," +
                "ngayxb text," +
                "nhaxb text," +
                "gia float )";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    // get all order by date desc
    public List<Sach>getAll(){
        List<Sach>list=new ArrayList<>();
        SQLiteDatabase sqLiteDatabase=getReadableDatabase();
        String order="gia desc";// giam dan desc, tang dan asc
        Cursor rs=sqLiteDatabase.query("sach",null,null,null,null,null,order);
        while (rs!=null && rs.moveToNext()){
            Sach s=new Sach();
            s.setId(rs.getInt(0));
            s.setTen(rs.getString(1));
            s.setTacgia(rs.getString(2));
            s.setNgayxb(rs.getString(3));
            s.setNhaxb(rs.getString(4));
            s.setGia(rs.getFloat(5));
            System.out.println(s.getTen()+" "+s.getNhaxb()+" "+s.getTacgia()+" "+s.getGia()+" "+s.getNgayxb());
            list.add(s);
        }
        return list;
    }
    // them item vao database
    public long addItem(Sach s){

        ContentValues values=new ContentValues();
        values.put("ten",s.getTen());
        values.put("tacgia",s.getTacgia());
        values.put("ngayxb",s.getNgayxb());
        values.put("nhaxb",s.getNhaxb());
        values.put("gia",s.getGia());
        System.out.println(s.getTen()+" "+s.getNhaxb()+" "+s.getTacgia()+" "+s.getGia()+" "+s.getNgayxb());
        // put cac truong vao khong them id
        SQLiteDatabase sq=getWritableDatabase();
        return sq.insert("sach",null,values);
    }
    //cap nhat chi tiet
    public int update(Sach s){
        ContentValues values=new ContentValues();
        values.put("ten",s.getTen());
        values.put("tacgia",s.getTacgia());
        values.put("ngayxb",s.getNgayxb());
        values.put("nhaxb",s.getNhaxb());
        values.put("gia",s.getGia());
        // put cac truong vao nho put ca id
        String where="id=?";
        String[] args={s.getId()+""};
        SQLiteDatabase sq=getWritableDatabase();
        int kq=sq.update("sach",values,where,args);
        return kq;
    }
    // xoa chi tiet
    public int delete(int id){
        String where="id=?";
        String[] args={id+""};
        SQLiteDatabase sq=getWritableDatabase();
        return sq.delete("sach",where,args);
    }
    // tim kiem
    public List<Sach>earchByName(String key){
        List<Sach>list=new ArrayList<>();
        String where="ten like ?";// like ?, between ? and ?
        String[] args={"%"+key+"%"};// "%"+key+"%", neu la ngay thi trim()
        SQLiteDatabase sq=getReadableDatabase();
        Cursor rs=sq.query("sach",null,where,args,null,null,null);
        while (rs!=null && rs.moveToNext()){
            Sach s=new Sach();
            s.setId(rs.getInt(0));
            s.setTen(rs.getString(1));
            s.setTacgia(rs.getString(2));
            s.setNgayxb(rs.getString(3));
            s.setNhaxb(rs.getString(4));
            s.setGia(rs.getFloat(5));
            list.add(s);
        }
        return list;
    }
    public List<Sach>searchByGiafromto(float gia1, float gia2){
        List<Sach>list=new ArrayList<>();
        String where="gia between ? and ?";// like ?, between ? and ?
        String[] args={gia1+"",gia2+""};// "%"+key+"%", neu la ngay thi trim()
        String orderby="gia desc";
        SQLiteDatabase sq=getReadableDatabase();
        Cursor rs=sq.query("sach",null,where,args,null,null,orderby);
        while (rs!=null && rs.moveToNext()){
            Sach s=new Sach();
            s.setId(rs.getInt(0));
            s.setTen(rs.getString(1));
            s.setTacgia(rs.getString(2));
            s.setNgayxb(rs.getString(3));
            s.setNhaxb(rs.getString(4));
            s.setGia(rs.getFloat(5));
            list.add(s);
        }
        return list;
    }
    public Sach searchBynhaxb(String nhasx){
        String sql="select * from sach where nhaxb like ? order by gia desc limit 1";
        String[] args={nhasx};
        SQLiteDatabase sq=getReadableDatabase();
        Cursor rs=sq.rawQuery(sql,args);
        Sach s=null;
        if (rs!=null && rs.moveToNext()){
            s=new Sach();
            s.setId(rs.getInt(0));
            s.setTen(rs.getString(1));
            s.setTacgia(rs.getString(2));
            s.setNgayxb(rs.getString(3));
            s.setNhaxb(rs.getString(4));
            s.setGia(rs.getFloat(5));
        }
        return s;
    }
}
