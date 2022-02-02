package com.revature.daos;

import com.revature.models.Employee;
import java.util.List;

public interface EmployeeDao {

        public boolean createEmployee(Employee employee);
        public List<Employee> getAllEmployees();
        public Employee getEmployeeById(int id);
        public boolean updateEmployee(Employee employee);
        public boolean deleteEmployee(int id);


}
