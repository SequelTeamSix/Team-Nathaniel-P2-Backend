package com.teamnathaniel.model;
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
//tentatively include json identity, maybe
public class Series {

    @Id
    private int seriesId;
    @Column
    private String name;
    @Column
    private String description;


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "characterId")
    private List<Character> character;
}
