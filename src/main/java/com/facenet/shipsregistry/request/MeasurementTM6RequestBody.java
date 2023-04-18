package com.facenet.shipsregistry.request;

import com.facenet.shipsregistry.entity.DetailMeasurement;
import com.facenet.shipsregistry.entity.StructuralDescriptionTM6;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MeasurementTM6RequestBody {

    private String name;
    private String noOrLetter;
    private StructuralDescriptionTM6 structuralDescriptionTM6;
    private DetailMeasurement measurementDetail;
}