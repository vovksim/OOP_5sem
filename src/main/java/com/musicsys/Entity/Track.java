package com.musicsys.Entity;

import java.lang.String;
import java.time.Duration;

public class Track {
    Integer id;
    String trackTitle;
    String genre;
    Duration duration;

    public Track(Integer _id, String _trackTitle, String _genre, Duration _duration) {
        this.id = _id;
        this.trackTitle = _trackTitle;
        this.genre = _genre;
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

    public String getGenre() {
        return genre;
    }

    public void setTrackTitle(String _trackTitle) {
        this.trackTitle = _trackTitle;
    }

    public void setDuration(Duration _duration) {
        this.duration = _duration;
    }

    public void setGenre(String _genre) {
        this.genre = _genre;
    }

    @Override
    public String toString() {
        return id + "." + trackTitle + " " + genre + " " + duration.toMinutes() + "m " + duration.toSecondsPart() + "s";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Track track) {
            return this.id.equals(track.getId()) && this.trackTitle.equals(track.getTitle()) && this.genre.equals(track.genre) && this.duration.equals(track.getDuration());
        }
        return false;
    }
}
