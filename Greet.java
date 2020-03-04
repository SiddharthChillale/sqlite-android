package com.example.plyermuse;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;

public class Greet extends AppCompatActivity {

    SQLiteDatabase sqLiteDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_greet);

        Intent catch_intent = getIntent();
        try {
            sqLiteDatabase = this.openOrCreateDatabase("mydb", MODE_PRIVATE, null);
            //sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS college (name varchar,age int(3))");
        } catch (Exception e) { }

//        String new_log = catch_intent.getStringExtra(ScrollingActivity.D1);
        TextView t1 = findViewById(R.id.wall);

        Cursor c = sqLiteDatabase.rawQuery("SELECT * FROM logger", null);
        int activityindex = c.getColumnIndex("activity_name");
        int buttonindex = c.getColumnIndex("button_num");
        try {


            c.moveToFirst();
            while (c != null) {
                t1.append("\nmsg:" + c.getString(activityindex));
                t1.append("\nbutton:" + Integer.toString(c.getInt(buttonindex)));
                c.moveToNext();
            }
        } catch (Exception e) {

        }


    }
}
