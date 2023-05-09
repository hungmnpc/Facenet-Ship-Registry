package com.facenet.shipsregistry.modal;


import com.facenet.shipsregistry.entity.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MeasurementTM7DTO {

    private Long id;

    private String item;
   // private FrameNumber frameNumber;
    private DetailMeasurementDTO upperPart;

    private DetailMeasurementDTO midPart;

    private DetailMeasurementDTO lowerPart;
}