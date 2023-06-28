package com.facenet.shipsregistry.entity;

import lombok.*;
import javax.persistence.*;

@Entity
@Table(name = "measurement_TM7")
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class MeasurementTM7 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "item")
    private String item;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "id_frame_number")
    private FrameNumber frameNumber;

    @OneToOne(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @JoinColumn(name = "upper_part_id")
    private DetailMeasurement upperPart;

    @OneToOne(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @JoinColumn(name = "mid_part_id")
    private DetailMeasurement midPart;

    @OneToOne(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @JoinColumn(name = "lower_part_id")
    private DetailMeasurement lowerPart;
}
