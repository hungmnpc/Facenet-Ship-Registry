package com.facenet.shipsregistry.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.engine.internal.Cascade;

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

    @Column(name = "name")
    private String name;

    @Column(name = "no_or_letter")
    private String noOrLetter;

    @ManyToOne
    @JoinColumn(name = "structural_description_id")
    private StructuralDescriptionTM6 structuralDescriptionTM6;

    @OneToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "measurement_detail_id")
    private DetailMeasurement measurementDetail;
}
