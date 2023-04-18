package com.facenet.shipsregistry.modal;

import com.facenet.shipsregistry.entity.DetailMeasurement;
import com.facenet.shipsregistry.entity.FormTM1;
import jakarta.persistence.*;
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
public class MeasurementTM1DTO {

    private Long id;

    private String platePosition;

    private String noOrLetter;

    private DetailMeasurementDTO forwardReadingMeasurementDetail;

    private DetailMeasurementDTO afterReadingMeasurementDetail;
}