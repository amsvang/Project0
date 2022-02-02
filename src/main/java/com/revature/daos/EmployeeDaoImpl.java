package com.revature.daos;

import com.revature.models.*;
import com.revature.util.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDaoImpl implements EmployeeDao {


    @Override
    public boolean createEmployee(Employee employee) {

        String sql = "insert into employee (user_id, title_id, empl_num) values (?, ?, ?)";

        try (Connection c = ConnectionUtil.getConnection();
             PreparedStatement ps = c.prepareStatement(sql);) {

            ps.setInt(1, employee.getUserId());
            ps.setInt(2, employee.getTitle().ordinal()+1);
            ps.setInt(3, employee.getEmplNum());

            int rowsAffected = ps.executeUpdate();

            if (rowsAffected == 1) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;

        }

        @Override
        public List<Employee> getAllEmployees () {

            String sql = "select * from employee;";
            List<Employee> employees = new ArrayList<>();

            try (Connection c = ConnectionUtil.getConnection();
                 Statement s = c.createStatement();) {
                ResultSet rs = s.executeQuery(sql);

                while (rs.next()) {
                    Employee employee = new Employee();

                    int id = rs.getInt("id");
                    employee.setId(id);

                    int userId = rs.getInt("user_id");
                    employee.setUserId(userId);

                    int typeOrdinal = rs.getInt("title_id");
                    typeOrdinal = typeOrdinal - 1; // start index at position 1
                    Title[] title = Title.values();
                    employee.setTitle(title[typeOrdinal]);

                    int emplNum = rs.getInt("empl_num");
                    employee.setEmplNum(emplNum);

                    employees.add(employee);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            return employees;
        }

        @Override
        public Employee getEmployeeById ( int id){

            String sql = "select * from employee where id = ? ";
            try (Connection c = ConnectionUtil.getConnection();
                 PreparedStatement ps = c.prepareStatement(sql)){

                ps.setInt(1, id);

                ResultSet rs = ps.executeQuery();

                if (rs.next()){
                    Employee employee = new Employee();

                    employee.setId(id);

                    employee.setUserId(rs.getInt("user_id"));

                    int typeOrdinal = rs.getInt("title_id");
                    typeOrdinal = typeOrdinal - 1; // start index at position 1
                    Title[] title = Title.values();
                    employee.setTitle(title[typeOrdinal]);

                    employee.setEmplNum(rs.getInt("empl_num"));

                    return employee;
                }
            } catch (SQLException e){
                e.printStackTrace();
            }

        return null;
        }

        @Override
        public boolean updateEmployee (Employee employee){

            String sql = "update employee set id = ?, user_id = ?, title_id = ?, empl_num = ?;";
            try (Connection c = ConnectionUtil.getConnection();
                 PreparedStatement ps = c.prepareStatement(sql)) {

                ps.setInt(1, employee.getId());
                ps.setInt(2, employee.getUserId());
                ps.setFloat(3, employee.getTitle().ordinal()+1);
                ps.setInt(4, employee.getEmplNum());

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
        public boolean deleteEmployee ( int id){

        String sql = "delete from employee where id = ?";
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

