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
@Builder
public class DetailMeasurementDTO {

    private Long id;

    private Double originalThickness;

    private Double maxAlwbDim;

    private Double gaugedP;

    private Double gaugedS;

    private String percent;
}