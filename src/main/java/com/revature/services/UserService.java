package com.revature.services;

import com.revature.daos.UserDaoImpl;
import com.revature.daos.UserDao;
import com.revature.models.UserType;
import com.revature.models.Users;
import java.util.Date;
import java.util.List;

public class UserService {

    private final UserDao userDao = new UserDaoImpl();

    /*public boolean createUser(UserType type, String first, String last, String email, String password, String ssn, Date birthday) {
        email =  email.toLowerCase();
        Users u = new Users(type, first, last, email, password, ssn, birthday);
        return userDao.createUser(u);
    }*/

    public boolean createUser(Users user) {
        user.setEmail(user.getEmail().toLowerCase());
        return userDao.createUser(user);
    }

    public List<Users> getAll() {
        return userDao.getAllUsers();
    }

    public Users getById(int id) {
        return userDao.getUsersById(id);
    }

    public boolean updateUser(Users user) {
        return userDao.updateUsers(user);
    }

    public boolean deleteUser(int id) {
        return userDao.deleteUser(id);
    }

    public Users getUserByUsernameAndPassword(String username, String pass){
        return userDao.getUserByUsernameAndPassword(username,pass);
    }

}
