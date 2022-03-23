package com.teamnathaniel.repository;

import com.teamnathaniel.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GenreRepository extends JpaRepository<Genre, String> {

    @Query("From Genre")
    List<Genre> findAll();

    @Query("From Genre where genreId = :genreId")
    Genre findByGenreId(int genreId);

    Genre save(Genre genre);

    void deleteByGenreId(int genreId);
}
