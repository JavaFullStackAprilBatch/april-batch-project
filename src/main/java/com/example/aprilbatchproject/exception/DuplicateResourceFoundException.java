package com.example.aprilbatchproject.exception;

public class DuplicateResourceFoundException extends  RuntimeException{

    public DuplicateResourceFoundException(String message) {
        super(message);
    }
}
