package com.revature.controllers;

import io.javalin.http.Context;

public class AppExceptionHandler {

    public void handleNumberFormatException(Exception e, Context ctx){
        ctx.status(400);
        ctx.result("The input you provided cannot be parsed to an int value");
    }
}
