package micronaut.neo4j.example.controller;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import micronaut.neo4j.example.controller.dto.MusicianDTO;
import micronaut.neo4j.example.service.MusicianService;

import javax.validation.constraints.NotNull;

@Controller("/api/musician")
public class MusicianController {

    private MusicianService musicianService;

    public MusicianController(MusicianService musicianService) {
        this.musicianService = musicianService;
    }

    @Get(uri = "/{musicianId}")
    public MusicianDTO getMusician(@NotNull Integer musicianId) {
        return musicianService.findMusicianById(musicianId);
    }

    @Post
    public void createMusician(MusicianDTO musicianDTO) {
        musicianService.createMusician(musicianDTO);
    }

}
