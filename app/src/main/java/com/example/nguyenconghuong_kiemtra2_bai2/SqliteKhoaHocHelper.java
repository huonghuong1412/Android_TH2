package com.example.nguyenconghuong_kiemtra2_bai2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class SqliteKhoaHocHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME="HocOnlineDB.db";
    private static final int DATABSE_VERSION=1;

    public SqliteKhoaHocHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABSE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql="CREATE TABLE `khoahoc`(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name TEXT," +
                "date TEXT, " +
                "major TEXT," +
                "active INTEGER)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    //add
    public void addKhoahoc(Khoahoc order){
        String sql="INSERT INTO `khoahoc`(name,date, major, active) VALUES(?,?,?,?)";
        String[] args={order.getName(),  order.getDate(),  order.getMajor(), Integer.toString(order.getActive())};
        SQLiteDatabase statement=getWritableDatabase();
        statement.execSQL(sql,args);
    }
    // get all
    public List<Khoahoc> getAll(){
        List<Khoahoc> list=new ArrayList<>();
        SQLiteDatabase statement=getReadableDatabase();
        Cursor rs=statement.query("`khoahoc`",null,
                null,null,null,
                null,null);
        while((rs!=null && rs.moveToNext())){
            int id=rs.getInt(0);
            String name=rs.getString(1);
            String date = rs.getString(2);
            String  major = rs.getString(3);
            int active = rs.getInt(4);
            list.add(new Khoahoc(id,name,date, major, active));
        }
        return list;
    }

    public List<Khoahoc> getByName(String name) {
        String sql = "name like ?";
        String[] args = { "%" + name + "%" };
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query("`khoahoc`", null, sql, args, null, null, null);
        List<Khoahoc> orders = new ArrayList<>();
        while(cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String itemName = cursor.getString(1);
            String date = cursor.getString(2);
            String  major = cursor.getString(3);
            int active = cursor.getInt(4);
            Khoahoc order = new Khoahoc(id, itemName, date, major, active);
            orders.add(order);
        }
        return orders;
    }

    //getBy id
    public Khoahoc getStudentById(int id){
        String whereClause="id =?";
        String[] whereArgs={String.valueOf(id)};
        SQLiteDatabase st=getReadableDatabase();
        Cursor rs=st.query("`khoahoc`",null,whereClause,
                whereArgs,null,null,null);
        if(rs.moveToNext()){
            String itemName = rs.getString(1);
            String date = rs.getString(2);
            String  major = rs.getString(3);
            int active = rs.getInt(4);
            return new Khoahoc(id, itemName, date, major, active);
        }
        return null;
    }

    //update
    public int update(Khoahoc order){
        ContentValues v=new ContentValues();
        v.put("name",order.getName());
        v.put("date",order.getDate());
        v.put("major",order.getMajor());
        v.put("active",order.getActive());

        String whereClause="id=?";
        String[] whereArgs={String.valueOf(order.getId())};
        SQLiteDatabase st=getWritableDatabase();
        getAll();
        return st.update("`khoahoc`",v,whereClause,whereArgs);
    }
    //delete
    public int delete(int id){
        String whereClause="id=?";
        String[] whereArgs={String.valueOf(id)};
        SQLiteDatabase st=getWritableDatabase();
        return st.delete("`khoahoc`",whereClause,whereArgs);
    }

}
