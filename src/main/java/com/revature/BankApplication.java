package com.revature;
//import org.apache.logging.log4j;

import io.javalin.Javalin;


public class BankApplication {

    public static void main(String[] args) {

        JavalinApp app = new JavalinApp();
        app.start(8080);

    }




}
