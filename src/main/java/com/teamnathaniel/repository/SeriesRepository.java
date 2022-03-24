package com.teamnathaniel.repository;

import com.teamnathaniel.model.Series;
import com.teamnathaniel.model.Character;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface SeriesRepository extends JpaRepository<Series, String> {

    @Query("From Series")
    List<Series> findAll();

    @Query("from Series where name = :name")
    Series findByName(String name);

    @Query("from Series where seriesId = :id")
    Series findById(int id);

    List<Series> findAllByCharacter(Character character);

    Series save(Series series);

    void deleteBySeriesId(int seriesId);
}
