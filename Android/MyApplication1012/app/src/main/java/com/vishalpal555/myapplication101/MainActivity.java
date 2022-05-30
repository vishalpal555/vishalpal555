package com.vishalpal555.myapplication101;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    EditText userId;
    public static final String EXTRA_NAME = "com.vishalpal555.myapplication101.extra.NAME";
    private Button recyclerViewButton;
    private Button mediaButton;
    private Button buttonSharedPreferences;
    private Button buttonDataBase;
    private Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonSharedPreferences = findViewById(R.id.buttonSHaredPreferences);
        this.userId = findViewById(R.id.editTextTextEmailAddress);
        buttonDataBase = findViewById(R.id.buttonDataBase);

        recyclerViewButton = findViewById(R.id.recyclerViewButton);
        recyclerViewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(MainActivity.this, RecyclerViewAcitivity.class);
                startActivity(intent);
            }
        });

        mediaButton = findViewById(R.id.mediaButton);        mediaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(MainActivity.this, ActivityMedia.class);
                startActivity(intent);
            }
        });

        buttonSharedPreferences.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(MainActivity.this, SharedPreferencesActivity.class);
                startActivity(intent);
            }
        });

        buttonDataBase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(MainActivity.this, DataBaseActivity.class);
                startActivity(intent);
            }
        });
    }

    protected boolean emailVerify(String email){
        return Pattern.compile("[a-zA-Z0-9]@*\\.com").matcher(email).find();
    }
    public void signIn(View view){
        String userIdString = this.userId.getText().toString();
        if(!this.emailVerify(userIdString)) {
            intent = new Intent(this, MainActivity2.class);
            intent.putExtra(EXTRA_NAME, userIdString);
            startActivity(intent);
        }
        else{
            Toast.makeText(this, "Wrong User ID", Toast.LENGTH_SHORT).show();
        }
    }

}