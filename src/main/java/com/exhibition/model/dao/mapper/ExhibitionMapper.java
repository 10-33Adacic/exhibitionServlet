package com.exhibition.model.dao.mapper;

import com.exhibition.model.entity.Exhibition;
import com.exhibition.model.service.UserService;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ExhibitionMapper {

    private UserService userService = new UserService();

    public Exhibition extractFromResultSet(ResultSet rs) throws SQLException {
        return Exhibition.builder()
                .id(rs.getLong("id"))
                .name(rs.getString("name"))
                .showroom(rs.getString("showroom"))
                .description(rs.getString("description"))
                .author(userService.findById(rs.getLong("author")))
                .price(rs.getBigDecimal("price"))
                .date(Date.valueOf(rs.getString("date")))
                .build();
    }
}
