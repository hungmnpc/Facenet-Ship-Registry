package com.facenet.shipsregistry.request;

import lombok.*;

/**
 * @author: CongTu
 * Date created: 18/04/2023
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MeasurementTM3RequestBody {

    private String structuralMember;

    private String noOrLetter;

    private DetailMeasurementRequestBody firstTransverseSectionMeasurementDetail;

    private DetailMeasurementRequestBody secondTransverseSectionMeasurementDetail;

    private DetailMeasurementRequestBody thirdTransverseSectionMeasurementDetail;
}
