package com.facenet.shipsregistry.modal;


import com.facenet.shipsregistry.entity.MeasurementTM5;
import lombok.*;
import lombok.experimental.SuperBuilder;

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
@ToString
@SuperBuilder
public class FormTM5DTO extends FormDTO{

    private String type = "TM5";

    private String displayName;

    private String locationOfStructure;

    private String tankHolDescription;

    private String frameNo;

    private List<StructuralTM5DTO> structuralTM5List;


}