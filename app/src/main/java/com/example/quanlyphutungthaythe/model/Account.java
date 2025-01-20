package com.example.quanlyphutungthaythe.model;

public class Account {
    String useName;
    String passWord;

    public Account(String useName, String passWord){
        this.useName = useName;
        this.passWord = passWord;
    }

    public Account() {
    }

    public String getUseName() {
        return useName;
    }

    public void setUseName(String useName) {
        this.useName = useName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

}

