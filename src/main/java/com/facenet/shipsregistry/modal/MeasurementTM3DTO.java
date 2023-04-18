package com.facenet.shipsregistry.modal;

import lombok.*;

/**
 * @author: CongTu
 * Date created: 15/04/2023
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MeasurementTM3DTO {

    private Long id;

    private String structuralMember;

    private String noOrLetter;

    private DetailMeasurementDTO firstTransverseSectionMeasurementDetail;

    private DetailMeasurementDTO secondTransverseSectionMeasurementDetail;

    private DetailMeasurementDTO thirdTransverseSectionMeasurementDetail;
}
