package com.facenet.shipsregistry.request;

import com.facenet.shipsregistry.entity.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MeasurementTM7RequestBody {

    private String name;
    private FrameNumber frameNumber;
    private DetailMeasurement upperPart;
    private DetailMeasurement midPart;
    private DetailMeasurement lowerPart;
}