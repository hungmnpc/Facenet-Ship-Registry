package com.facenet.shipsregistry.modal;

import com.facenet.shipsregistry.entity.MeasurementTM1;
import com.facenet.shipsregistry.entity.ReportIndex;
import jakarta.persistence.*;
import lombok.*;

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
public class FormTM1DTO implements FormDTO {

    private String type = "Form TM1";

    private Long id;

    private String strakePosition;

    private List<MeasurementTM1DTO> measurementTM1DTOList;
}