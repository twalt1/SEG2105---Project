package com.example.seg2105;

public class Instructor extends UserAccount{
    public Instructor(String name, int age, String email, String phoneNo, String userName, String passWord, String acctType){
        super(name, age, email, phoneNo, userName, passWord, "Instructor");
    }
}
