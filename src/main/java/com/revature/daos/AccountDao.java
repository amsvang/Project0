package com.revature.daos;

import com.revature.models.Account;
import java.util.List;

public interface AccountDao {

    public boolean createAccount(Account account);
    public List<Account> getAllAccounts();
    public Account getAccountByAccountNum(int accountNum);
    public Account getAccountById(int id);
    public boolean updateAccount(Account account);
    public boolean deleteAccount(int id);

}
