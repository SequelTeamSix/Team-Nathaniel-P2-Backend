package com.teamnathaniel.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Console")
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString

public class Console {

    @Id
    private int consoleId;
    @Column
    private String consoleName;
    @Column
    private String releaseDate;
    @Column
    private String logo;
    @Column
    private String picture;


    @ManyToMany(mappedBy = "console")
    @JsonIgnoreProperties({"console", "series","gameFeatures"})
    private List<Game> game;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "consoleFeatureId")
    @JsonIgnoreProperties("console")
    private List<ConsoleFeature> consoleFeature;
}
