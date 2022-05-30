package com.vishalpal555.myapplication101;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class StudentHandler extends SQLiteOpenHelper {
    private final String tag = "Student Handler";
    public StudentHandler(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "CREATE TABLE student(id INTEGER PRIMARY KEY, name TEXT, phone_number TEXT)";
        sqLiteDatabase.execSQL(query);
        Log.d(tag, "onCreate: database created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String drop = "DROP TABLE IF EXISTS ";
        sqLiteDatabase.execSQL(drop, new String[]{"student"});

        Log.d(tag, "onUpgrade: database deleted");
        this.onCreate(sqLiteDatabase);
        Log.d(tag, "onUpgrade: database upgraded");
    }
    public void addStudent(Student student){
        Log.d(tag, "addStudent: Writing in data base....");
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("id", student.getId());
        values.put("name", student.getName());
        values.put("phone_number", student.getPhoneNo());
        long k = sqLiteDatabase.insert("student", null, values);
        Log.d(tag, "addStudent: Details of student added k=" +String.valueOf(k));
        sqLiteDatabase.close();
    }
    public void getStudent(int id){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query("student", new String[]{"id", "name", "phone_number"}, "id=?", new String[]{String.valueOf(id)}, null, null, null, null);
        if(cursor != null && cursor.moveToFirst()){
            Log.d(tag, "ID : " +cursor.getString(0));
            Log.d(tag, "Name : " +cursor.getString(1));
            Log.d(tag, "Phone Number : +91" +cursor.getString(2));
        }
        else{
            Log.d(tag, "getStudent(int id): Error occurred getting data");
        }
        sqLiteDatabase.close();
    }
    public List<Student> getAllStudents(){
        List<Student> students = new ArrayList<Student>();
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query("student", null, null, null, null, null, null, null);
        if(cursor != null){
            if(cursor.moveToFirst()){
                do{
                    students.add(new Student(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2)));
                }while (cursor.moveToNext());
                for(Student student:students)
                    Log.d(tag, "getAllStudents: Student [id= " +student.getId() +", name= " +student.getName() +", PhoneNumber= " +student.getPhoneNo() +"]");
            }
        }
        else{
            Log.d(tag, "getAllStudents: Cursor is null");
        }
        return students;
    }
    public void deleteStudentById(int id){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.delete("student", "id=?", new String[]{String.valueOf(id)});
        Log.d(tag, "deleteStudentById: Deleted id=" +String.valueOf(id));
        sqLiteDatabase.close();
    }
    public void deleteAll(){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.delete("student", null, null);
    }
    public int getLastId(){
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query("student", new String[]{"id"}, null, null,null, null, null);
        cursor.moveToLast();
        return cursor.getInt(0);
    }
}
