package com.facenet.shipsregistry.request;

import lombok.*;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

/**
 * @author: hungdinh
 * Date created: 11/04/2023
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class GeneralParticularRequestBody {

    private ShipInfoRequestBody ship;

    private String reportNo;

    private String surveyorInfo;

    private String certificateNo;

    private String measurementEquipmentInfo;

    private String surveyType;

    private String placeOfMeasurement;

    private LocalDate firstDateOfMeasurement;

    private LocalDate lastDateOfMeasurement;

    private String nameOfOperator;
}