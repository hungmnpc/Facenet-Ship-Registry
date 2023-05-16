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
public class MeasurementTM2DTO {

    private Long id;

    private String strakePosition;

    private String noOrLetter;

    private DetailMeasurementDTO firstTransverseSectionMeasurementDetailTM2;

    private DetailMeasurementDTO secondTransverseSectionMeasurementDetailTM2;

    private DetailMeasurementDTO thirdTransverseSectionMeasurementDetailTM2;
}