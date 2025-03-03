package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Антон", "Кривович", (byte) 74);
        userService.saveUser("Клим", "Саныч", (byte) 56);
        userService.saveUser("Дмитрий", "Пучков", (byte) 62);
        userService.saveUser("Клавдия", "Мурыжкина", (byte) 14);
        System.out.println(userService.getAllUsers());
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
