package com.musicsys.Entity;

public class CompilationTrack {
  Integer trackId;
  Integer compilationId;


  public CompilationTrack(Integer _trackId, Integer _compilationId) {
    this.compilationId = _compilationId;
    this.trackId = _trackId;
  }

  public Integer getTrackId() {
    return compilationId;
  }

  public Integer getCompilationId() {
    return compilationId;
  }
}
