package com.revature.controllers;

import com.revature.models.Users;
import com.revature.services.UserService;
import io.javalin.http.Context;
import java.util.List;

public class UserController {

    private final UserService userService = new UserService();

    public void handleCreate(Context ctx){
        Users newUser = ctx.bodyAsClass(Users.class);
        boolean success = userService.createUser(newUser);

        if(success){
            ctx.status(201);
        } else {
            ctx.status(400);
        }
    }

    public void handleGetAll(Context ctx){
        List<Users> user = userService.getAll();
        ctx.json(user);
    }

    public void handleGetOne(Context ctx){
        String idParam = ctx.pathParam("id");
        int id = Integer.parseInt(idParam);
        Users user = userService.getById(id);
        // return a 404 if not found
        ctx.json(user);
    }

    public void handleUpdate(Context ctx){

        String idParam = ctx.pathParam("id");
        Users userToUpdate = ctx.bodyAsClass(Users.class);
        int idToUpdate = Integer.parseInt(idParam);
        userToUpdate.setId(idToUpdate);

        boolean success = userService.updateUser(userToUpdate);

        if(success){
            ctx.status(200);
        } else {
            ctx.status(400);
        }
    }

    public void handleDelete(Context ctx){
        ctx.status(405);
    }


}
