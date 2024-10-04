package com.musicsys.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class AbstractJunctionDAO<T> extends AbstractDAO<T> {
  public AbstractJunctionDAO(Connection _connection, AbstractDAO.ParameterMapper<T> _mapper, AbstractDAO.ResultSetParser<T> _parser) {
    super(_connection, _mapper, _parser);
  }

  public ArrayList<T> getAllRelationsById(Integer relationId, String query) throws SQLException {
    PreparedStatement stmt = connection.prepareStatement(query);
    parameterMapper.mapIdParameter(stmt, relationId);
    return resultSetParser.parseInList(stmt.executeQuery());
  }

  public void removeRelation(T relation, String query) throws SQLException {
    PreparedStatement stmt = connection.prepareStatement(query);
    parameterMapper.mapInsertParameters(stmt, relation);
    stmt.executeUpdate();
  }
}
