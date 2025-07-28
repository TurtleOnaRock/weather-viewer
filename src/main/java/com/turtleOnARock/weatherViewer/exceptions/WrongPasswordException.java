package com.turtleOnARock.weatherViewer.exceptions;

public class WrongPasswordException extends RuntimeException{

    public WrongPasswordException(String message){
        super(message);
    }
}
