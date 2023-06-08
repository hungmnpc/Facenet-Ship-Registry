package com.facenet.shipsregistry.entity;

import lombok.*;
import javax.persistence.*;
import java.time.LocalDate;
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
public class Ship extends EntityAudit{

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
    private String grossTons;

    @Column(name = "deadweight")
    private String deadweight;

    @Column(name = "date_of_build")
    private LocalDate dateOfBuild;

    @Column(name = "classification_society")
    private String classificationSociety;

    @OneToMany(mappedBy = "ship", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private List<GeneralParticulars> generalParticularsList;
}