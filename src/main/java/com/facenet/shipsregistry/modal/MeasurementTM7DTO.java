package com.facenet.shipsregistry.modal;


import com.facenet.shipsregistry.entity.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MeasurementTM7DTO {
    private Long id;
    private FrameNumber frameNumber;
    private String name;
    private DetailMeasurement upperPart;
    private DetailMeasurement midPart;
    private DetailMeasurement lowerPart;
}