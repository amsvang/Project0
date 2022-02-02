package com.revature.controllers;

import com.revature.models.Account;
import com.revature.services.AccountService;
import io.javalin.http.Context;

import java.util.List;

public class AccountController {

    private final AccountService accountService = new AccountService();

    public void handleCreate(Context ctx){
        Account newAccount = ctx.bodyAsClass(Account.class);
        boolean success = accountService.createAccount(newAccount);

        if(success){
            ctx.status(201);
        } else {
            ctx.status(400);
        }
    }

    public void handleGetAllAccounts(Context ctx){
        List<Account> accounts = accountService.getAll();
        ctx.json(accounts);
    }

    public void handleGetOne(Context ctx){
        String idParam = ctx.pathParam("id");
        int id = Integer.parseInt(idParam);
        Account account = accountService.getAccountById(id);
        // return a 404 if not found
        ctx.json(account);
    }

    public void handleUpdate(Context ctx){

        String idParam = ctx.pathParam("id");
        Account accountToUpdate = ctx.bodyAsClass(Account.class);
        int idToUpdate = Integer.parseInt(idParam);
        accountToUpdate.setId(idToUpdate);

        boolean success = accountService.updateAccount(accountToUpdate);

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
