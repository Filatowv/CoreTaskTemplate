package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {


    private static final String CLEAN_TABLE_USERS = "TRUNCATE TABLE users";
    private static final String REQUESTING_ALL_USERS = "SELECT * FROM users";
    private static final String DELETE_BY_ID = "DELETE FROM users WHERE id = ";
    private static final String INSERT_USERS = "INSERT INTO users (name, lastName, age) VALUE (?,?,?)";
    private static final String DROP_TABLE = "DROP TABLE IF EXISTS users";
    private static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS users (" +
            " id INTEGER PRIMARY KEY NOT NULL" +
            " AUTO_INCREMENT," +
            " name VARCHAR(40), " +
            "lastName VARCHAR(40), " +
            "age INTEGER(3))";


    public UserDaoJDBCImpl() {}

    @Override
    public void dropUsersTable() {
        try (Connection connection = Util.getMySQLConnection();
            PreparedStatement statement = connection.prepareStatement(DROP_TABLE)) {
            statement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void createUsersTable() {
        try (Connection connection = Util.getMySQLConnection();
             PreparedStatement statement = connection.prepareStatement(CREATE_TABLE)) {
            statement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Connection connection = Util.getMySQLConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {
        try (Connection connection = Util.getMySQLConnection();
            PreparedStatement statement = connection.prepareStatement(DELETE_BY_ID + id)) {
            statement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        User user;

        try (Connection connection = Util.getMySQLConnection();
             PreparedStatement statement = connection.prepareStatement(REQUESTING_ALL_USERS)){
             ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                user = new User();
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));
                users.add(user);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return users;
    }


    @Override
    public void cleanUsersTable() {
        try (Connection connection = Util.getMySQLConnection();
             PreparedStatement statement = connection.prepareStatement(CLEAN_TABLE_USERS)) {
            statement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
