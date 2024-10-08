package com.musicsys.DTO;

import com.musicsys.Entity.Compilation;
import com.musicsys.Entity.Track;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class CompilationDTOTest {

    @Test
    void sortByGenre() {
        ArrayList<Track> initialTrackList = new ArrayList<>();
        initialTrackList.add(new Track(1, "Track1", "rock", Duration.ZERO));
        initialTrackList.add(new Track(2, "Track2", "pop", Duration.ZERO));
        initialTrackList.add(new Track(3, "Track3", "rap", Duration.ZERO));
        initialTrackList.add(new Track(4, "Track4", "rock", Duration.ZERO));

        CompilationDTO dto = new CompilationDTO(new Compilation(1, "Compilation1"), initialTrackList);

        dto.sortByGenre();

        assertEquals("pop",dto.getTracks().get(0).getGenre());
        assertEquals("rap",dto.getTracks().get(1).getGenre());
        assertEquals("rock",dto.getTracks().get(2).getGenre());
        assertEquals("rock",dto.getTracks().get(3).getGenre());
    }

    @Test
    void getTracksByDuration() {
        ArrayList<Track> initialTrackList = new ArrayList<>();
        initialTrackList.add(new Track(1, "Track1", "rock", Duration.ofSeconds(100)));
        initialTrackList.add(new Track(2, "Track2", "pop", Duration.ofSeconds(200)));
        initialTrackList.add(new Track(3, "Track3", "rap", Duration.ofSeconds(250)));
        initialTrackList.add(new Track(4, "Track4", "rock", Duration.ofSeconds(300)));

        CompilationDTO dto = new CompilationDTO(new Compilation(1, "Compilation1"), initialTrackList);

        ArrayList<Track> result = dto.getTracksByDuration(Duration.ofSeconds(150),Duration.ofSeconds(300));
        assertEquals(2,result.get(0).getId());
        assertEquals(3,result.get(1).getId());
        assertEquals(2,result.size());
    }
}