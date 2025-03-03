package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.util.List;

public class UserServiceImpl implements UserService {
    private final UserDao userDaoJBDCImpl = new UserDaoJDBCImpl();

    public void createUsersTable() {
        userDaoJBDCImpl.createUsersTable();
    }

    public void dropUsersTable() {
        userDaoJBDCImpl.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) {
        userDaoJBDCImpl.saveUser(name, lastName, age);
    }

    public void removeUserById(long id) {
        userDaoJBDCImpl.removeUserById(id);
    }

    public List<User> getAllUsers() {
        return userDaoJBDCImpl.getAllUsers();
    }

    public void cleanUsersTable() {
        userDaoJBDCImpl.cleanUsersTable();
    }
}
