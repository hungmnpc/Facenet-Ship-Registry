package com.facenet.shipsregistry.entity;

import lombok.*;
import javax.persistence.*;
import java.time.LocalDate;
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
public class GeneralParticulars extends EntityAudit{

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

    @Column(name = "place_of_measurement")
    private String placeOfMeasurement;

    @Column(name = "first_date_of_measurement")
    private LocalDate firstDateOfMeasurement;

    @Column(name = "last_date_of_measurement")
    private LocalDate lastDateOfMeasurement;

    @Column(name = "name_of_operator")
    private String nameOfOperator;

    @OneToMany(mappedBy = "generalParticulars", cascade = {CascadeType.ALL})
    private List<ReportIndex> reportIndexList;

    @Column(name = "locked", nullable = false)
    private Boolean locked = false;

    @Column(name = "measurement_equipment_info")
    @Lob
    private String measurementEquipmentInfo;

    @Column(name = "survey_type")
    private String surveyType;
}