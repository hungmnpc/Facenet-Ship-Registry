package com.facenet.shipsregistry.modal;

import com.facenet.shipsregistry.entity.MeasurementTM1;
import jakarta.persistence.*;

/**
 * @author: hungdinh
 * Date created: 11/04/2023
 */
public class DetailMeasurementDTO {

    private Long id;

    private Double originalThickness;

    private Double maxAlwbDim;

    private Double gaugedP;

    private Double gaugedS;
}