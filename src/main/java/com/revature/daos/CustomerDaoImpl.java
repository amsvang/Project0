package com.revature.daos;

import com.revature.models.Customer;
import com.revature.util.ConnectionUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDaoImpl implements CustomerDao {

    @Override
    public boolean createCustomer(Customer customer) {
        String sql = "insert into customer (user_id) values (?)";

        try (Connection c = ConnectionUtil.getConnection();
             PreparedStatement ps = c.prepareStatement(sql);){

            ps.setInt(1, customer.getUserId());

            int rowsAffected = ps.executeUpdate();

            if(rowsAffected==1){
                return true;
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Customer> getAllCustomers() {

        String sql = "select * from customer;";
        List<Customer> customers = new ArrayList<>();

        try (Connection c = ConnectionUtil.getConnection();
             Statement s = c.createStatement();) {
            ResultSet rs = s.executeQuery(sql);

            while (rs.next()) {
                Customer customer = new Customer();

                int id = rs.getInt("id");
                customer.setId(id);

                int UserId = rs.getInt("user_id");
                customer.setUserId(UserId);

                customers.add(customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customers;
    }

    @Override
    public Customer getCustomerById(int id) {
        String sql = "select * from customer where id = (?)";

        try (Connection c = ConnectionUtil.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Customer customer = new Customer();
                customer.setId(id);

                return customer;
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean updateCustomer(Customer customer) {

        String sql = "update customer set user_id = ?;";

        try (Connection c = ConnectionUtil.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, customer.getUserId());

            int rowsAffected = ps.executeUpdate();

            if(rowsAffected==1){
                return true;
            }
        } catch (SQLException e){
            e.printStackTrace();
        }


        return false;
    }

    @Override
    public boolean deleteCustomer(int id) {
        String sql = "delete from customer where id = (?); ";
        try (Connection c = ConnectionUtil.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, id);

            int rowsAffected = ps.executeUpdate();

            if(rowsAffected==1){
                return true;
            }
        } catch (SQLException e){
            e.printStackTrace();
        }

        return false;
    }


}
