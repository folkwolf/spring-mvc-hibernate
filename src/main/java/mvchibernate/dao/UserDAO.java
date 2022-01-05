package mvchibernate.dao;

import mvchibernate.model.User;

import java.util.List;

public interface UserDAO {

    public User getUser(int id);

    public List<User> getAllUsers();

    public void saveUser(User user);

    public void updateUser(User user);

    public void deleteUser(int id);

}
