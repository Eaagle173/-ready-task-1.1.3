package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {
    }

    @Override
    public void createUsersTable() {
        String sql = "CREATE TABLE IF NOT EXISTS users (" +
                "id SERIAL PRIMARY KEY, " +
                "name VARCHAR(50), " +
                "lastNAme VARCHAR(50), " +
                "age SMALLINT)";
        try (Connection con = Util.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.executeUpdate();
            System.out.println("Таблица создана");
        } catch (SQLException e) {
            System.out.println("Ошибка создания таблицы: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void dropUsersTable() {
        String sql = "DROP TABLE IF EXISTS users";
        try (Connection con = Util.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.execute();
        } catch (SQLException e) {
            System.out.println("При удалении таблицы произошла ошибка: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        String sql = "INSERT INTO users (name, lastName, age) VALUES (?, ?, ?)";
        try (Connection con = Util.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, name);
            ps.setString(2, lastName);
            ps.setByte(3, age);
            if (ps.executeUpdate() > 0) {
                System.out.println("User с именем — " + name + " добавлен в базу данных");
            }
        } catch (SQLException e) {
            System.out.println("Ошибка создания юзера: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {
        String sql = "DELETE FROM users WHERE id = ?";
        try (Connection con = Util.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setLong(1, id);
            if (ps.executeUpdate() > 0) {
                System.out.println("Пользователь с id: \"" + id + "\" удалён.");
            } else {
                System.out.println("Пользователь с id: \"" + id + "\" не найден.");
            }
        } catch (SQLException e) {
            System.out.println("Ошибка при удалении пользователя: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<User>();
        String sql = "SELECT * FROM users";
        try (Connection con = Util.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                long id = rs.getLong("id");
                String name = rs.getString("name");
                String lastName = rs.getString("lastName");
                byte age = rs.getByte("age");
                User user = new User(name, lastName, age);
                user.setId(id);
                users.add(user);
            }
        } catch (SQLException e) {
            System.out.println("Ошибка при получении всех User`ов: " + e.getMessage());
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public void cleanUsersTable() {
        String sql = "DELETE FROM users";
        try (Connection con = Util.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.execute();
        } catch (SQLException e) {
            System.out.println("Ошибка при очистке таблицы пользователей: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
