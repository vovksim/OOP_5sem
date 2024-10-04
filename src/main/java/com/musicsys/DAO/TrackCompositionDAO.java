package com.musicsys.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.musicsys.Entity.CompilationTrack;

public class TrackCompositionDAO extends AbstractJunctionDAO<CompilationTrack> {

  public TrackCompositionDAO(Connection _connection) {
    super(_connection, 
      new ParameterMapper<CompilationTrack>() {
        @Override
        public void mapInsertParameters(PreparedStatement stmt, CompilationTrack entity) throws SQLException {
          stmt.setInt(1, entity.getCompilationId());
          stmt.setInt(2, entity.getTrackId());
        }

        @Override
        public void mapIdParameter(PreparedStatement stmt, Integer id) throws SQLException {
          stmt.setInt(1,id); 
        }
      },
      new ResultSetParser<CompilationTrack>() {
        @Override
        public ArrayList<CompilationTrack> parseInList(ResultSet rs) throws SQLException {
          ArrayList<CompilationTrack> result = new ArrayList<>();
          while(rs.next()) {
            CompilationTrack temp = new CompilationTrack(rs.getInt(1),rs.getInt(2));
            result.add(temp);
          }
          return result;
        }
      });

  }

  public void deleteRelation(CompilationTrack relation) throws SQLException {
    String query = "DELETE FROM CompilationTrack WHERE compilation_id = ? AND track_id = ?;";
    super.removeRelation(relation, query);
  }

  public void deleteAllTracksFromCompilation(Integer compilationId) throws SQLException {
    String query="DELETE FROM CompilationTrack WHERE compilation_id = ?;";
    super.delete(compilationId, query);
  }

  public void deleteTrackFromAllCompilations(Integer trackId) throws SQLException {
    String query="DELETE FROM CompilationTrack WHERE track_id = ?;";
    super.delete(trackId, query);
  }

  public void insert(CompilationTrack relation) throws SQLException {
    String query = "INSERT INTO CompilationTrack (compilation_id, track_id) VALUES (?, ?);";
    super.insert(relation, query);
  }

  public ArrayList<CompilationTrack> getAllRelationsForTrack(Integer trackId) throws SQLException {
    String query = "SELECT * FROM CompilationTrack WHERE track_id = ?";
    return super.getAllRelationsById(trackId, query);
  }

  public ArrayList<CompilationTrack> getAllRelationsForCompilation(Integer compilationId) throws SQLException {
    String query = "SELECT * FROM CompilationTrack WHERE track_id = ?";
    return super.getAllRelationsById(compilationId, query);
  }
}
