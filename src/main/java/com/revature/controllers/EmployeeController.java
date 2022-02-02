package com.revature.controllers;

import com.revature.models.Employee;
import com.revature.services.EmployeeService;
import io.javalin.http.Context;
import java.util.List;

public class EmployeeController {

    private final EmployeeService employeeService = new EmployeeService();

    public void handleCreate(Context ctx){
        Employee newEmployee = ctx.bodyAsClass(Employee.class);
        boolean success = employeeService.createEmployee(newEmployee);

        if(success){
            ctx.status(201);
        } else {
            ctx.status(400);
        }
    }

    public void handleGetAll(Context ctx){
        List<Employee> employees = employeeService.getAll();
        ctx.json(employees);
    }

    public void handleGetOne(Context ctx){
        String idParam = ctx.pathParam("id");
        int id = Integer.parseInt(idParam);
        Employee employee = employeeService.getById(id);
        // return a 404 if not found
        ctx.json(employee);
    }

    public void handleUpdate(Context ctx){

        String idParam = ctx.pathParam("id");
        Employee employeeToUpdate = ctx.bodyAsClass(Employee.class);
        int idToUpdate = Integer.parseInt(idParam);
        employeeToUpdate.setId(idToUpdate);

        boolean success = employeeService.updateEmployee(employeeToUpdate);

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
