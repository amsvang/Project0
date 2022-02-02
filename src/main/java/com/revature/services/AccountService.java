package com.revature.services;

import com.revature.daos.AccountDao;
import com.revature.daos.AccountDaoImpl;
import com.revature.models.Account;

import java.util.List;

public class AccountService {

    private final AccountDao accountDao = new AccountDaoImpl();

    public boolean createAccount(Account account) {
        return accountDao.createAccount(account);
    }

    public List<Account> getAll() {
        return accountDao.getAllAccounts();
    }

    public Account getAccountById(int id) {
        return accountDao.getAccountById(id);
    }

    public boolean updateAccount(Account account) {
        return accountDao.updateAccount(account);
    }

    public boolean deleteAccount(int id) {
        return accountDao.deleteAccount(id);
    }

}
