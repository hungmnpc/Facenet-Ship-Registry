package com.facenet.shipsregistry.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.engine.internal.Cascade;

/**
 * @author: CongTu
 * Date created: 14/04/2023
 */

@Entity
@Table(name = "measurement_TM5")
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class MeasurementTM5 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "structural_component_type")
    private String structuralComponentType;

    @Column(name = "structural_component_no")
    private String structuralComponent;

    @ManyToOne
    @JoinColumn(name = "form_TM5_id")
    private FormTM5 formTM5;

    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "measurement_detail_id")
    private DetailMeasurement measurementDetail;
}