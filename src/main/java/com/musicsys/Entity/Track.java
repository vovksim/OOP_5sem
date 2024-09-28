package com.musicsys.Entity;

import java.lang.String;
import java.time.Duration;

public class Track {
  Integer id;
  String trackTitle;
  Duration duration;

  public Track(Integer _id, String _trackTitle, Duration _duration) {
    this.id = _id;
    this.trackTitle = _trackTitle;
    this.duration = _duration;
  }

  public Integer getId() {
    return id;
  }

  public String getTitle() {
    return trackTitle;
  }

  public Duration getDuration() {
    return duration;
  }

  public void setId(Integer _id) {
    this.id = _id; 
  }

  public void setTrackTitle(String _trackTitle) {
    this.trackTitle = _trackTitle;
  }

  public void setDuration(Duration _duration) {
    this.duration = _duration;
  }

  @Override
  public String toString() {
    return id + ". " + trackTitle + duration.toMinutes() + "m " + duration.toSecondsPart() + "s";
  }
}
