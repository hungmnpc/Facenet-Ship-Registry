package com.facenet.shipsregistry.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

/**
 * @author: hungdinh
 * Date created: 11/04/2023
 */

@Entity
@Table(name = "general_particulars")
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class GeneralParticulars {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ship_id")
    private Ship ship;

    @Column(name = "report_number", length = 100, unique = true)
    private String reportNo;

    @Column(name = "surveyor_info", length = 100)
    private String surveyorInfo;

    @ManyToOne
    @JoinColumn(name = "certificate_id")
    private Certificate certificate;

    @OneToMany(mappedBy = "generalParticulars", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private List<ReportIndex> reportIndexList;

    @Column(name = "lock", nullable = false)
    private Boolean lock = false;

    @Column(name = "measurement_equipment_info")
    @Lob
    private String measurementEquipmentInfo;
}