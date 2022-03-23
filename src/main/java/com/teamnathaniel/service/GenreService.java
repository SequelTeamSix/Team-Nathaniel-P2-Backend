package com.teamnathaniel.service;

import com.teamnathaniel.model.Genre;
import com.teamnathaniel.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GenreService {
    GenreRepository genreRepository;

    @Autowired
    public GenreService(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    public List<Genre> getAllGenres(){
        return genreRepository.findAll();
    }

    public Genre saveGenre(Genre genre){
        return genreRepository.save(genre);
    }

    public Genre findGenreById(int genreId){
        return genreRepository.findByGenreId(genreId);
    }

    public void deleteGenre(int genreId){
        genreRepository.deleteByGenreId(genreId);
    }

    public Genre updateGenre(int genreId, Genre genre){
        Genre oldGenre = genreRepository.findByGenreId(genreId);
        if(oldGenre != null){
            genre.setGenreId(genre.getGenreId());

            if(genre.getGenre() != null){
                genre.setGenre(genre.getGenre());
            }
        }
        return genreRepository.save(genre);
    }
}
