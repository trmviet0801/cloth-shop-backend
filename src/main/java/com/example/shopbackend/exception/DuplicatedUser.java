package com.example.shopbackend.exception;

public class DuplicatedUser extends Exception{
    public DuplicatedUser(String msg) {
        super(msg);
    }
}
