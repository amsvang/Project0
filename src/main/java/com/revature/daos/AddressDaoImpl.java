package com.revature.daos;

import com.revature.models.Address;
import com.revature.util.ConnectionUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AddressDaoImpl implements AddressDao {
    @Override
    public boolean createAddress(Address address) {
        String sql = "insert into address (user_id, address, city, state, zip, telephone) values (?, ?, ?, ?, ?, ?);";

        try (Connection c = ConnectionUtil.getConnection();
             PreparedStatement ps = c.prepareStatement(sql);){

            ps.setInt(1, address.getUserId());
            ps.setString(2, address.getAddress());
            ps.setString(3, address.getCity());
            ps.setString(4, address.getState());
            ps.setString(5, address.getZip());
            ps.setString(6, address.getTelephone());

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
    public List<Address> getAllAddresses() {

        String sql = "select * from address;";
        List<Address> addresses = new ArrayList<>();

        try (Connection c = ConnectionUtil.getConnection();
             Statement s = c.createStatement();) {
            ResultSet rs = s.executeQuery(sql);

            while (rs.next()) {
                Address address = new Address();

                int userId = rs.getInt("user_id");
                address.setUserId(userId);

                String custAddress = rs.getString("address");
                address.setAddress(custAddress);

                String city = rs.getString("city");
                address.setCity(city);

                String state = rs.getString("state");
                address.setState(state);

                String zip = rs.getString("zip");
                address.setZip(zip);

                addresses.add(address);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return addresses;

    }

    @Override
    public Address getAddressById(int id) {

        String sql = "select * from address where id = (?)";

        try (Connection c = ConnectionUtil.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Address address = new Address();
                address.setId(id);

                return address;
            }
        } catch (SQLException e){
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public boolean updateAddress(Address address) {

        String sql = "update address set user_id = ?;";

        try (Connection c = ConnectionUtil.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, address.getUserId());
            ps.setString(2, address.getAddress());
            ps.setString(3, address.getCity());
            ps.setString(4, address.getState());
            ps.setString(5, address.getZip());
            ps.setString(6, address.getTelephone());

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
    public boolean deleteAddress(int id) {

        String sql = "delete from address where id = ?; ";
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
