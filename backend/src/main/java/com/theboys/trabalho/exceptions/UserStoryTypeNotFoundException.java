package com.theboys.trabalho.exceptions;

public class UserStoryTypeNotFoundException extends RuntimeException{
    public UserStoryTypeNotFoundException(){
        super("User Story Type not found!");
    }
}
