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

/**
 * @author: CongTu
 * Date created: 24/04/2023
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StructuralDescriptionTM6DTO {

    private Long id;

    private String structuralDescriptionTitle;

    private List<MeasurementTM6DTO> measurementTM6DTOList;
}