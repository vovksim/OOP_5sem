package com.musicsys.DAO;

import com.musicsys.Entity.Track;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.Duration;
import java.util.ArrayList;

public class TrackDAO extends AbstractDAO<Track> {
    public TrackDAO(Connection _connection) {
        super(_connection,
                new AbstractDAO.ParameterMapper<>() {
                    @Override
                    public void mapInsertParameters(PreparedStatement stmt, Track entity) throws SQLException {
                        stmt.setInt(1, entity.getId());
                        stmt.setString(2, entity.getTitle());
                        stmt.setString(3, entity.getGenre());
                        stmt.setLong(4, entity.getDuration().toSeconds());
                    }

                    @Override
                    public void mapUpdateParameters(PreparedStatement stmt, Track entity) throws SQLException {
                        stmt.setString(1, entity.getTitle());
                        stmt.setString(2, entity.getGenre());
                        stmt.setLong(3, entity.getDuration().toSeconds());
                        stmt.setInt(4, entity.getId());
                    }

                    @Override
                    public void mapIdParameter(PreparedStatement stmt, Integer id) throws SQLException {
                        stmt.setInt(1, id);
                    }
                },
                new AbstractDAO.ResultSetParser<>() {
                    public ArrayList<Track> parseInList(ResultSet rs) throws SQLException {
                        ArrayList<Track> result = new ArrayList<>();
                        while (rs.next()) {
                            Duration duration = Duration.ofSeconds(rs.getLong(4));
                            Track temp = new Track(rs.getInt(1), rs.getString(2), rs.getString(3), duration);
                            result.add(temp);
                        }
                        return result;
                    }
                }
        );
    }

    public void insert(Track newTrack) throws SQLException {
        String query = "INSERT INTO Track (id, title, genre, duration) VALUES (?, ?, ?, ?);";
        super.insert(newTrack, query);
    }

    public Track getById(Integer id) throws SQLException {
        String query = "SELECT * FROM Track WHERE id=?;";
        return super.getById(id, query);
    }

    public ArrayList<Track> getAll() throws SQLException {
        String query = "SELECT * FROM Track;";
        return super.getAll(query);
    }

    public void delete(Integer id) throws SQLException {
        String query = "DELETE FROM Track WHERE id=?;";
        super.delete(id, query);
    }

    public void update(Track track) throws SQLException {
        String query = "UPDATE Track SET title=?, genre=?, duration=? WHERE id=?";
        super.update(track, query);
    }
}
