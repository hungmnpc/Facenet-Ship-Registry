package com.facenet.shipsregistry.entity;

import jakarta.persistence.*;
import jdk.jfr.Enabled;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * @author: hungdinh
 * Date created: 11/04/2023
 */

@Entity
@Table(name = "detail_measurement")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DetailMeasurement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "original_thickness", columnDefinition = "Decimal(10,2)")
    private Double originalThickness;

    @Column(name = "max_alwb_dim", columnDefinition = "Decimal(10,2)")
    private Double maxAlwbDim;

    @Column(name = "gauged_P", columnDefinition = "Decimal(10,2)")
    private Double gaugedP;

    @Column(name = "gauged_S", columnDefinition = "Decimal(10,2)")
    private Double gaugedS;

    @OneToOne(mappedBy = "forwardReadingMeasurementDetail",
            cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private MeasurementTM1 measurementTM1ForwardList;

    @OneToOne(mappedBy = "afterReadingMeasurementDetail",
            cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private MeasurementTM1 measurementTM1AfterList;
    @OneToOne(mappedBy = "measurement_detail_id",
            cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private MeasurementTM5 measurementTM5;
}