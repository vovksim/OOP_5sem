package com.musicsys.app;


import com.musicsys.Entity.Compilation;
import com.musicsys.Entity.CompilationTrack;
import com.musicsys.Entity.Track;
import com.musicsys.MusicService.MusicService;

import java.time.Duration;

public class App {
    public static void main(String[] args) {
        try {
            MusicService musicService = new MusicService();
            musicService.addTrack(new Track(1, "Track1", "rock", Duration.ofSeconds(100)));
            musicService.addTrack(new Track(2, "Track2", "rap", Duration.ofSeconds(120)));

            musicService.addCompilation(new Compilation(1, "Compilation1"));
            musicService.addTrackToCompilation(new CompilationTrack(1, 1));
            System.out.println(musicService.getCompilationDTO(1));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
