package com.facenet.shipsregistry.entity;

import jakarta.persistence.*;
import jdk.jfr.Enabled;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    @Column(name = "original_thickness", precision = 10, scale = 2)
    private Double originalThickness;

    @Column(name = "max_alwb_dim", precision = 10, scale = 2)
    private Double maxAlwbDim;

    @Column(name = "gauged_P", precision = 10, scale = 2)
    private Double gaugedP;

    @Column(name = "gauged_S", precision = 10, scale = 2)
    private Double gaugedS;
}