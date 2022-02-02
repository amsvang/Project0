package com.revature.services;


import com.revature.daos.TransactionDao;
import com.revature.daos.TransactionDaoImpl;
import com.revature.models.Transaction;
import com.revature.models.Users;

import java.util.List;

public class TransactionService {

    private final TransactionDao transactionDao = new TransactionDaoImpl();

    public boolean createTransaction(Transaction transaction) {

        return transactionDao.createTransaction(transaction);
    }

    public List<Transaction> getAllTransactions() {
        return transactionDao.getAllTransactions();
    }

    public Transaction getTransactionById(int id) {
        return transactionDao.getTransactionById(id);
    }

    public boolean updateTransaction(Transaction transaction) {
        return transactionDao.updateTransaction(transaction);
    }

    public boolean deleteTransaction(int id) {
        return transactionDao.deleteTransaction(id);
    }

    //public transferMoney (String first, String last, int fromAccountNum, int toAccountNum, float amountToTransfer, int emplId, int Status ) {


}
