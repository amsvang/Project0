package com.revature.daos;

import com.revature.models.Users;
import java.util.List;

public interface UserDao {

    public boolean createUser(Users user);
    public List<Users> getAllUsers();
    public Users getUsersById(int id);
    public boolean updateUsers(Users user);
    public boolean deleteUser(int id);
    public Users getUserByUsernameAndPassword(String email, String password);


}
