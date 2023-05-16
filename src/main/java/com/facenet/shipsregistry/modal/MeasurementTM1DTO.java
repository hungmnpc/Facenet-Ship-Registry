package com.facenet.shipsregistry.modal;

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