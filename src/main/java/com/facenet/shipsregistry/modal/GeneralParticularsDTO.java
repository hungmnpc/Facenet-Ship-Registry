package com.facenet.shipsregistry.modal;

import jakarta.persistence.Column;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.*;

import java.time.LocalDate;

/**
 * @author: hungdinh
 * Date created: 11/04/2023
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class GeneralParticularsDTO {

    private Long id;

    private ShipDTO shipInfo;

    private CertificateDTO certificateDTO;

    private String reportNo;

    private String surveyorInfo;

    private String surveyType;

    private String measurementEquipmentInfo;

    private String placeOfMeasurement;

    private LocalDate firstDateOfMeasurement;

    private LocalDate lastDateOfMeasurement;

    private String nameOfOperator;

    private Boolean locked;
}