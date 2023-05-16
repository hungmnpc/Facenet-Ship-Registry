package com.facenet.shipsregistry.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MeasurementTM2RequestBody {


    private String strakePosition;

    private String noOrLetter;

    private DetailMeasurementRequestBody firstTransverseSectionMeasurementDetailTM2;

    private DetailMeasurementRequestBody secondTransverseSectionMeasurementDetailTM2;

    private DetailMeasurementRequestBody thirdTransverseSectionMeasurementDetailTM2;
}
