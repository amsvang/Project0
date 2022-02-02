package com.revature.daos;

import com.revature.models.Customer;

import java.util.List;

public interface CustomerDao {

    public boolean createCustomer(Customer customer);
    public List<Customer> getAllCustomers();
    public Customer getCustomerById(int id);
    public boolean updateCustomer(Customer customer);
    public boolean deleteCustomer(int id);

}
