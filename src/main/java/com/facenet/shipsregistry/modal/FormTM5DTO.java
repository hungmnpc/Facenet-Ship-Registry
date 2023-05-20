package com.facenet.shipsregistry.modal;


import com.facenet.shipsregistry.entity.MeasurementTM5;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

/**
 * @author: hungdinh
 * Date created: 12/04/2023
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FormTM5DTO extends FormDTO{

    private String type = "TM5";

    private String locationOfStructure;

    private String tankHolDescription;

    private String frameNo;

    private List<StructuralTM5DTO> structuralTM5List;


}