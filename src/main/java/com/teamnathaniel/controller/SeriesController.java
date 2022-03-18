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

    @GetMapping("getAllSeries")
    public List<Series> getAllSeries() {
        return seriesService.getAllSeries();
    }

    @PostMapping("saveSeries")
    public Series createSeries(@RequestBody Series series) {
        return seriesService.saveSeries(series);
    }

    @GetMapping("findSeries/{id}")
    public Series findSeriesById(@PathVariable int id) {
        return seriesService.findSeriesById(id);
    }

    @GetMapping("SeriesName/{name}")
    public Series findSeriesByName(@PathVariable String name) {
        return seriesService.findSeriesByName(name);
    }

    @DeleteMapping("deleteSeries/{series}")
    public boolean deleteSeries(@PathVariable Series series) {
        seriesService.deleteSeries(series);
        System.out.println(series.toString() + " was deleted.");
        return true;
    }

    @PutMapping("updateSeries")
    public void updateSeries(@RequestBody Series series) {
        seriesService.updateSeries(series);
    }

}