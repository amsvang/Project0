package com.revature.daos;

import com.revature.models.*;
import com.revature.util.ConnectionUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TransactionDaoImpl implements TransactionDao {

    @Override
    public boolean createTransaction(Transaction transaction) {
        System.out.println("transation" + transaction);

        String sql = "insert into transaction (amount, from_account_id, to_account_id, transaction_type_id, Status_type_id, empl_id) values (?, ?, ?, ?, ?, ?)";

        try (Connection c = ConnectionUtil.getConnection();
             PreparedStatement ps = c.prepareStatement(sql);){

            ps.setFloat(1, transaction.getAmount());
            if (transaction.getFromAccountId() != 0)
                ps.setInt(2, transaction.getFromAccountId());
            else
                ps.setNull(2, 4);
            ps.setInt(3, transaction.getToAccountId());
            ps.setInt(4, transaction.getTransactionType().ordinal()+1);
            ps.setInt(5, transaction.getStatusType().ordinal()+1);

            if (transaction.getEmplId() != 0)
                ps.setInt(6, transaction.getEmplId());
            else
                ps.setNull(6, 4);

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
    public List<Transaction> getAllTransactions() {

        String sql = "select * from transaction;";
        List<Transaction> transactions = new ArrayList<>();

        try (Connection c = ConnectionUtil.getConnection();
             Statement s = c.createStatement();) {
            ResultSet rs = s.executeQuery(sql);

            while (rs.next()) {
                Transaction transaction = new Transaction();

                int id = rs.getInt("id");
                transaction.setId(id);

                float amount = rs.getFloat("amount");
                transaction.setAmount(amount);

                int fromAccount = rs.getInt("from_account_id");
                transaction.setFromAccountId(fromAccount);

                int toAccount = rs.getInt("to_account_id");
                transaction.setToAccountId(toAccount);

                int emplId = rs.getInt("empl_id");
                transaction.setEmplId(emplId);

                int typeOrdinal = rs.getInt("transaction_type_id");
                typeOrdinal = typeOrdinal - 1; //start index at position 1
                TransactionType[] transactionType = TransactionType.values();
                transaction.setTransactionType(transactionType[typeOrdinal]);

                int typeOrdinal2 = rs.getInt("status_type_id");
                typeOrdinal2 = typeOrdinal2 - 1; //start index at position 1
                StatusType[] statusType = StatusType.values();
                transaction.setStatusType(statusType[typeOrdinal2]);

                transactions.add(transaction);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return transactions;
    }

    @Override
    public Transaction getTransactionById(int id) {

        String sql = "select * from transaction where from_account = ? or to_account = ?; ";
        try (Connection c = ConnectionUtil.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)){

            ps.setInt(1, id);
            ps.setInt(2, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()){
                Transaction transaction = new Transaction();

                transaction.setFromAccountId(rs.getInt("from_account_id"));
                transaction.setToAccountId(rs.getInt("to_account_id"));
                transaction.setAmount(rs.getInt("amount"));

                int typeOrdinal = rs.getInt("transaction_type_id");
                typeOrdinal = typeOrdinal - 1; //start index at position 1
                TransactionType[] transactionType = TransactionType.values();
                transaction.setTransactionType(transactionType[typeOrdinal]);

                int typeOrdinal2 = rs.getInt("status_type_id");
                typeOrdinal2 = typeOrdinal2 - 1; //start index at position 1
                StatusType[] statusType = StatusType.values();
                transaction.setStatusType(statusType[typeOrdinal2]);

                return transaction;
            }
        } catch (SQLException e){
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public boolean updateTransaction(Transaction transaction) {

        String sql = "update transaction set id = ?, transaction_type_id = ?, status_type_id = ?, amount = ?, from_account = ?, to_account = ?, empl_id = ?;";
        try (Connection c = ConnectionUtil.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, transaction.getId());
            ps.setInt(2, transaction.getTransactionType().ordinal()+1);
            ps.setInt(3, transaction.getStatusType().ordinal()+1);
            ps.setFloat(4, transaction.getAmount());
            ps.setInt(5, transaction.getFromAccountId());
            ps.setInt(6, transaction.getToAccountId());
            ps.setInt(7, transaction.getEmplId());

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
    public boolean deleteTransaction(int id) {

        String sql = "delete from transaction where id = ?; ";
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
