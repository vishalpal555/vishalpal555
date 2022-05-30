package com.vishalpal555.myapplication101;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class RecyclerViewAcitivity extends AppCompatActivity {
    public Student s1 = new Student(1,"Vishal", "123456789");
    public Student s2 = new Student(2,"Swarnali", "123456789");
    public Student s3 = new Student(3, "Payel", "123456789");
    public Student s4 = new Student(4,"Prasenjit", "123456789");
    public Student s5 = new Student(5, "Sushanta", "123456789");
    public Student s6 = new Student(6, "Antanu", "123456789");
    public Student s7 = new Student(7, "Pritam", "123456789");
    public Student s8 = new Student(8, "Swarup", "123456789");
    public Student s9 = new Student(9, "Ashis", "123456789");
    public Student s10 = new Student(10,"Tamal", "123456789");
    public Student s11 = new Student(11, "Rahul", "123456789");
    public Student[] array = {s1, s2, s3, s4, s5, s6, s8, s9, s10, s11};
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_acitivity);
        RecyclerViewCustomAdapter adapter = new RecyclerViewCustomAdapter(array);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }
}