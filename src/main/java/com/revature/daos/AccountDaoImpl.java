package com.revature.daos;

import com.revature.models.Account;
import com.revature.models.AccountType;
import com.revature.util.ConnectionUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccountDaoImpl implements AccountDao{


    @Override
    public boolean createAccount(Account account) {

        String sql = "insert into account (account_num, balance) values (?, ?)";

        try (Connection c = ConnectionUtil.getConnection();
             PreparedStatement ps = c.prepareStatement(sql);){

            ps.setInt(1, account.getAccountNum());
            ps.setFloat(2, account.getBalance());

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
    public List<Account> getAllAccounts() {
        String sql = "select * from account;";
        List<Account> accounts = new ArrayList<>();

        try (Connection c = ConnectionUtil.getConnection();
             Statement s = c.createStatement();) {
            ResultSet rs = s.executeQuery(sql);

            while (rs.next()) {
                Account account = new Account();

                int id = rs.getInt("id");
                account.setId(id);

                int typeOrdinal = rs.getInt("type_id");
                typeOrdinal = typeOrdinal - 1; // start index at position 1
                AccountType[] accountType = AccountType.values();
                account.setAccountTypeId(accountType[typeOrdinal]);

                int account_num = rs.getInt("account_num");

                float balance = rs.getFloat("balance");

                accounts.add(account);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Account getAccountByAccountNum(int accountNum) {

        String sql = "select * from account where account_num = ? ";
        try (Connection c = ConnectionUtil.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)){

            ps.setInt(1, accountNum);

            ResultSet rs = ps.executeQuery();

            if (rs.next()){
                Account account = new Account();

                int id = rs.getInt("id");
                account.setId(id);

                account.setAccountNum(accountNum);

                account.setAccountNum(rs.getInt("account_num"));

                account.setBalance(rs.getFloat("balance"));

                int typeOrdinal = rs.getInt("account_type_id") - 1;
                AccountType[] accountType = AccountType.values();
                account.setAccountTypeId(accountType[typeOrdinal]);

                return account;
            }
        } catch (SQLException e){
            e.printStackTrace();
        }

        return null;
    }

    public Account getAccountById(int id) {

        String sql = "select * from user where id = ? ";
        try (Connection c = ConnectionUtil.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)){

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()){
                Account account = new Account();
                account.setAccountNum(id);

                account.setAccountNum(rs.getInt("account_num"));

                account.setBalance(rs.getFloat("balance"));

                int typeOrdinal = rs.getInt("type_id");
                AccountType[] accountType = AccountType.values();
                account.setAccountTypeId(accountType[typeOrdinal]);

                return account;
            }
        } catch (SQLException e){
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public boolean updateAccount(Account account) {

        String sql = "update account set id = ?, type_id = ?, account_num= ?, balance = ?, account_type_id = ?;";
        try (Connection c = ConnectionUtil.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, account.getId());
            ps.setInt(2, account.getAccountNum());
            ps.setFloat(3, account.getBalance());
            ps.setInt(4, account.getAccountTypeId().ordinal()+1);

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
    public boolean deleteAccount(int id) {

        String sql = "delete from account where id = ?; ";
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
