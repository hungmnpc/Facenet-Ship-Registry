package com.facenet.shipsregistry.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.engine.internal.Cascade;

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

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "id_frame _number")
    private FrameNumber frameNumber;

    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "upper_part_id")
    private DetailMeasurement upperPart;

    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "mid_part_id")
    private DetailMeasurement midPart;

    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "lower_part_id")
    private DetailMeasurement lowerPart;
}
