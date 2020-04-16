package com.exhibition.model.dao.mapper;

import com.exhibition.model.entity.Role;
import com.exhibition.model.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper {

    public User extractFromResultSet(ResultSet rs) throws SQLException {
        return User.builder()
                .id(rs.getLong("id"))
                .username(rs.getString("username"))
                .password(rs.getString("password"))
                .role(Role.valueOf(rs.getString("role")))
                .active(rs.getBoolean("active"))
                .accountMoney(rs.getLong("account_money"))
                .build();
    }
}
