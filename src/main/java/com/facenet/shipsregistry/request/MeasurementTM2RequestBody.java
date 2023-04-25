package com.facenet.shipsregistry.request;


import com.facenet.shipsregistry.entity.DetailMeasurement;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
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
