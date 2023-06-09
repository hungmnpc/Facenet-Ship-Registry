package com.facenet.shipsregistry.entity;

import lombok.*;
import javax.persistence.*;

import java.util.List;

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
public class MeasurementTM4 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "structural_member")
    private String structuralMember;

    @Column(name = "item")
    private String item;

    @OneToOne(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @JoinColumn(name = "measurement_detail_id")
    private DetailMeasurement detailMeasurement;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "structural_title_id")
    private StructuralMemberTM4 structuralMemberTM4;
}
