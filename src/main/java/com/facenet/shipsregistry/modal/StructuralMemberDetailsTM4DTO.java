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
public class StructuralMemberDetailsTM4DTO {

    private Long id;

    private String structuralMemberName;

    private String itemName;

    private MeasurementTM4DTO measurementTM4DTO;
}
