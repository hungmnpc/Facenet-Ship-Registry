package com.facenet.shipsregistry.modal;

import com.facenet.shipsregistry.entity.MeasurementTM1;
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
public class DetailMeasurementDTO {

    private Long id;

    private Double originalThickness;

    private Double maxAlwbDim;

    private Double gaugedP;

    private Double gaugedS;

    private String percent;
}