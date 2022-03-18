package com.teamnathaniel.repository;

import com.teamnathaniel.model.Series;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface SeriesRepository extends JpaRepository<Series, String> {

    @Query("From Series")
    List<Series> findAll();

    @Query("from Series where SeriesName = :name")
    Series findByName(String name);

    @Query("from Series where seriesId = :id")
    Series findById(int id);

    Series save(Series series);

    void deleteBySeriesId(int seriesId);

}
