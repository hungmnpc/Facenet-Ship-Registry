package com.facenet.shipsregistry.entity;

import lombok.*;
import javax.persistence.*;

/**
 * @author: CongTu
 * Date created: 14/04/2023
 */

@Entity
@Table(name = "measurement_TM3")
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class MeasurementTM3 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "structural_member")
    private String structuralMember;

    @Column(name = "no_or_letter")
    private String noOrLetter;

    @ManyToOne
    @JoinColumn(name = "form_TM3_id")
    private FormTM3 formTM3;

    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "1st_transverse_section_measurement_detail_id_TM3")
    private DetailMeasurement firstTransverseSectionMeasurementDetailTM3;

    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "2nd_transverse_section_measurement_detail_id_TM3")
    private DetailMeasurement secondTransverseSectionMeasurementDetailTM3;

    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "3rd_transverse_section_measurement_detail_id_TM3")
    private DetailMeasurement thirdTransverseSectionMeasurementDetailTM3;

}
