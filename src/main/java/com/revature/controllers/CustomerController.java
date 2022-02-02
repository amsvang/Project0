package com.revature.controllers;

import com.revature.models.Customer;
import com.revature.models.DepositInput;
import com.revature.models.WithdrawInput;
import com.revature.services.CustomerService;
import com.revature.models.TransferInput;
import io.javalin.http.Context;

import java.util.List;

public class CustomerController {

    private final CustomerService customerService = new CustomerService();

    public void handleCreate(Context ctx){
        Customer newCustomer = ctx.bodyAsClass(Customer.class);
        boolean success = customerService.createCustomer(newCustomer);

        if(success){
            ctx.status(201);
        } else {
            ctx.status(400);
        }
    }
    public void handleGetAll(Context ctx){
        List<Customer> customer = customerService.getAll();
        ctx.json(customer);
    }

    public void handleGetOne(Context ctx){
        String idParam = ctx.pathParam("id");
        int id = Integer.parseInt(idParam);
        Customer customer = customerService.getById(id);
        // return a 404 if not found
        ctx.json(customer);
    }

    public void handleUpdate(Context ctx){

        String idParam = ctx.pathParam("id");
        Customer customerToUpdate = ctx.bodyAsClass(Customer.class);
        int idToUpdate = Integer.parseInt(idParam);
        customerToUpdate.setId(idToUpdate);

        boolean success = customerService.updateCustomer(customerToUpdate);

        if(success){
            ctx.status(200);
        } else {
            ctx.status(400);
        }
    }

    public void handleDelete(Context ctx){
        ctx.status(405);
    }

    public void handleTransfer(Context ctx) {
        TransferInput transferInput = ctx.bodyAsClass(TransferInput.class);

        boolean success = customerService.requestMoneyTransfer(transferInput);
        if(success){
            ctx.status(200);
        } else {
            ctx.status(400);
        }
    }

    public void handleDeposit(Context ctx) {
        DepositInput depositInput = ctx.bodyAsClass(DepositInput.class);

        boolean success = customerService.requestDeposit(depositInput);
        if(success){
            ctx.status(200);
        } else {
            ctx.status(400);
        }
    }
    public void handleWithdraw(Context ctx) {
        WithdrawInput withdrawInput = ctx.bodyAsClass(WithdrawInput.class);

        boolean success = customerService.requestWithdraw(withdrawInput);
        if(success){
            ctx.status(200);
        } else {
            ctx.status(400);
        }
    }


}



