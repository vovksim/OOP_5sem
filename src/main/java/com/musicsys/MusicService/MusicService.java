package com.musicsys.MusicService;

import com.musicsys.DAO.CompilationDAO;
import com.musicsys.DAO.CompilationTrackDAO;
import com.musicsys.DAO.TrackDAO;
import com.musicsys.DTO.CompilationDTO;
import com.musicsys.Database.ConnectionPool;
import com.musicsys.Entity.Compilation;
import com.musicsys.Entity.CompilationTrack;
import com.musicsys.Entity.Track;

import java.sql.SQLException;
import java.util.ArrayList;

public class MusicService {
    private CompilationDAO compilationDAO;
    private TrackDAO trackDAO;
    private CompilationTrackDAO compilationTrackDAO;

    public MusicService() throws SQLException {
        ConnectionPool.init("jdbc:mariadb://localhost:3306/MusicSys", "root", "passwd");
        compilationDAO = new CompilationDAO(ConnectionPool.getConnection());
        trackDAO = new TrackDAO(ConnectionPool.getConnection());
        compilationTrackDAO = new CompilationTrackDAO(ConnectionPool.getConnection());
    }

    public ArrayList<Track> getAllTracks() throws SQLException {
        return trackDAO.getAll();
    }

    public ArrayList<Compilation> getAllCompilationsNames() throws SQLException {
        return compilationDAO.getAll();
    }

    public CompilationDTO getCompilationDTO(Integer compilationId) throws SQLException {
        ArrayList<CompilationTrack> relationsList = compilationTrackDAO.getAllRelationsForCompilation(compilationId);
        ArrayList<Track> relatedTracks = new ArrayList<>();
        for (CompilationTrack compilationTrack : relationsList) {
            relatedTracks.add(trackDAO.getById(compilationTrack.getTrackId()));
        }
        return new CompilationDTO(compilationDAO.get(compilationId), relatedTracks);
    }

    public void addTrack(Track track) throws SQLException {
        trackDAO.insert(track);
    }

    public void addCompilation(Compilation c) throws SQLException {
        compilationDAO.insert(c);
    }

    public void addTrackToCompilation(CompilationTrack relation) throws SQLException {
        compilationTrackDAO.insert(relation);
    }

    public void removeTrackFromCompilation(CompilationTrack relation) throws SQLException {
        compilationTrackDAO.deleteRelation(relation);
    }

    public void removeTrack(Integer trackId) throws SQLException {
        compilationTrackDAO.deleteTrackFromAllCompilations(trackId);
        trackDAO.delete(trackId);
    }

    public void removeCompilation(Integer compilationId) throws SQLException {
        compilationTrackDAO.deleteAllTracksFromCompilation(compilationId);
        compilationDAO.delete(compilationId);
    }

    public void updateTrack(Track track) throws SQLException {
        trackDAO.update(track);
    }

    public void updateCompilation(Compilation compilation) throws SQLException {
        compilationDAO.update(compilation);
    }
}
