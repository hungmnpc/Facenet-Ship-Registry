package com.facenet.shipsregistry.entity;

import lombok.*;
import javax.persistence.*;

@Entity
@Table(name = "measurement_TM6")
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class MeasurementTM6 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "description")
    private String description;

    @Column(name = "item")
    private String item;

    @OneToOne(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @JoinColumn(name = "measurement_detail_id")
    private DetailMeasurement detailMeasurement;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "structural_title_id")
    private StructuralDescriptionTM6 structuralDescriptionTM6;
}