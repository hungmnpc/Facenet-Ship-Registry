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
public class FormTM5DTO implements FormDTO{

    private String type = "FORM TM5";

    private Long id;

    private String description;

    private String name;

    private String locationOfStructure;

    private String tankHolDescription;

    private String frameNo;

    private List<MeasurementTM5DTO> measurementTM5List;


}