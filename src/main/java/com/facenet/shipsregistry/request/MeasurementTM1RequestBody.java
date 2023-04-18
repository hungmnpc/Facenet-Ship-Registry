package com.facenet.shipsregistry.request;

import com.facenet.shipsregistry.modal.DetailMeasurementDTO;
import lombok.*;

/**
 * @author: hungdinh
 * Date created: 11/04/2023
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MeasurementTM1RequestBody {

    private String platePosition;

    private String noOrLetter;

    private DetailMeasurementRequestBody forwardReadingMeasurementDetail;

    private DetailMeasurementRequestBody afterReadingMeasurementDetail;
}
