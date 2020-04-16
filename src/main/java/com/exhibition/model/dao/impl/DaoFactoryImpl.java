package com.exhibition.model.dao.impl;

import com.exhibition.model.dao.DaoFactory;
import com.exhibition.model.dao.ExhibitionDao;
import com.exhibition.model.dao.UserDao;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class DaoFactoryImpl extends DaoFactory {

    private DataSource dataSource = ConnectionPoolHolder.getDataSource();

    @Override
    public UserDao createUserDao() {
        return new UserJdbcDao(getConnection());
    }

    @Override
    public ExhibitionDao createExhibitionDao() {
        return new ExhibitionJdbcDao(getConnection());
    }

    private Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
