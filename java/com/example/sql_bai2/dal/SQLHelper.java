package com.example.sql_bai2.dal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.sql_bai2.model.BaiHat;

import java.util.ArrayList;
import java.util.List;

public class SQLHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME="bh.db";
    private static int DATABASE_VERSION=1;
    public SQLHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    // tao bang
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql="create table baihat (" +
                "id integer primary key autoincrement," +
                "ten text," +
                "tencasi text," +
                "album text," +
                "theloai," +
                "yeuthich int)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    // get all order by date desc
    public List<BaiHat>getAll(){
        List<BaiHat>list=new ArrayList<>();
        SQLiteDatabase sqLiteDatabase=getReadableDatabase();
        Cursor rs=sqLiteDatabase.query("baihat",null,null,null,null,null,null);
        while (rs!=null && rs.moveToNext()){
            BaiHat bh=new BaiHat();
            bh.setId(rs.getInt(0));
            bh.setTen(rs.getString(1));
            bh.setTencasi(rs.getString(2));
            bh.setAlbum(rs.getString(3));
            bh.setTheloai(rs.getString(4));
            if(rs.getInt(5)==0)
                bh.setYeuthich(false);
            else
                bh.setYeuthich(true);
            list.add(bh);
        }
        return list;
    }
    // them item vao database
    public long addItem(BaiHat i){
        ContentValues values=new ContentValues();
        values.put("ten",i.getTen());
        values.put("tencasi",i.getTencasi());
        values.put("album",i.getAlbum());
        values.put("theloai",i.getTheloai());
        if(i.isYeuthich())
            values.put("yeuthich",1);
        else
            values.put("yeuthich",0);
        SQLiteDatabase sq=getWritableDatabase();
        return sq.insert("baihat",null,values);
    }
    //cap nhat chi tiet
    public int update(BaiHat i){
        ContentValues values=new ContentValues();
        // put cac truong vao nho put ca id
        values.put("ten",i.getTen());
        values.put("tencasi",i.getTencasi());
        values.put("album",i.getAlbum());
        values.put("theloai",i.getTheloai());
        if(i.isYeuthich())
            values.put("yeuthich",1);
        else
            values.put("yeuthich",0);
        String where="id=?";
        String[] args={i.getId()+""};
        SQLiteDatabase sq=getWritableDatabase();
        int kq=sq.update("baihat",values,where,args);
        return kq;
    }
    // xoa chi tiet
    public int delete(int id){
        String where="id=?";
        String[] args={id+""};
        SQLiteDatabase sq=getWritableDatabase();
        return sq.delete("baihat",where,args);
    }
    // tim kiem theo tung album
    public List<BaiHat>searchByAlbum(String key){
        List<BaiHat>list=new ArrayList<>();
        String where="album like ?";// like ?, between ? and ?
        String[] args={"%"+key+"%"};// "%"+key+"%", neu la ngay thi trim()
        SQLiteDatabase sq=getReadableDatabase();
        Cursor rs=sq.query("baihat",null,where,args,null,null,null);
        while (rs!=null && rs.moveToNext()){
            // them du lieu vao
            BaiHat bh=new BaiHat();
            bh.setId(rs.getInt(0));
            bh.setTen(rs.getString(1));
            bh.setTencasi(rs.getString(2));
            bh.setAlbum(rs.getString(3));
            bh.setTheloai(rs.getString(4));
            if(rs.getInt(5)==0)
                bh.setYeuthich(false);
            else
                bh.setYeuthich(true);
            list.add(bh);
        }
        return list;
    }
    // thong ke theo the loai
    public String thongke(){
        String sql="select t.theloai, count(t.theloai) from baihat t group by t.theloai order by count(t.theloai) desc";
        SQLiteDatabase sq=getWritableDatabase();
        Cursor rs=sq.rawQuery(sql,null);
        String kq="";
        while (rs!=null && rs.moveToNext()){
            kq+=rs.getString(0)+" "+rs.getInt(1)+"\n";
        }
        return kq;
    }
    public List<BaiHat>getbaiTop(){
        List<BaiHat>list=new ArrayList<>();
        String sql="select *,count(t.album) from baihat t group by t.album limit 1";
        SQLiteDatabase sq=getWritableDatabase();
        Cursor rs=sq.rawQuery(sql,null);
        while (rs!=null && rs.moveToNext()){
            // them du lieu vao
            BaiHat bh=new BaiHat();
            bh.setId(rs.getInt(0));
            bh.setTen(rs.getString(1));
            bh.setTencasi(rs.getString(2));
            bh.setAlbum(rs.getString(3));
            bh.setTheloai(rs.getString(4));
            if(rs.getInt(5)==0)
                bh.setYeuthich(false);
            else
                bh.setYeuthich(true);
            list.add(bh);
        }
        return list;
    }
}
