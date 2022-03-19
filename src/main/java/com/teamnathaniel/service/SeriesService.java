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

    public void deleteSeries(int seriesId){
        seriesRepository.deleteBySeriesId(seriesId);
    }

    public Series findSeriesById(int id){
        return seriesRepository.findById(id);
    }

    public Series updateSeries(int seriesId, Series series){
        Series oldSeries = seriesRepository.findById(seriesId);
        //if Series is in DB, update the requested info based on provided
        if(oldSeries != null) {
            if (series.getName() != null) {
                oldSeries.setName(series.getName());
            }
            if (series.getDescription() != null) {
                oldSeries.setDescription(series.getDescription());
            }
            return saveSeries(oldSeries);
        }
        //Series isn't in DB, go ahead and create one with provided info.
        else {
            return saveSeries(series);
        }
    }
}