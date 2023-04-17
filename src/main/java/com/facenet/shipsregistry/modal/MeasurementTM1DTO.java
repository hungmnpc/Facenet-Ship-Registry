package com.facenet.shipsregistry.modal;

import com.facenet.shipsregistry.entity.DetailMeasurement;
import com.facenet.shipsregistry.entity.FormTM1;
import jakarta.persistence.*;

/**
 * @author: hungdinh
 * Date created: 11/04/2023
 */
public class MeasurementTM1DTO {

    private Long id;

    private String platePosition;

    private String noOrLetter;

    private DetailMeasurementDTO forwardReadingMeasurementDetail;

    private DetailMeasurementDTO afterReadingMeasurementDetail;
}