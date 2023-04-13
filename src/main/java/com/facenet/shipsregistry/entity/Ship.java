package com.facenet.shipsregistry.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

/**
 * @author: hungdinh
 * Date created: 11/04/2023
 */

@Entity
@Table(name = "ship")
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Ship {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ship_id;

    @Column(name = "name")
    private String name;

    @Column(name = "IMO_number", unique = true)
    private String imoNumber;

    @Column(name = "ABS_identification", unique = true)
    private String absIdentification;

    @Column(name = "post_of_registry")
    private String postOfRegistry;

    @Column(name = "gross_tons")
    private Integer grossTons;

    @Column(name = "deadweight")
    private Integer deadweight;

    @Column(name = "date_of_build")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateOfBuild;

    @OneToMany(mappedBy = "ship", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private List<GeneralParticulars> generalParticularsList;
}