package com.example.sqliterecipe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MyHelper helper = new MyHelper(this);                                                //creating an object of Helper class which has all the necessary methods required for sqlite operations
       //SQLiteDatabase sqLiteDatabase = helper.getReadableDatabase();                                    //Giving readable access to database
        SQLiteDatabase sqLiteDatabase = helper.getWritableDatabase();                                     //changing access from readable to writable so that we can update it too

        /*ContentValues contentValues = new ContentValues();
        contentValues.put("PRICE",280.00);


      sqLiteDatabase.update("PRODUCTS",contentValues,"_id=?",new String[]{"1"});                   //we can change price on the basis of id

      sqLiteDatabase.update("PRODUCTS",contentValues,"NAME=? AND DESCRIPTION",new String[]{"Bread","Brown Bread"}); //or we can change price on the basis of name and description

       sqLiteDatabase.delete("PRODUCTS","_id=?",new String[]{"1"});                                  //delete the first line of table

        */
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT NAME,PRICE FROM PRODUCTS",new String[]{});    //returns an object to cursor variable

        //to show the row with name bread:-
        //Cursor cursor = sqLiteDatabase.rawQuery("SELECT NAME,PRICE FROM PRODUCTS WHERE NAME=?",new String[]{"Bread"});           //to show the row with name bread:-


        if(cursor!=null){
            cursor.moveToFirst();                                                                   //cursor points to first line of table
        }

        StringBuilder builder = new StringBuilder();                                                //stringbuilder to append data to which cursor is pointing in the table

        do{
            String name = cursor.getString(0);
            double price = cursor.getDouble(1);

            builder.append("NAME - "+name+" PRICE - "+price+"\n");
        }while(cursor.moveToNext());                                                                //will exit when cursor points to null,movetoNext() function moves the pointer to next Line

        TextView textView = findViewById(R.id.textData);
        textView.setText(builder.toString());                                                       //textview to display data from the table
    }
}