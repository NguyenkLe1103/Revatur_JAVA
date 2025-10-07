package com.homework;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );

        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        MessageService myMsg = context.getBean("messageService", MessageService.class);
        myMsg.printMessage();
        
    }
}
