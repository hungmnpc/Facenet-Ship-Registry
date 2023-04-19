package com.facenet.shipsregistry.modal;


import com.facenet.shipsregistry.entity.DetailMeasurement;
import com.facenet.shipsregistry.entity.FormTM6;
import com.facenet.shipsregistry.entity.FormTM7;
import com.facenet.shipsregistry.entity.MeasurementTM6;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StructuralDescriptionTM6DTO {

    private Long id;

    private String structuralDescription;

    private List<MeasurementTM6DTO> measurementTM6List;
}