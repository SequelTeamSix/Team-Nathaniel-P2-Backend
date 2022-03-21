package com.teamnathaniel.service;

import com.teamnathaniel.model.Series;
import com.teamnathaniel.repository.SeriesRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class SeriesServiceTest {
    private SeriesService subject;

    private SeriesRepository rEpOsItOrY;

    private List<Series> allSeries;
    private Series zelda;

    private static boolean deleteCalled = false;


    @BeforeEach
    void sEtUp() {
        rEpOsItOrY = Mockito.mock(SeriesRepository.class);
        subject = new SeriesService(rEpOsItOrY);

        allSeries = new ArrayList<>();
        Series halo = new Series();
        halo.setDescription("A Sci-Fi first person shooter series");
        halo.setName("The Halo Series");
        allSeries.add(halo);

        zelda = new Series();
        zelda.setName("The Legend of Zelda Series");
        zelda.setDescription("A fantasy game series starring Link");
    }

    @Test
    void getAllSeries() {
        Mockito.when(rEpOsItOrY.findAll()).thenReturn(allSeries);
        assertEquals(subject.getAllSeries(), allSeries);
    }

    @Test
    void saveSeries() {
        Mockito.when(rEpOsItOrY.save(any(Series.class))).thenReturn(zelda);
        assertEquals(subject.saveSeries(zelda), zelda);
    }

    @Test
    void findSeriesByName() {
        Mockito.when(rEpOsItOrY.findByName(any(String.class))).thenReturn(zelda);
        assertEquals(subject.findSeriesByName("zelda"), zelda);
    }

    @Test
    void deleteSeries() {
        Mockito.doAnswer(invocationOnMock -> {
            deleteCalled = true;
            return null;
        }).when(rEpOsItOrY).deleteBySeriesId(any(Integer.class));
        subject.deleteSeries(0);
        assertTrue(deleteCalled);
    }

    @Test
    void findSeriesById() {
        Mockito.when(rEpOsItOrY.findById(any(Integer.class))).thenReturn(zelda);
        assertEquals(subject.findSeriesById(0), zelda);
    }

    @Test
    void updateSeries() {
        Mockito.when(rEpOsItOrY.findById(0)).thenReturn(zelda);
        when(rEpOsItOrY.save(any(Series.class))).thenReturn(zelda);
        Series newZelda = new Series();
        newZelda.setDescription("A game about breaking jars and jaws");
        newZelda.setName("Zaldo");
        assertEquals(subject.updateSeries(0, newZelda), zelda);
        assertEquals(zelda.getDescription(), "A game about breaking jars and jaws");
        assertEquals(zelda.getName(), "Zaldo");
    }

    @Test
    void updateSeriesNoPrev() {
        when(rEpOsItOrY.findById(any(Integer.class))).thenReturn(null);
        when(rEpOsItOrY.save(any(Series.class))).then(invocationOnMock -> {
            return invocationOnMock.getArgument(0, Series.class);
        });
        assertEquals(subject.updateSeries(0, zelda), zelda);
    }
}
