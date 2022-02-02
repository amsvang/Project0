package com.revature.services;

import com.revature.daos.AccountDaoImpl;
import com.revature.daos.CustomerDao;
import com.revature.daos.CustomerDaoImpl;
import com.revature.models.*;
import com.revature.daos.AccountDao;
import com.revature.daos.TransactionDao;
import com.revature.daos.TransactionDaoImpl;
import com.revature.daos.UserDao;

import java.util.List;

public class CustomerService {

    private final CustomerDao customerDao = new CustomerDaoImpl();
    private final AccountDao accountDao = new AccountDaoImpl();
    private final TransactionDao transactionDao = new TransactionDaoImpl();

    public boolean createCustomer(Customer customer) {
        return customerDao.createCustomer(customer);
    }

    public List<Customer> getAll() {
        return customerDao.getAllCustomers();
    }

    public Customer getById(int id) {
        return customerDao.getCustomerById(id);
    }

    public boolean updateCustomer(Customer customer) {
        return customerDao.updateCustomer(customer);
    }

    public boolean deleteCustomer(int id) {
        return customerDao.deleteCustomer(id);
    }


    public boolean requestMoneyTransfer(TransferInput input) {
        //check if they have the money, check if both account is valid/exists,  create a row in db

        //get account numbers passed in
        Account fromAcct = accountDao.getAccountByAccountNum(input.getFromAccountNum());
        Account toAcct = accountDao.getAccountByAccountNum(input.getToAccountNum());

        if (fromAcct == null || toAcct == null)
            return false;

        if (fromAcct.getBalance() > input.getAmount()) {
            Transaction transaction = new Transaction();

            transaction.setFromAccountId(fromAcct.getId());
            transaction.setToAccountId(toAcct.getId());
            transaction.setAmount(input.getAmount());
            transaction.setTransactionType(TransactionType.TRANSFER);
            transaction.setStatusType(StatusType.PENDING);

            return transactionDao.createTransaction(transaction);

        }
        return false;
    }
    //deposit money
    public boolean requestDeposit(DepositInput input) {

        //get toAccount number passed in
        Account toAcct = accountDao.getAccountByAccountNum(input.getToAccountNum());

        if (toAcct == null) {
            return false;
        }

        Transaction transaction = new Transaction();

        transaction.setToAccountId(toAcct.getId());
        transaction.setAmount(input.getAmount());
        transaction.setTransactionType(TransactionType.DEPOSIT);
        transaction.setStatusType(StatusType.PENDING);

        return transactionDao.createTransaction(transaction);

    }


}
