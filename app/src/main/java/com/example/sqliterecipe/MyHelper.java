package com.example.sqliterecipe;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyHelper extends SQLiteOpenHelper {

    private static final String dbname = "mydb";
    private static final int version = 1;

    public MyHelper(Context context){
        super(context,dbname,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {           //gets called only the first time when app is installed...if database is already created,it doesn't call this function,if database doesn't exist,it will call this function to create it
        //creates table
        String sql = "CREATE TABLE PRODUCTS (_id INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT, DESCRIPTION TEXT,PRICE REAL)";
        sqLiteDatabase.execSQL(sql);


        //insert data into table
        insertData("Jam","Fruit Jam",300.50,sqLiteDatabase);
        insertData("Bread","Brown Bread",300.50,sqLiteDatabase);
        insertData("Corn Flakes","Flakes",300.50,sqLiteDatabase);

    }
    //function to create object of ContentValues which is basically like hashmap used to insert datas into table in a key-value fashion
    private void insertData(String name,String description,double price,SQLiteDatabase sqLiteDatabase){
        ContentValues contentValues = new ContentValues();
        contentValues.put("NAME",name);
        contentValues.put("DESCRIPTION",description);
        contentValues.put("PRICE",price);
        sqLiteDatabase.insert("PRODUCTS",null,contentValues);                     //inserts into table named PRODUCTS
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //we update int version whenever we update sqlite using this onUpgrade function
        //if version of their sqlite is different than the one in this code,only then onUpgrade function is called to update it
    }
}
