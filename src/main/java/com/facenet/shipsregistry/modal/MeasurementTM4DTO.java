package com.facenet.shipsregistry.modal;

import lombok.*;

/**
 * @author: CongTu
 * Date created: 15/04/2023
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MeasurementTM4DTO {

    private Long id;

    private String structuralMember;

    private String item;

    private DetailMeasurementDTO detailMeasurement;
}
