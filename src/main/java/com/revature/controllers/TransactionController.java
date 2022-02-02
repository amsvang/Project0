package com.revature.controllers;

import com.revature.models.Transaction;
import com.revature.models.Users;
import com.revature.services.TransactionService;
import io.javalin.http.Context;
import java.util.List;

public class TransactionController {

    private final TransactionService transactionService = new TransactionService();

    public void handleCreate(Context ctx){
        Transaction newTransaction = ctx.bodyAsClass(Transaction.class);
        boolean success = transactionService.createTransaction(newTransaction);

        if(success){
            ctx.status(201);
        } else {
            ctx.status(400);
        }
    }

    public void handleGetAllTransactions(Context ctx){
        List<Transaction> transactions = transactionService.getAllTransactions();
        ctx.json(transactions);
    }

    public void handleGetOne(Context ctx){
        String idParam = ctx.pathParam("id");
        int id = Integer.parseInt(idParam);
        Transaction transaction = transactionService.getTransactionById(id);
        // return a 404 if not found
        ctx.json(transaction);
    }

    public void handleUpdate(Context ctx){

        String idParam = ctx.pathParam("id");
        Transaction transactionToUpdate = ctx.bodyAsClass(Transaction.class);
        int idToUpdate = Integer.parseInt(idParam);
        transactionToUpdate.setId(idToUpdate);

        boolean success = transactionService.updateTransaction(transactionToUpdate);

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
