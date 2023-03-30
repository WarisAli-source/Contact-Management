package com.contact.management.contactmanagement.exception;

public class NoSuchContactsExistException extends RuntimeException{
    private String message;

    public NoSuchContactsExistException() {}

    public NoSuchContactsExistException(String msg)
    {
        super(msg);
        this.message = msg;
    }
}
