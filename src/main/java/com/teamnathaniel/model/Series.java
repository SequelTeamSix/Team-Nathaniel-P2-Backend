package com.teamnathaniel.model;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Series")
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString

public class Series {

    @Id
    private int seriesId;
    @Column
    private String name;
    @Column
    private String logo;
    @Column
    private String description;


    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable
    @JsonIgnoreProperties("series")
    private List<Character> character;

    @OneToMany(mappedBy = "series")
    @JsonIgnoreProperties({"console", "gameFeatures", "series"})
    private List<Game> game;
}
