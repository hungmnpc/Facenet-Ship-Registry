package com.facenet.shipsregistry.request;

import com.facenet.shipsregistry.modal.DetailMeasurementDTO;
import lombok.*;

/**
 * @author: hungdinh
 * Date created: 11/04/2023
 */

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class MeasurementTM4RequestBody {

    private String structuralMember;

    private String item;

    private DetailMeasurementRequestBody detailMeasurement;
}
