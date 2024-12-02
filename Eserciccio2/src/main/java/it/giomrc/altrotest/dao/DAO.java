package it.giomrc.altrotest.dao;

import it.giomrc.altrotest.model.Piatto;

import java.sql.SQLException;
import java.util.Set;

public interface DAO<T> {
    Set<T> findAll() throws SQLException, Exception;
    T findById(Long id) throws SQLException, Exception;
    void create(T entity) throws SQLException, Exception;
    void delete(Long id) throws SQLException, Exception;
    public void update(T entity) throws Exception;
}
