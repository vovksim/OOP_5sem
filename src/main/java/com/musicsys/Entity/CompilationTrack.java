package com.musicsys.Entity;

import java.util.Objects;

public class CompilationTrack {
    Integer trackId;
    Integer compilationId;


    public CompilationTrack(Integer _compilationId, Integer _trackId) {
        this.compilationId = _compilationId;
        this.trackId = _trackId;
    }

    public Integer getTrackId() {
        return trackId;
    }

    public Integer getCompilationId() {
        return compilationId;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof CompilationTrack track) {
            return this.compilationId.equals(track.getCompilationId()) && this.trackId.equals(track.getTrackId());
        }
        return false;
    }
}
