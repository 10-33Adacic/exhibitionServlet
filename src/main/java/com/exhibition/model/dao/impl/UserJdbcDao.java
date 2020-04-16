package com.exhibition.model.dao.impl;

import com.exhibition.model.dao.UserDao;
import com.exhibition.model.dao.mapper.UserMapper;
import com.exhibition.model.entity.Exhibition;
import com.exhibition.model.entity.User;

import java.sql.*;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class UserJdbcDao implements UserDao {

    private Connection connection;

    private UserMapper mapper;

    public UserJdbcDao(Connection connection) {
        this.connection = connection;
        mapper = new UserMapper();
    }

    @Override
    public void add(User entity) {
        try (PreparedStatement ps =
                     connection.prepareStatement(
                                    QUERY_USER_ADD)
        ) {
            ps.setString(1, entity.getUsername());
            ps.setString(2, entity.getPassword());
            ps.setString(3, entity.getRole().name());
            ps.setBoolean(4, entity.isActive());
            ps.setLong(5, entity.getAccountMoney());

            ps.executeUpdate();
        }
        catch (SQLException e) {
            throw new RuntimeException("Invalid input");
        }
    }

    @Override
    public User findByUsernameAndPassword (String username, String password) {
        try (PreparedStatement ps =
                     connection.prepareStatement(
                             QUERY_USER_FIND_BY_USERNAME)
        ) {
            ps.setString(1, username);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return mapper.extractFromResultSet(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public void buyTicket(User user, Exhibition exhibition) throws SQLException {
        try (PreparedStatement updateBalance =
                connection.prepareStatement(QUERY_USER_UPDATE_BALANCE);
             PreparedStatement addTicket =
                connection.prepareStatement(QUERY_TICKET_ADD)
        ) {
            connection.setAutoCommit(false);

            updateBalance.setLong(1, user.getAccountMoney() - exhibition.getPrice());
            updateBalance.setLong(2, user.getId());
            updateBalance.execute();

            addTicket.setLong(1, user.getId());
            addTicket.setLong(2, exhibition.getId());
            addTicket.execute();

            connection.commit();

            connection.setAutoCommit(true);
        } catch (SQLException e) {
            connection.rollback();

            connection.setAutoCommit(true);

            throw new RuntimeException("You cannot buy this ticket");
        }
    }

    @Override
    public void updateBalance(User user, Long value) {
        try (PreparedStatement ps =
                     connection.prepareStatement(
                             QUERY_USER_UPDATE_BALANCE)
        ) {
            ps.setLong(1, value);
            ps.setLong(2, user.getId());
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Cannot update balance");
        }
    }

    @Override
    public User findById(Long id) {
        try (PreparedStatement ps =
                     connection.prepareStatement(
                             QUERY_USER_FIND_BY_ID)
        ) {
            ps.setLong(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return mapper.extractFromResultSet(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public List<User> findAll() {
        List<User> resultList = new CopyOnWriteArrayList<>();

        try (Statement statement =
                     connection.createStatement()
        ) {
            ResultSet rs = statement.executeQuery(QUERY_USER_FIND_ALL);

            while (rs.next()) {
                User result = mapper.extractFromResultSet(rs);
                resultList.add(result);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return resultList;
    }

    @Override
    public void update(User entity) {
        try (PreparedStatement ps =
                     connection.prepareStatement(
                             QUERY_USER_UPDATE)
        ) {
            ps.setString(1, entity.getUsername());
            ps.setString(2, entity.getPassword());
            ps.setString(3, entity.getRole().name());
            ps.setBoolean(4, entity.isActive());
            ps.setLong(5, entity.getAccountMoney());
            ps.setLong(6, entity.getId());

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Long id) {
        try (PreparedStatement ps =
                     connection.prepareStatement(
                            QUERY_USER_DELETE_BY_ID)
        ) {
            ps.setLong(1, id);

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteEntity(User entity) {
        try (PreparedStatement ps =
                     connection.prepareStatement(
                             QUERY_USER_DELETE_BY_ID)
        ) {
            ps.setLong(1, entity.getId());

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static final String QUERY_USER_ADD =
            "INSERT INTO user (username , password , role, active, account_money) VALUES (? ,? ,?, ?, ?)";
    private static final String QUERY_USER_FIND_BY_USERNAME =
            "SELECT * FROM user WHERE username = ? AND password = ?";
    private static final String QUERY_USER_FIND_BY_ID =
            "SELECT * FROM user WHERE id = ?";
    private static final String QUERY_USER_FIND_ALL =
            "SELECT * FROM user";
    private static final String QUERY_USER_UPDATE =
            "UPDATE user SET username = ? , password = ?, role = ?, active = ?, account_money = ? WHERE id = ?";
    private static final String QUERY_USER_DELETE_BY_ID =
            "DELETE FROM user  WHERE id = ?";
    private static final String QUERY_USER_UPDATE_BALANCE =
            "UPDATE user SET account_money = ? WHERE id = ?";
    private static final String QUERY_TICKET_ADD =
            "INSERT INTO ticket (user_id, ticket_id) VALUES (?, ?)";
}
