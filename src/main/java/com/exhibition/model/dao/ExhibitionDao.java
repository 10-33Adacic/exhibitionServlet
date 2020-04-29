package com.exhibition.model.dao;

import com.exhibition.model.entity.Exhibition;
import com.exhibition.model.entity.User;

import java.util.List;

public interface ExhibitionDao extends GenericDao<Exhibition> {

    Exhibition findByName(String name);

    List<Exhibition> findByShowroom(String username);

    List<Exhibition> findByAuthor(User user);

    List<Exhibition> findBoughtTickets(User user);

    int countOfRecords();

    List<Exhibition> findRange(int from, int to);
}
