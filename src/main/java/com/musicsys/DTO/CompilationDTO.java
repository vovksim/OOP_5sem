package com.musicsys.DTO;

import com.musicsys.Entity.Compilation;
import com.musicsys.Entity.Track;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Comparator;

public class CompilationDTO {
    private Compilation compilation;
    private ArrayList<Track> tracks;

    public CompilationDTO(Compilation _compilation, ArrayList<Track> _tracks) {
        this.compilation = _compilation;
        this.tracks = _tracks;
    }

    void sortByGenre() {
        tracks.sort(new Comparator<Track>() {
            public int compare(Track o1, Track o2) {
                return o1.getGenre().compareTo(o2.getGenre());
            }
        });
    }

    public Compilation getCompilation() {
        return compilation;
    }

    public ArrayList<Track> getTracks() {
        return tracks;
    }

    ArrayList<Track> getTracksByDuration(Duration lowerBound, Duration upperBound) {
        ArrayList<Track> result = new ArrayList<>();
        for (Track track : tracks) {
            if (track.getDuration().compareTo(lowerBound) > 0 && track.getDuration().compareTo(upperBound) < 0)
                result.add(track);
        }
        return result;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append(result.toString());
        for (Track track : tracks) {
            result.append(track.toString());
        }
        return result.toString();
    }
}
