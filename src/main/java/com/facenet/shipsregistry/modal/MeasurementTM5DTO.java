package com.facenet.shipsregistry.modal;


import com.facenet.shipsregistry.entity.DetailMeasurement;
import com.facenet.shipsregistry.entity.FormTM5;
import com.facenet.shipsregistry.entity.FormTM6;
import com.facenet.shipsregistry.entity.FormTM7;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MeasurementTM5DTO {
    private Long id;
    private String structuralComponentType;
    private String structuralComponent;
    private FormTM5 formTM5;
    private DetailMeasurement measurementDetail;
}