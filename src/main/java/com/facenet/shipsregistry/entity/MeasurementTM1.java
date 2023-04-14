package com.facenet.shipsregistry.entity;

import jakarta.persistence.*;
import lombok.*;

/**
 * @author: CongTu
 * Date created: 14/04/2023
 */

@Entity
@Table(name = "measurement_TM1")
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor

public class MeasurementTM1 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "plate_position")
    private String platePosition;

    @Column(name = "no_or_letter")
    private String noOrLetter;
    @ManyToOne
    @JoinColumn(name = "form_TM1_id")
    private Long formTM1Id;

    @Column(name = "forward_reading_measurement_detail_id")
    private Long forwardReadingMeasurementDetailId;

    @Column(name = "after_reading_measurement_detail_id")
    private Long afterReadingMeasurementDetailId;
}
