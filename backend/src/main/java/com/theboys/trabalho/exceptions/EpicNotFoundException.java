package com.theboys.trabalho.exceptions;

public class EpicNotFoundException extends RuntimeException{
    public EpicNotFoundException(){
        super("Epic not found!");
    }
}
