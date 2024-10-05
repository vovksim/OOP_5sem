package com.musicsys.DAO;

import org.junit.jupiter.api.*;
import org.h2.jdbcx.JdbcDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Duration;
import java.util.ArrayList;

import com.musicsys.Entity.Track;

import static org.junit.jupiter.api.Assertions.*;

public class TrackDAOTest {
    private static Connection connection;
    private static TrackDAO trackDAO;

    @BeforeAll
    public static void setupDatabase() throws SQLException {
        JdbcDataSource dataSource = new JdbcDataSource();
        dataSource.setURL("jdbc:h2:mem:testDB;DB_CLOSE_DELAY=-1;MODE=MySQL");
        connection = dataSource.getConnection();

        connection.createStatement().execute(
                "CREATE TABLE Track (" +
                        "id INTEGER PRIMARY KEY," +
                        "title VARCHAR(255), " +
                        "genre VARCHAR(50)," +
                        "duration INTEGER);"
        );
        trackDAO = new TrackDAO(connection);
    }

    @AfterAll
    public static void closeDatabase() throws SQLException {
        connection.close();
    }

    @AfterEach
    public void tearDown() throws SQLException {
        connection.createStatement().execute("DELETE FROM Track");
    }

    @Test
    void parser() throws SQLException {
        connection.createStatement().execute("INSERT INTO Track (id, title, genre, duration) VALUES (1, 'Track1', 'rock', 100)");
        connection.createStatement().execute("INSERT INTO Track (id, title, genre, duration) VALUES (2, 'Track2', 'pop', 150)");

        ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM Track");

        ArrayList<Track> expected = new ArrayList<>();
        expected.add(new Track(1, "Track1", "rock", Duration.ofSeconds(100)));
        expected.add(new Track(2, "Track2", "pop", Duration.ofSeconds(150)));

        ArrayList<Track> actual = trackDAO.resultSetParser.parseInList(resultSet);

        assertEquals(expected, actual);
    }


    @Test
    public void testInsertTrack() throws SQLException {
        Track excpectedTrack = new Track(1, "Track1", "rock", Duration.ofSeconds(100));

        trackDAO.insert(excpectedTrack);

        ResultSet rs = connection.createStatement().executeQuery("SELECT * FROM Track");

        Track actualTrack = trackDAO.resultSetParser.parseInList(rs).get(0);

        assertEquals(excpectedTrack, actualTrack);
    }

    @Test
    public void testUpdateTrack() throws SQLException {
        //initial entity
        connection.createStatement().execute("INSERT INTO Track (id, title, genre, duration) VALUES (1, 'Track1', 'rock', 100)");

        //setting new fields, except of id
        Track expectedTrack = new Track(1, "Track2", "pop", Duration.ofSeconds(150));

        trackDAO.update(expectedTrack);

        ResultSet rs = connection.createStatement().executeQuery("SELECT * FROM Track");

        Track actualTrack = trackDAO.resultSetParser.parseInList(rs).get(0);

        assertEquals(expectedTrack, actualTrack);
    }

    @Test
    void getById() throws SQLException {
        Track expectedTrack = new Track(1, "Track1", "rock", Duration.ofSeconds(100));

        connection.createStatement().execute("INSERT INTO Track (id, title, genre, duration) VALUES (1, 'Track1', 'rock', 100)");

        Track actualTrack = trackDAO.getById(1);

        assertEquals(expectedTrack, actualTrack);
    }

    @Test
    void getAll() throws SQLException {
        ArrayList<Track> expectedResult = new ArrayList<>();
        Track expectedTrack1 = new Track(1, "Track1", "rock", Duration.ofSeconds(100));
        Track expectedTrack2 = new Track(2, "Track2", "pop", Duration.ofSeconds(150));
        expectedResult.add(expectedTrack1);
        expectedResult.add(expectedTrack2);

        connection.createStatement().execute("INSERT INTO Track (id, title, genre, duration) VALUES (1, 'Track1', 'rock', 100)");
        connection.createStatement().execute("INSERT INTO Track (id, title, genre, duration) VALUES (2, 'Track2', 'pop', 150)");

        trackDAO.getAll();

        ResultSet rs = connection.createStatement().executeQuery("SELECT * FROM Track");

        ArrayList<Track> actualResult = trackDAO.resultSetParser.parseInList(rs);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void delete() throws SQLException {
        connection.createStatement().execute("INSERT INTO Track (id, title, genre, duration) VALUES (1, 'Track1', 'rock', 100)");

        trackDAO.delete(1);

        ResultSet rs = connection.createStatement().executeQuery("SELECT * FROM Track");

        assertFalse(rs.next());
    }
}