package com.exhibition.model.dao.impl;

import com.exhibition.model.dao.ExhibitionDao;
import com.exhibition.model.dao.mapper.ExhibitionMapper;
import com.exhibition.model.entity.Exhibition;
import com.exhibition.model.entity.User;

import java.sql.*;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class ExhibitionJdbcDao implements ExhibitionDao {

    private Connection connection;

    private ExhibitionMapper mapper;

    public ExhibitionJdbcDao(Connection connection) {
        this.connection = connection;
        mapper = new ExhibitionMapper();
    }

    @Override
    public void add(Exhibition entity) {
        try(PreparedStatement ps =
                connection.prepareStatement(
                        QUERY_EXHIBITION_ADD)
        ) {
            ps.setString(1, entity.getName());
            ps.setString(2, entity.getShowroom());
            ps.setString(3, entity.getDescription());
            ps.setLong(4, entity.getAuthor().getId());
            ps.setLong(5, entity.getPrice());
            ps.setString(6, entity.getDate().toString());

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Exhibition findByName(String name) {
        try (PreparedStatement ps =
                     connection.prepareStatement(
                             QUERY_EXHIBITION_FIND_BY_NAME)
        ) {
            ps.setString(1, name);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return mapper.extractFromResultSet(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Exhibition does not exist.");
        }
        return null;
    }

    @Override
    public List<Exhibition> findByShowroom(String showroom) {
        List<Exhibition> resultList = new CopyOnWriteArrayList<>();

        try (PreparedStatement ps =
                    connection.prepareStatement(
                            QUERY_EXHIBITION_FIND_BY_SHOWROOM)
        ) {
            ps.setString(1, showroom);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Exhibition ex = mapper.extractFromResultSet(rs);
                resultList.add(ex);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Didn't find exhibition");
        }

        if (!resultList.isEmpty()) {
            return resultList;
        } else {
            return null;
        }
    }

    @Override
    public List<Exhibition> findByAuthor(User user) {
        List<Exhibition> resultList = new CopyOnWriteArrayList<>();

        try (PreparedStatement ps =
                     connection.prepareStatement(
                             QUERY_EXHIBITION_FIND_BY_AUTHOR)
        ) {
            ps.setLong(1, user.getId());

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Exhibition ex = mapper.extractFromResultSet(rs);
                resultList.add(ex);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Didn't find exhibition");
        }

        if (!resultList.isEmpty()) {
            return resultList;
        } else {
            return null;
        }
    }

    @Override
    public List<Exhibition> findBoughtTickets(User user) {
        List<Exhibition> resultList = new CopyOnWriteArrayList<>();

        try (PreparedStatement ps =
                     connection.prepareStatement(
                             QUERY_FIND_BOUGHT_TICKETS)
        ) {
            ps.setLong(1, user.getId());

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Exhibition result = mapper.extractFromResultSet(rs);
                resultList.add(result);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return resultList;
    }

    @Override
    public int countOfRecords()  {
        try (PreparedStatement ps =
                     connection.prepareStatement(
                             QUERY_COUNT_OF_RECORDS)
        ) {
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public List<Exhibition> findRange(int from, int count) {
        List<Exhibition> resultList = new CopyOnWriteArrayList<>();

        try(PreparedStatement ps =
                    connection.prepareStatement(
                            QUERY_FIND_RANGE)
        ){
            ps.setInt(1, from);
            ps.setInt(2, count);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Exhibition ex = mapper.extractFromResultSet(rs);
                resultList.add(ex);
            }
        } catch (SQLException e) {
            throw new RuntimeException("QUERY_FIND_RANGE");
        }

        if (!resultList.isEmpty()) {
            return resultList;
        } else {
            return null;
        }
    }

    @Override
    public Exhibition findById(Long id) {
        try (PreparedStatement ps =
                     connection.prepareStatement(
                             QUERY_EXHIBITION_FIND_BY_ID)
        ) {
            ps.setLong(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return mapper.extractFromResultSet(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException("No exhibition by this Id");
        }
        return null;
    }

    @Override
    public List<Exhibition> findAll() {
        List<Exhibition> resultList = new CopyOnWriteArrayList<>();

        try(Statement statement =
                connection.createStatement()
        ){
           ResultSet rs = statement.executeQuery(QUERY_EXHIBITION_FIND_ALL);

           while (rs.next()) {
               Exhibition ex = mapper.extractFromResultSet(rs);
               resultList.add(ex);
           }
        } catch (SQLException e) {
            throw new RuntimeException("");
        }

        if (!resultList.isEmpty()) {
            return resultList;
        } else {
            return null;
        }
    }


    @Override
    public void update(Exhibition entity) {
        try (PreparedStatement ps =
                     connection.prepareStatement(
                             QUERY_EXHIBITION_UPDATE)
        ) {
            ps.setString(1, entity.getName());
            ps.setString(2, entity.getShowroom());
            ps.setString(3, entity.getDescription());
            ps.setLong(4, entity.getAuthor().getId());
            ps.setLong(5, entity.getPrice());
            ps.setString(6, entity.getDate().toString());
            ps.setLong(7, entity.getId());

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("");
        }
    }

    @Override
    public void delete(Long id) {
        try (PreparedStatement ps =
                     connection.prepareStatement(
                             QUERY_EXHIBITION_DELETE_BY_ID)
        ) {
            ps.setLong(1, id);

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteEntity(Exhibition entity) {
        try (PreparedStatement ps =
                     connection.prepareStatement(
                             QUERY_EXHIBITION_DELETE_BY_ID)
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

    private static final String QUERY_EXHIBITION_ADD =
            "INSERT INTO exhibition (name , showroom , description, author, price, date) VALUES (? ,? ,?, ?, ?, ?)";
    private static final String QUERY_EXHIBITION_FIND_BY_SHOWROOM =
            "SELECT * FROM exhibition WHERE showroom = ?";
    private static final String QUERY_EXHIBITION_FIND_BY_ID =
            "SELECT * FROM exhibition WHERE id = ?";
    private static final String QUERY_EXHIBITION_FIND_ALL =
            "SELECT * FROM exhibition ORDER BY id DESC ";
    private static final String QUERY_EXHIBITION_UPDATE =
            "UPDATE exhibition SET name = ? , showroom = ?, description = ?, author = ?, price = ?, date = ? WHERE id = ?";
    private static final String QUERY_EXHIBITION_DELETE_BY_ID =
            "DELETE FROM exhibition  WHERE id = ?";
    private static final String QUERY_EXHIBITION_FIND_BY_AUTHOR =
            "SELECT * FROM exhibition WHERE author = ? ORDER BY id DESC";
    private static final String QUERY_FIND_BOUGHT_TICKETS =
            "SELECT id, name, showroom, description, author, price, date FROM exhibition INNER JOIN ticket ON id = ticket_id AND user_id = ? ORDER BY id DESC";
    private static final String  QUERY_COUNT_OF_RECORDS =
            "SELECT COUNT(*) FROM exhibition";
    private static final String QUERY_FIND_RANGE =
            "SELECT * FROM exhibition ORDER BY id DESC LIMIT ?, ?";
    private static final String QUERY_EXHIBITION_FIND_BY_NAME =
            "SELECT * FROM exhibition WHERE name = ?";
}
