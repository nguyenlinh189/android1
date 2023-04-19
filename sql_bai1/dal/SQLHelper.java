package com.example.sql_bai1.dal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.sql_bai1.model.CongViec;
import com.example.sql_bai1.model.ThongKe;

import java.util.ArrayList;
import java.util.List;

public class SQLHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME="cv.db";
    private static int DATABASE_VERSION=1;
    public SQLHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    // tao bang
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql="create table congviec (" +
                "id integer primary key autoincrement, " +
                "ten text," +
                "noidung text," +
                "ngay text," +
                "tinhtrang text," +
                "congtac int )";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    // get all order by date desc
    public List<CongViec>getAll(){
        List<CongViec>list=new ArrayList<>();
        SQLiteDatabase sqLiteDatabase=getReadableDatabase();
        String order="ngay desc";// giam dan desc, tang dan asc
        Cursor rs=sqLiteDatabase.query("congviec",null,null,null,null,null,order);
        while (rs!=null && rs.moveToNext()){
            CongViec cv=new CongViec();
            cv.setId(rs.getInt(0));
            cv.setTen(rs.getString(1));
            cv.setNoidung(rs.getString(2));
            cv.setNgay(rs.getString(3));
            cv.setTinhtrang(rs.getString(4));
            int tt=rs.getInt(5);
            if(tt==0)
                cv.setCongtac(false);
            else
                cv.setCongtac(true);
            list.add(cv);
        }
        return list;
    }
    // them item vao database
    public long addItem(CongViec cv){
        ContentValues values=new ContentValues();
        // put cac truong vao khong them id
        values.put("ten",cv.getTen());
        values.put("noidung",cv.getNoidung());
        values.put("ngay",cv.getNgay());
        values.put("tinhtrang",cv.getTinhtrang());
        if(cv.isCongtac())
            values.put("congtac",0);
        else
            values.put("congtac",1);
        SQLiteDatabase sq=getWritableDatabase();
        return sq.insert("congviec",null,values);
    }
    //cap nhat chi tiet
    public int update(CongViec cv){
        ContentValues values=new ContentValues();
        // put cac truong vao
        values.put("ten",cv.getTen());
        values.put("noidung",cv.getNoidung());
        values.put("ngay",cv.getNgay());
        values.put("tinhtrang",cv.getTinhtrang());
        if(cv.isCongtac())
            values.put("congtac",0);
        else
            values.put("congtac",1);
        String where="id=?";
        String[] args={cv.getId()+""};
        SQLiteDatabase sq=getWritableDatabase();
        int kq=sq.update("congviec",values,where,args);
        return kq;
    }
    // xoa chi tiet
    public int delete(int id){
        String where="id=?";
        String[] args={id+""};
        SQLiteDatabase sq=getWritableDatabase();
        return sq.delete("congviec",where,args);
    }
    // tim kiem theo key
    public List<CongViec>searchByTenOrNoiDung(String key){
        List<CongViec>list=new ArrayList<>();
        String where="ten like ? or noidung like ?";// like ?, between ? and ?
        String[] args={"%"+key+"%"};// "%"+key+"%", neu la ngay thi trim()
        String orderby="ngay desc";
        SQLiteDatabase sq=getReadableDatabase();
        Cursor rs=sq.query("congviec",null,where,args,null,null,orderby);
        while (rs!=null && rs.moveToNext()){
            CongViec cv=new CongViec();
            cv.setId(rs.getInt(0));
            cv.setTen(rs.getString(1));
            cv.setNoidung(rs.getString(2));
            cv.setNgay(rs.getString(3));
            cv.setTinhtrang(rs.getString(4));
            int tt=rs.getInt(5);
            if(tt==0)
                cv.setCongtac(false);
            else
                cv.setCongtac(true);
            list.add(cv);
        }
        return list;
    }
    // tim kiem theo tinhtrang
    public List<CongViec>searchByTinhTrang(String tinhtrang){
        List<CongViec>list=new ArrayList<>();
        String where="tinhtrang like ?";
        String[] args={"%"+tinhtrang+"%"};// "%"+key+"%", neu la ngay thi trim()
        String orderby="ngay desc";
        SQLiteDatabase sq=getReadableDatabase();
        Cursor rs=sq.query("congviec",null,where,args,null,null,orderby);
        while (rs!=null && rs.moveToNext()){
            CongViec cv=new CongViec();
            cv.setId(rs.getInt(0));
            cv.setTen(rs.getString(1));
            cv.setNoidung(rs.getString(2));
            cv.setNgay(rs.getString(3));
            cv.setTinhtrang(rs.getString(4));
            int tt=rs.getInt(5);
            if(tt==0)
                cv.setCongtac(false);
            else
                cv.setCongtac(true);
            list.add(cv);
        }
        return list;
    }
    public List<CongViec>searchByNgay(String ngay){
        List<CongViec>list=new ArrayList<>();
        String where="ngay like ?";
        String[] args={ngay};// "%"+key+"%", neu la ngay thi trim()
        SQLiteDatabase sq=getReadableDatabase();
        Cursor rs=sq.query("congviec",null,where,args,null,null,null);
        while (rs!=null && rs.moveToNext()){
            CongViec cv=new CongViec();
            cv.setId(rs.getInt(0));
            cv.setTen(rs.getString(1));
            cv.setNoidung(rs.getString(2));
            cv.setNgay(rs.getString(3));
            cv.setTinhtrang(rs.getString(4));
            int tt=rs.getInt(5);
            if(tt==0)
                cv.setCongtac(false);
            else
                cv.setCongtac(true);
            list.add(cv);
        }
        return list;
    }
    //thong ke
    public String thongke(){
        String kq="";
        String sql="select t.tinhtrang, count(t.tinhtrang) from congviec t group by t.tinhtrang order by count(t.tinhtrang)" ;
        SQLiteDatabase sq=getReadableDatabase();
        Cursor rs=sq.rawQuery(sql,null);
        while (rs!=null && rs.moveToNext()) {
            kq+=rs.getString(0)+": "+rs.getString(1)+"\n";
        }
        return kq;
    }
}
