package com.facenet.shipsregistry.entity;

import jakarta.persistence.*;
import lombok.*;

/**
 * @author: CongTu
 * Date created: 14/04/2023
 */

@Entity
@Table(name = "measurement_TM4")
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class StructuralMemberDetailsTM4 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "structural_member_name")
    private String structuralMemberName;

    @Column(name = "item_name")
    private String itemName;

    @ManyToOne
    @JoinColumn(name = "structural_fr_id")
    private MeasurementTM4 measurementTM4;

    @OneToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "measurement_detail_id")
    private DetailMeasurement measurementDetailId;
}
