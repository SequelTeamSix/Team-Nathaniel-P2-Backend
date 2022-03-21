package com.teamnathaniel.controller;

import com.teamnathaniel.model.Series;
import com.teamnathaniel.service.SeriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SeriesController {
    SeriesService seriesService;

    @Autowired
    public SeriesController(SeriesService seriesService) {
        this.seriesService = seriesService;
    }

    @CrossOrigin
    @GetMapping("getAllSeries")
    public List<Series> getAllSeries() {
        return seriesService.getAllSeries();
    }

    @CrossOrigin
    @PostMapping("saveSeries")
    public Series createSeries(@RequestBody Series series) {
        return seriesService.saveSeries(series);
    }

    @CrossOrigin
    @GetMapping("findSeries/{id}")
    public Series findSeriesById(@PathVariable int id) {
        return seriesService.findSeriesById(id);
    }

    @CrossOrigin
    @GetMapping("SeriesName/{name}")
    public Series findSeriesByName(@PathVariable String name) {
        return seriesService.findSeriesByName(name);
    }

    @CrossOrigin
    @DeleteMapping("deleteSeries/{seriesId}")
    public boolean deleteSeries(@PathVariable int seriesId) {
        seriesService.deleteSeries(seriesId);
        System.out.println("Series with Id " + seriesId + " was deleted.");
        return true;
    }

    @CrossOrigin
    @PutMapping("updateSeries/{seriesId}")
    public Series updateSeries(@PathVariable int seriesId, @RequestBody Series series) {
        return seriesService.updateSeries(seriesId, series);
    }

}