package com.musicsys.DAO;

import com.musicsys.Entity.Compilation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CompilationDAO extends AbstractDAO<Compilation> {

    public CompilationDAO(Connection _connection) {
        super(_connection,
                new ParameterMapper<>() {
                    @Override
                    public void mapInsertParameters(PreparedStatement stmt, Compilation entity) throws SQLException {
                        stmt.setInt(1, entity.getId());
                        stmt.setString(2, entity.getCompilationTitle());
                    }

                    @Override
                    public void mapUpdateParameters(PreparedStatement stmt, Compilation entity) throws SQLException {
                        stmt.setString(1, entity.getCompilationTitle());
                        stmt.setInt(2, entity.getId());
                    }

                    @Override
                    public void mapIdParameter(PreparedStatement stmt, Integer id) throws SQLException {
                        stmt.setInt(1, id);
                    }
                },
                new ResultSetParser<>() {
                    @Override
                    public ArrayList<Compilation> parseInList(ResultSet rs) throws SQLException {
                        ArrayList<Compilation> result = new ArrayList<>();
                        while (rs.next()) {
                            Compilation temp = new Compilation(rs.getInt(1), rs.getString(2));
                            result.add(temp);
                        }
                        return result;
                    }
                });
    }

    public void insert(Compilation entity) throws SQLException {
        String query = "INSERT INTO compilation (id, compilationTitle) VALUES (?, ?)";
        super.insert(entity, query);
    }

    public void update(Compilation entity) throws SQLException {
        String query = "UPDATE compilation SET compilationTitle = ? WHERE id = ?";
        super.update(entity, query);
    }

    public void delete(Integer id) throws SQLException {
        String query = "DELETE FROM compilation WHERE id = ?";
        super.delete(id, query);
    }

    public Compilation get(Integer id) throws SQLException {
        String query = "SELECT * FROM compilation WHERE id = ?";
        return super.getById(id, query);
    }

    public ArrayList<Compilation> getAll() throws SQLException {
        String query = "SELECT * FROM compilation";
        return super.getAll(query);
    }
}
