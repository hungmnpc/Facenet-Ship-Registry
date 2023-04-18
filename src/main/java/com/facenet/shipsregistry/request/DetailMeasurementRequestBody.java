package com.facenet.shipsregistry.request;

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
public class DetailMeasurementRequestBody {

    private Double originalThickness;

    private Double maxAlwbDim;

    private Double gaugedP;

    private Double gaugedS;
}