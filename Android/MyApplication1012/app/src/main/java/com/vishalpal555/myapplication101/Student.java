package com.vishalpal555.myapplication101;

import androidx.annotation.NonNull;

import java.util.regex.Pattern;

public class Student {
    private final int id;
    private String name = new String();
    private String phoneNo = new String();
    private static int count = 0;

    public Student(int id, @NonNull String name, String phoneNo){
        this.setName(name);
        this.setPhoneNo(phoneNo);
        this.id = id;
    }

    public void setName(String name){
        if(name.trim().length() > 0){
            this.name = name;
        }
        else{
            System.out.println("Name cannot be empty");
        }
    }

    public void setPhoneNo(String phoneNo) {
        boolean ifPhoneNumber = Pattern.compile("[0-9]").matcher(phoneNo).find();
        if(ifPhoneNumber){
            if(phoneNo.length() == 10){
                this.phoneNo = phoneNo;
            }
            else {
                System.out.println("Length of phone number has to be 10 ");
            }
        }
        else{
            System.out.println("Phone number should contain only numerics");
        }
    }

    public String getId() {
        return String.valueOf(this.id);
    }

    public String getName() {
        return this.name;
    }

    public String getPhoneNo() {
        return this.phoneNo;
    }

    @Override
    public String toString(){
        return "ID : " +this.id +"\nName : " +this.name +"\nPhone number : " +this.phoneNo;
    }
}
