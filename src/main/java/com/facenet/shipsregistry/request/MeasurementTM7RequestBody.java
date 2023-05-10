package com.facenet.shipsregistry.request;

import com.facenet.shipsregistry.entity.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MeasurementTM7RequestBody {

    private String item;

    private DetailMeasurementRequestBody upperPart;

    private DetailMeasurementRequestBody midPart;

    private DetailMeasurementRequestBody lowerPart;
}