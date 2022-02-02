package com.revature.controllers;

import com.revature.models.Users;
import com.revature.services.UserService;
import io.javalin.http.Context;
import io.javalin.http.ForbiddenResponse;
import io.javalin.http.UnauthorizedResponse;

public class AuthController {

    private final UserService userService = new UserService();

    public void authenticateLogin(Context ctx){
        // interpret request
        String username = ctx.formParam("username");
        String password = ctx.formParam("password");

        // fulfill the request
        Users user =  userService.getUserByUsernameAndPassword(username, password);

        // preparing response
        if(user==null){
            throw new UnauthorizedResponse("Incorrect username or password");
        } else {
            String simpleToken = user.getType()+"-TOKEN"; // Employee-token or Customer-token or Manager-token
            ctx.header("Authorization", simpleToken);
            ctx.status(200);
        }
    }

    public void authorizeEmployeeToken(Context ctx){
        String authHeader = ctx.header("Authorization");

        if(authHeader!=null){
            if(authHeader.equals("EMPLOYEE-TOKEN") || authHeader.equals("MANAGER-TOKEN")){
                return;
            } else if (authHeader.equals("CUSTOMER-TOKEN")){
                throw new ForbiddenResponse("Customers are unable to access this feature");
            }
        }
        throw new UnauthorizedResponse("please login and try again");
    }
    public void authorizeManageToken(Context ctx){
        String authHeader = ctx.header("Authorization");
        if(authHeader!=null){
            if(authHeader.equals("MANAGER-TOKEN")){
                return;
            } else {
                throw new ForbiddenResponse("You are unable to access this feature");
            }
        }
        throw new UnauthorizedResponse("please login and try again");
    }
    public void authorizeCustomerToken(Context ctx){
        String authHeader = ctx.header("Authorization");{

        if(authHeader!=null)
            if(authHeader.equals("CUSTOMER-TOKEN") || authHeader.equals("EMPLOYEE-TOKEN") || authHeader.equals("MANAGER-TOKEN")){
                return;
            } else {
                throw new ForbiddenResponse("You are unable to access this feature");
            }
        }
        throw new UnauthorizedResponse("please login and try again");
    }
}
