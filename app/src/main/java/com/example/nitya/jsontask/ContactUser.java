package com.example.nitya.jsontask;

public class ContactUser {
    String contact;
    String name;

    ContactUser(String contact,String name){
        this.contact=contact;
        this.name=name;
    }

    public String getContact() {
        return contact;
    }

    public String getName() {
        return name;
    }
}
