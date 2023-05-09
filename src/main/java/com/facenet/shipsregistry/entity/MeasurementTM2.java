package com.facenet.shipsregistry.entity;

import jakarta.persistence.*;
import lombok.*;

/**
 * @author: CongTu
 * Date created: 14/04/2023
 */

@Entity
@Table(name = "measurement_TM2")
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class MeasurementTM2 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "strake_position")
    private String strakePosition;

    @Column(name = "no_or_letter")
    private String noOrLetter;

    @ManyToOne
    @JoinColumn(name = "form_TM2_id")
    private FormTM2 formTM2;

    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "1st_transverse_section_measurement_detail_id_TM2")
    private DetailMeasurement firstTransverseSectionMeasurementDetailTM2;

    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "2nd_transverse_section_measurement_detail_id_TM2")
    private DetailMeasurement secondTransverseSectionMeasurementDetailTM2;

    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "3rd_transverse_section_measurement_detail_id_TM2")
    private DetailMeasurement thirdTransverseSectionMeasurementDetailTM2;
}
