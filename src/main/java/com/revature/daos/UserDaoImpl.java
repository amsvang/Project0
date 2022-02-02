package com.revature.daos;

import com.revature.models.Users;
import com.revature.models.UserType;
import com.revature.util.ConnectionUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class UserDaoImpl implements UserDao {

    @Override
    public boolean createUser(Users user) {
        String sql = "insert into users (type_id, first, last, email, username, password, ssn) values (?, ?, ?, ?, ?, ?, ?);";

        // TODO: NEED DATE
        try (Connection c = ConnectionUtil.getConnection();
             PreparedStatement ps = c.prepareStatement(sql);){

            ps.setInt(1, user.getType().ordinal()+1);
            ps.setString(2, user.getFirst());
            ps.setString(3, user.getLast());
            ps.setString(4, user.getEmail());
            ps.setString(5, user.getUsername());
            ps.setString(6, user.getPassword());
            ps.setString(7, user.getSsn());
            // ps.setDate(8, (Date) user.getBirthday()); // TODO: NEED DATE

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
    public List<Users> getAllUsers() {
        String sql = "select * from users;";
        List<Users> users = new ArrayList<>();

        try (Connection c = ConnectionUtil.getConnection();
             Statement s = c.createStatement();) {
            ResultSet rs = s.executeQuery(sql);

            while (rs.next()) {
                Users user = new Users();

                int id = rs.getInt("id");
                user.setId(id);

                int typeOrdinal = rs.getInt("type_id");
                typeOrdinal = typeOrdinal - 1; //start index at position 1
                UserType[] userTypes = UserType.values();
                user.setType(userTypes[typeOrdinal]);

                String username = rs.getString("username");
                user.setUsername(username);

                String first = rs.getString("first");
                user.setFirst(first);

                String last = rs.getString("last");
                user.setLast(last);

                String email = rs.getString("email");
                user.setEmail(email);

                Date birthday = rs.getDate("birthday");
                user.setBirthday(birthday);

                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public Users getUsersById(int id) {

        String sql = "select * from user where id = ? ";
        try (Connection c = ConnectionUtil.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)){

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()){
                Users user = new Users();
                user.setId(id);

                int typeOrdinal = rs.getInt("type_id");
                typeOrdinal = typeOrdinal - 1; // start index at position 1
                UserType[] userTypes = UserType.values();
                user.setType(userTypes[typeOrdinal]);

                user.setFirst(rs.getString("first"));
                user.setLast(rs.getString("last"));
                user.setEmail(rs.getString("email"));
                user.setSsn(rs.getString("ssn"));
                user.setUsername(rs.getString("username"));
                user.setBirthday(rs.getDate("birthday"));
                return user;
            }
        } catch (SQLException e){
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public boolean updateUsers(Users user) {
        String sql = "update customer set id = ?, type_id = ?, first = ?, last = ?, email = ?, username = ?, password = ?, ssn = ?, birthday = ?;";
        try (Connection c = ConnectionUtil.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, user.getId());
            ps.setInt(2, user.getType().ordinal()+1);
            ps.setString(3, user.getFirst());
            ps.setString(4, user.getLast());
            ps.setString(5, user.getEmail());
            ps.setString(6, user.getUsername());
            ps.setString(7, user.getPassword());
            ps.setString(8, user.getSsn());
            ps.setDate(9, (Date) user.getBirthday());

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
    public boolean deleteUser(int id) {
        String sql = "delete from users where id = ?; ";
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

    @Override
    public Users getUserByUsernameAndPassword(String username, String password) {
        String sql = "select * from users where username = ? and password = ?";
        try (Connection c = ConnectionUtil.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, username);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Users user = new Users();

                user.setId(rs.getInt("id"));

                int typeOrdinal = rs.getInt("type_id" );
                UserType[] types = UserType.values();
                user.setType(types[typeOrdinal -1]);

                user.setFirst(rs.getString("first"));

                user.setLast(rs.getString("last"));

                user.setUsername(rs.getString("username"));

                user.setEmail(rs.getString("email"));

                user.setPassword(rs.getString("password"));

                return user;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
