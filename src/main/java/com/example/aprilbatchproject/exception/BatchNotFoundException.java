package com.example.aprilbatchproject.exception;

public class BatchNotFoundException extends RuntimeException{
    public BatchNotFoundException(String message)
    {
        super(message);
    }
}
