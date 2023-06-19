package com.facenet.shipsregistry.entity;

import lombok.*;
import javax.persistence.*;

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

    @Column(name = "item")
    private String item;

    @ManyToOne
    @JoinColumn(name = "structuralTM5_id")
    private StructuralTM5 structuralTM5;

    @OneToOne(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @JoinColumn(name = "measurement_detail_id")
    private DetailMeasurement measurementDetail;
}