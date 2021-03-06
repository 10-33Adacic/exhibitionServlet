package com.exhibition.model.dao;

import com.exhibition.model.entity.Exhibition;
import com.exhibition.model.entity.User;

import java.math.BigDecimal;
import java.sql.SQLException;

public interface UserDao extends GenericDao<User> {

    User findByUsernameAndPassword (String username, String password);

    User findByUsername (String username);

    void buyTicket(User user, Exhibition exhibition) throws SQLException;

    void updateBalance(User user, BigDecimal value);
}
