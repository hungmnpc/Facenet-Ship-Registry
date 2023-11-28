package com.facenet.shipsregistry.modal;


import com.facenet.shipsregistry.entity.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MeasurementTM6DTO {

    private Long id;

    private String description;

    private String item;

    private DetailMeasurementDTO detailMeasurement;
}