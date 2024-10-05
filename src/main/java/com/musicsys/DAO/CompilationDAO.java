package com.musicsys.DAO;

import com.musicsys.Entity.Compilation;
import com.musicsys.Entity.CompilationTrack;

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
}
