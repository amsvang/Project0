package com.revature.daos;

import com.revature.models.Transaction;
import java.util.List;

public interface TransactionDao {

    public boolean createTransaction(Transaction transaction);
    public List<Transaction> getAllTransactions();
    public Transaction getTransactionById(int id);
    public boolean updateTransaction(Transaction transaction);
    public boolean deleteTransaction(int id);
}
