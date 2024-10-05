package com.musicsys.Entity;

public class CompilationTrack {
    Integer trackId;
    Integer compilationId;


    public CompilationTrack(Integer _compilationId, Integer _trackId) {
        this.compilationId = _compilationId;
        this.trackId = _trackId;
    }

    public Integer getTrackId() {
        return compilationId;
    }

    public Integer getCompilationId() {
        return compilationId;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof CompilationTrack track) {
            return track.compilationId.equals(track.getTrackId()) && track.trackId.equals(track.getTrackId());
        }
        return false;
    }
}
