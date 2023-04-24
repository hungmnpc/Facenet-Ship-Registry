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

    private String description;

    private String item;

    private DetailMeasurementRequestBody detailMeasurement;
}