package com.homework;

public class MessageService {
    private String message;
public MessageService(){}

public void setMessage(String message){
    this.message = message;
}   
public void printMessage(){
    System.out.println("Your Message: " + message);
}


}
