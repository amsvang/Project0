package com.revature.services;

import com.revature.daos.EmployeeDao;
import com.revature.daos.EmployeeDaoImpl;
import com.revature.models.Employee;
import com.revature.models.Title;

import java.util.List;

public class EmployeeService {

    private final EmployeeDao employeeDao = new EmployeeDaoImpl();

    public boolean createEmployee(int id, int emplNum, int userId, Title title) {
        Employee employee = new Employee(id, emplNum, userId, title);
        return employeeDao.createEmployee(employee);
    }
    public boolean createEmployee(Employee employee) {
        return employeeDao.createEmployee(employee);
    }

    public List<Employee> getAll() {
        return employeeDao.getAllEmployees();
    }

    public Employee getById(int id) {
        return employeeDao.getEmployeeById(id);
    }

    public boolean updateEmployee(Employee employee) {
        return employeeDao.updateEmployee(employee);
    }

    public boolean deleteEmployee(int id) {
        return employeeDao.deleteEmployee(id);
    }

}
