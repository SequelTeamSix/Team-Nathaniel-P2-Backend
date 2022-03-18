package com.teamnathaniel.service;

import com.teamnathaniel.model.Character;
import com.teamnathaniel.model.Series;
import com.teamnathaniel.repository.SeriesRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SeriesService {
    SeriesRepository seriesRepository;

    public SeriesService(SeriesRepository seriesRepository) {
        this.seriesRepository = seriesRepository;
    }

    public List<Series> getAllSeries(){
        return seriesRepository.findAll();
    }

    public Series saveSeries(Series series){
        return seriesRepository.save(series);
    }

    public Series findSeriesByName(String name){
        return seriesRepository.findByName(name);
    }

    public void deleteSeries(Series Series){
        seriesRepository.delete(Series);
    }

    public Series findSeriesById(int id){
        return seriesRepository.findById(id);
    }

    public void updateSeries(Series series){
        //if Series is in DB, update the requested info based on provided
        if(seriesRepository.findAll().contains(series))
        {
            series.setSeriesId(series.getSeriesId());
            series.setName(series.getName());
            series.setDescription(series.getDescription());
        }
        //Series isn't in DB, go ahead and create one with provided info.
        else {
            saveSeries(series);
        }
    }
}