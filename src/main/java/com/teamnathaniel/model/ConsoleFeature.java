package com.teamnathaniel.model;

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
//tentatively include json identity, maybe
public class ConsoleFeature {

    @Id
    private int consoleFeatureId;
    @Column
    private String title;
    @Column
    private String body;
    //multiplicity relationship for Console
    @ManyToOne(fetch =  FetchType.EAGER)
    @JoinColumn(name = "consoleId")
    private Console console;


}
