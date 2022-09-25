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

    public void createUsersTable() {

        String sql = "CREATE TABLE IF NOT EXISTS Users (id BIGINT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(255), lastName VARCHAR(255), age TINYINT)";
        try (Connection connection = new Util().getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.executeUpdate();
        } catch (RuntimeException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {

        String sql = "DROP TABLE IF EXISTS Users";
        try (Connection connection = new Util().getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.executeUpdate();
        } catch (RuntimeException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {

        String sql = "INSERT INTO Users (name, lastName, age) VALUES (?, ?, ?)";
        try (Connection connection = new Util().getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, name);
            statement.setString(2, lastName);
            statement.setByte(3, (age));
            statement.executeUpdate();
            System.out.println("User с именем – " + name + " добавлен в базу данных");

        } catch (RuntimeException | SQLException e) {
            e.printStackTrace();
        }

    }

    public void removeUserById(long id) {

        String sql = "DELETE FROM Users WHERE Users.id = ?";
        try (Connection connection = new Util().getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.executeUpdate();
        } catch (RuntimeException | SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {

        List<User> list = new ArrayList<>();
        String sql = "SELECT name, lastName, age FROM Users";
        try (Connection connection = new Util().getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                list.add(new User(resultSet.getString(1), resultSet.getString(2), resultSet.getByte(3)));
            }
        } catch (RuntimeException | SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void cleanUsersTable() {

        String sql = "DELETE FROM Users";
        try (Connection connection = new Util().getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.executeUpdate();
        } catch (RuntimeException | SQLException e) {
            e.printStackTrace();
        }
    }
}