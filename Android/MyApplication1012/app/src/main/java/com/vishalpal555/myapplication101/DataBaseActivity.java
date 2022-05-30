package com.vishalpal555.myapplication101;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

public class DataBaseActivity extends AppCompatActivity {
    private Button buttonAddStudentDetails;
    private EditText editTextStudentName;
    private EditText editTextStudentPhoneNumber;
    private RecyclerView recyclerView;
    private String studentName = new String();
    private String studentPhoneNumber = new String();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_base);
        recyclerView = findViewById(R.id.RecyclerViewDB);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        StudentHandler studentHandler = new StudentHandler(this, "StudentsDataBase", null, 1);
        editTextStudentName = findViewById(R.id.editTextStudentName);
        editTextStudentPhoneNumber = findViewById(R.id.editTextStudentPhoneNumber);
        buttonAddStudentDetails = findViewById(R.id.buttonAddToDB);

        List<Student> students = studentHandler.getAllStudents();

        RecyclerViewCustomAdapterDataBase adapter = new RecyclerViewCustomAdapterDataBase(students);
        recyclerView.setAdapter(adapter);
        buttonAddStudentDetails.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onClick(View view) {
                int id = studentHandler.getLastId() + 1;
                studentName = editTextStudentName.getText().toString();
                studentPhoneNumber = editTextStudentPhoneNumber.getText().toString();
                Log.d("In DataBase Activity", "onClick: " +studentName +studentPhoneNumber +" " +String.valueOf(id));
                studentHandler.addStudent(new Student(id, studentName, studentPhoneNumber));
                students.add(new Student(id, studentName, studentPhoneNumber));
                recyclerView.scrollToPosition(id-1);
                adapter.notifyItemInserted(id);

            }
        });

        studentHandler.close();
    }
}