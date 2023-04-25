package com.facenet.shipsregistry.request;

import com.facenet.shipsregistry.entity.DetailMeasurement;
import com.facenet.shipsregistry.entity.FormTM5;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MeasurementTM5RequestBody {

    private String structuralComponentType;
    private String structuralComponent;
    private DetailMeasurementRequestBody measurementDetail;
}