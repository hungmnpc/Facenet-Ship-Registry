package com.facenet.shipsregistry.modal;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

/**
 * @author: hungdinh
 * Date created: 11/04/2023
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@SuperBuilder
public class FormTM1DTO extends FormDTO {

    private String type = "TM1";

    private String strakePosition;

    private List<MeasurementTM1DTO> measurementTM1DTOList;
}