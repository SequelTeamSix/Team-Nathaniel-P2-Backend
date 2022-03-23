package com.teamnathaniel.controller;

import com.teamnathaniel.model.Genre;
import com.teamnathaniel.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GenreController {
    GenreService genreService;

    @Autowired
    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }

    @CrossOrigin
    @GetMapping("getAllGenres")
    public List<Genre> getAllGenres(){
        return genreService.getAllGenres();
    }

    @CrossOrigin
    @PostMapping("saveGenre")
    public Genre createGenre(@RequestBody Genre genre){
        return genreService.saveGenre(genre);
    }

    @CrossOrigin
    @GetMapping("findGenre/{genreId}")
    public Genre findGenreById(@PathVariable int genreId){
        return genreService.findGenreById(genreId);
    }

    @CrossOrigin
    @DeleteMapping("deleteGenre/{genreId}")
    public boolean deleteGenre(@PathVariable int genreId){
        genreService.deleteGenre(genreId);
        System.out.println("Genre with id " + genreId + " was deleted. ");
        return true;
    }

    @CrossOrigin
    @PutMapping("updateGenre/{genreId}")
    public Genre updateGenre(@PathVariable int genreId, @RequestBody Genre genre){
        return genreService.updateGenre(genreId, genre);
    }
}
