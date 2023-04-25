package com.facenet.shipsregistry.request;

import com.facenet.shipsregistry.entity.Ship;
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

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return reportNo;
    }
}