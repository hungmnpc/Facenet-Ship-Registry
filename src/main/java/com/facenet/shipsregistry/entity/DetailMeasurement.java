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

//    @OneToOne(mappedBy = "firstTransverseSectionMeasurementDetailTM2",
//            cascade = {CascadeType.MERGE, CascadeType.PERSIST})
//    private MeasurementTM2 measurementTM2FirstTranList;
//
//    @OneToOne(mappedBy = "secondTransverseSectionMeasurementDetailTM2",
//            cascade = {CascadeType.MERGE, CascadeType.PERSIST})
//    private MeasurementTM2 measurementTM2SecondTranList;
//
//    @OneToOne(mappedBy = "thirdTransverseSectionMeasurementDetailTM2",
//            cascade = {CascadeType.MERGE, CascadeType.PERSIST})
//    private MeasurementTM2 measurementTM2ThirdTranList;
//
//    @OneToOne(mappedBy = "firstFrameNoTM2",
//            cascade = {CascadeType.MERGE, CascadeType.PERSIST})
//    private MeasurementTM2 measurementTM2FirstFrameNoList;
//
//    @OneToOne(mappedBy = "secondFrameNoTM2",
//            cascade = {CascadeType.MERGE, CascadeType.PERSIST})
//    private MeasurementTM2 measurementTM2SecondFrameNoList;
//
//    @OneToOne(mappedBy = "thirdFrameNoTM2",
//            cascade = {CascadeType.MERGE, CascadeType.PERSIST})
//    private MeasurementTM2 measurementTM2ThirdFrameNoList;
//
//    @OneToOne(mappedBy = "firstTransverseSectionMeasurementDetailTM3",
//            cascade = {CascadeType.MERGE, CascadeType.PERSIST})
//    private MeasurementTM3 measurementTM3FirstTranList;
//
//    @OneToOne(mappedBy = "secondTransverseSectionMeasurementDetailTM3",
//            cascade = {CascadeType.MERGE, CascadeType.PERSIST})
//    private MeasurementTM3 measurementTM3SecondTranList;
//
//    @OneToOne(mappedBy = "thirdTransverseSectionMeasurementDetailTM3",
//            cascade = {CascadeType.MERGE, CascadeType.PERSIST})
//    private MeasurementTM3 measurementTM3ThirdTranList;
//
//    @OneToOne(mappedBy = "firstFrameNoTM3",
//            cascade = {CascadeType.MERGE, CascadeType.PERSIST})
//    private MeasurementTM3 measurementTM3FirstFrameNoList;
//
//    @OneToOne(mappedBy = "secondFrameNoTM3",
//            cascade = {CascadeType.MERGE, CascadeType.PERSIST})
//    private MeasurementTM3 measurementTM3SecondFrameNoList;
//
//    @OneToOne(mappedBy = "thirdFrameNoTM3",
//            cascade = {CascadeType.MERGE, CascadeType.PERSIST})
//    private MeasurementTM3 measurementTM3ThirdFrameNoList;
//
//    @OneToOne(mappedBy = "measurementDetailId",
//            cascade = {CascadeType.MERGE, CascadeType.PERSIST})
//    private StructuralMemberDetailsTM4 structuralMemberDetailsTM4;
}