package com.facenet.shipsregistry.request;

import com.facenet.shipsregistry.modal.MeasurementTM1DTO;
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
public class FormTM1RequestBody {

    private String strakePosition;

    private List<MeasurementTM1RequestBody> measurementTM1List;
}