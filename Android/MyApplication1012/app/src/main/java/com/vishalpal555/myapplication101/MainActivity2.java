package com.vishalpal555.myapplication101;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity2 extends AppCompatActivity {
    private String mainActivityLabel = new String();
    private Intent intent = new Intent();
    private String[] arr = {"Vishal", "Swarnali", "Payel", "Prasenjit", "Sushanta", "Antara", "Swarup", "Antanu", "Pritam", "Tushar"};
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        intent = getIntent();
        mainActivityLabel = intent.getStringExtra(MainActivity.EXTRA_NAME);
        getSupportActionBar().setTitle("Hii " +mainActivityLabel);
        listView = findViewById(R.id.list_view);

        // for built in listView
        // ArrayAdapter ad = new ArrayAdapter(this, android.R.layout.simple_list_item_1, arr);
        // listView.setAdapter(ad);

        CustomAdapter ad = new CustomAdapter(this, R.layout.vishal_custom_layout, arr);
        listView.setAdapter(ad);
    }
}