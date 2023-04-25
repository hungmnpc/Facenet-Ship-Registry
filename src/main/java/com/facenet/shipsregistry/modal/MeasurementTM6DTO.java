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
public class MeasurementTM6DTO {

    private Long id;

    private String structuralDescription;

    private String item;

    private DetailMeasurementDTO detailMeasurement;
}