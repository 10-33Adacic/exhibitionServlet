package com.exhibition.model.service;

import com.exhibition.model.dao.UserDao;
import com.exhibition.model.dao.impl.UserJdbcDao;
import com.exhibition.model.entity.Role;
import com.exhibition.model.entity.User;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class UserServiceTest {

    private static final String USER = "root";

    private static final String PASSWORD = "root";

    private static final String URL = "jdbc:mysql://localhost:3306/exhibition_servlet_db?useTimezone=true&serverTimezone=UTC";

    private UserDao userDao;

    private Connection connection;

    @Before
    public void before() {
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            userDao = new UserJdbcDao(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @After
    public void after() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void findAllUsers() {
        final String username = "user0";
        final User user = User.builder()
                .username(username)
                .password("")
                .role(Role.USER)
                .active(true)
                .accountMoney(BigDecimal.ZERO)
                .build();

        userDao.add(user);

        List<User> list = userDao.findAll();

        userDao.delete(list.get(0).getId());

        Assert.assertNotNull(list);
    }

    @Test
    public void addUser() {
        final String u = "user1";
        final String p = "";
        final User user = User.builder()
                .username(u)
                .password(p)
                .role(Role.USER)
                .active(true)
                .accountMoney(BigDecimal.ZERO)
                .build();

        userDao.add(user);

        User expected = userDao.findByUsernameAndPassword(u, p);

        userDao.delete(expected.getId());

        Assert.assertNotNull(expected);
    }

    @Test
    public void findUser() {
        final String u = "user3";
        final String p = "";
        final User user = User.builder()
                .username(u)
                .password(p)
                .role(Role.USER)
                .active(true)
                .accountMoney(BigDecimal.ZERO)
                .build();

        userDao.add(user);

        User expected = userDao.findByUsernameAndPassword(u, p);

        userDao.delete(expected.getId());

        Assert.assertNotNull(expected);
    }
}