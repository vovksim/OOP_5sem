package com.musicsys.DAO;

import com.musicsys.Entity.CompilationTrack;
import org.h2.jdbcx.JdbcDataSource;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class CompilationTrackDAOTest {
    private static Connection connection;
    private static CompilationTrackDAO compilationTrackDAO;

    @BeforeAll
    public static void setupDatabase() throws SQLException {
        JdbcDataSource dataSource = new JdbcDataSource();
        dataSource.setURL("jdbc:h2:mem:testDB;DB_CLOSE_DELAY=-1;MODE=MySQL");
        connection = dataSource.getConnection();

        connection.createStatement().execute(
                "CREATE TABLE CompilationTrack (" +
                        "compilation_id INTEGER," +
                        "track_id INTEGER);"
        );
        compilationTrackDAO = new CompilationTrackDAO(connection);
    }

    @AfterAll
    public static void closeDatabase() throws SQLException {
        connection.close();
    }

    @AfterEach
    public void tearDown() throws SQLException {
        connection.createStatement().execute("DELETE FROM CompilationTrack");
    }

    @Test
    void parser() throws SQLException {
        connection.createStatement().execute("INSERT INTO CompilationTrack (compilation_id,track_id) VALUES (1,1)");
        connection.createStatement().execute("INSERT INTO  CompilationTrack (compilation_id,track_id) VALUES (2,2)");

        ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM CompilationTrack");

        ArrayList<CompilationTrack> expected = new ArrayList<>();
        expected.add(new CompilationTrack(1,1));
        expected.add(new CompilationTrack(2,2));

        ArrayList<CompilationTrack> actual = compilationTrackDAO.resultSetParser.parseInList(resultSet);

        assertEquals(expected, actual);
    }

    @Test
    void deleteRelation() throws SQLException {
        connection.createStatement().execute("INSERT INTO  CompilationTrack (compilation_id,track_id) VALUES (1,1)");

        compilationTrackDAO.deleteRelation(new CompilationTrack(1,1));

        ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM CompilationTrack");

        assertFalse(resultSet.next());
    }

    @Test
    void deleteAllTracksFromCompilation() throws SQLException {
        //compilation 1
        connection.createStatement().execute("INSERT INTO  CompilationTrack (compilation_id,track_id) VALUES (1,1)");
        connection.createStatement().execute("INSERT INTO  CompilationTrack (compilation_id,track_id) VALUES (1,2)");
        //compilation 2
        connection.createStatement().execute("INSERT INTO  CompilationTrack (compilation_id,track_id) VALUES (2,1)");

        compilationTrackDAO.deleteAllTracksFromCompilation(1);

        ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM CompilationTrack WHERE compilation_id=1");

        //1 compilation is empty
        assertFalse(resultSet.next());

        resultSet = connection.createStatement().executeQuery("SELECT * FROM CompilationTrack WHERE compilation_id=2");

        //2 compilation still holds 1 track
        assertTrue(resultSet.next());
    }

    @Test
    void deleteTrackFromAllCompilations() {
    }

    @Test
    void insert() {
    }

    @Test
    void getAllRelationsForTrack() {
    }

    @Test
    void getAllRelationsForCompilation() {
    }
}