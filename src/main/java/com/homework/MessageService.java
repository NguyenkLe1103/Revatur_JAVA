package com.homework;

public class MessageService {
    private String message;
public MessageService(String message){
    this.message = message;

}   
public void printMessage(){
    System.out.println("Your Message: " + message);
}
public void setMessage(String message){
    this.message = message;
}

}
