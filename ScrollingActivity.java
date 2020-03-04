package com.example.plyermuse;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

public class ScrollingActivity extends AppCompatActivity {

    public static final String D1 = "name_button", D2="call_button" ;
    SQLiteDatabase sqLiteDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        startDB();

        Button greeting1 = findViewById(R.id.greet_button) ;
        greeting1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s1 ="Logging from button 1";
                logActivity(s1, 1);
            }
        });
        Button greeting2 = findViewById(R.id.greet_button2) ;
        greeting2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s2 ="Logging from button 2";
                logActivity(s2, 2);
            }
        });



    }

    public void startDB(){
        try {
            sqLiteDatabase = this.openOrCreateDatabase("mydb", MODE_PRIVATE, null);
            sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS logger (activity_name varchar,button_num int(3))");

        }
        catch (Exception e){}

    }

    public void logActivity(String incoming_s, int num){
//


        try {
            Intent greet_intent = new Intent(this, Greet.class);
//        greet_intent.putExtra(D1, incoming_s);
            startActivity(greet_intent);
            sqLiteDatabase.execSQL("INSERT INTO logger (activity_name, button_num) VALUES('"+incoming_s+"','"+num+"')");
            Toast.makeText(this, "Data entered successfully", Toast.LENGTH_SHORT).show();

        }
        catch (Exception e)
        {

        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_scrolling, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}




