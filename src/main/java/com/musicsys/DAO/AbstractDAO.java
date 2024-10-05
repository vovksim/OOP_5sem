package com.musicsys.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.ResultSet;

public abstract class AbstractDAO<T> implements AutoCloseable {
    Connection connection;

    public interface ParameterMapper<T> {
        void mapInsertParameters(PreparedStatement stmt, T entity) throws SQLException;

        default void mapUpdateParameters(PreparedStatement stmt, T entity) throws SQLException {}

        void mapIdParameter(PreparedStatement stmt, Integer id) throws SQLException;
    }

    public interface ResultSetParser<T> {
        ArrayList<T> parseInList(ResultSet rs) throws SQLException;
    }

    protected final ParameterMapper<T> parameterMapper;
    protected final ResultSetParser<T> resultSetParser;


    protected AbstractDAO(Connection _connection, ParameterMapper<T> _mapper, ResultSetParser<T> _parser) {
        this.connection = _connection;
        this.parameterMapper = _mapper;
        this.resultSetParser = _parser;
    }

    protected void insert(T entity, String query) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement(query);
        parameterMapper.mapInsertParameters(stmt, entity);
        stmt.executeUpdate();
    }

    protected void update(T entity, String query) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement(query);
        parameterMapper.mapUpdateParameters(stmt, entity);
        stmt.executeUpdate();
    }

    protected void delete(Integer id, String query) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement(query);
        parameterMapper.mapIdParameter(stmt, id);
        stmt.executeUpdate();
    }

    protected T getById(Integer id, String query) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement(query);
        parameterMapper.mapIdParameter(stmt, id);
        return resultSetParser.parseInList(stmt.executeQuery()).get(0);
    }

    protected ArrayList<T> getAll(String query) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement(query);
        return resultSetParser.parseInList(stmt.executeQuery());
    }

    @Override
    public void close() throws SQLException {
        connection.close();
    }
}
