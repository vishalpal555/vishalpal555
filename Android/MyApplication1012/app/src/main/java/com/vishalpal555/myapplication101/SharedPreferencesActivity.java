package com.vishalpal555.myapplication101;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SharedPreferencesActivity extends AppCompatActivity {
    private EditText editTextPersonName;
    private EditText editTextPhoneNumber;
    private Button buttonSave;
    private TextView textViewShowData;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_preferences);
        editTextPersonName = findViewById(R.id.editTextPersonName);
        editTextPhoneNumber = findViewById(R.id.editTextPhoneNumber);
        buttonSave = findViewById(R.id.buttonSave);
        textViewShowData = findViewById(R.id.textViewShowData);

        SharedPreferences sP = getSharedPreferences("Shared Pref Student", MODE_PRIVATE);
        String name = sP.getString("name", "no value");
        String phoneNumber = sP.getString("phoneNumber", "no value");

        textViewShowData.setText("Name : " +name +"\nPhone Number : " +phoneNumber);

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                String name = editTextPersonName.getText().toString();
                String phoneNumber = editTextPhoneNumber.getText().toString();

                SharedPreferences.Editor ed = sP.edit();
                ed.putString("name", name);
                ed.putString("phoneNumber", phoneNumber);

                ed.apply();
                textViewShowData.setText("Name : " +name +"\nPhone Number : " +phoneNumber);
            }
        });
    }
}