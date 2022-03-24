package com.teamnathaniel.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "ConsoleFeature")
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "consoleFeatureId")
public class ConsoleFeature {

    @Id
    private int consoleFeatureId;
    @Column
    private String title;
    @Column
    private String body;
    //multiplicity relationship for Console
    @ManyToOne(fetch =  FetchType.LAZY)
    @JoinColumn(name = "consoleId")
    private Console console;


}
