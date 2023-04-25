package com.facenet.shipsregistry.modal;

import lombok.*;

import java.util.List;

/**
 * @author: CongTu
 * Date created: 15/04/2023
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class StructuralMemberTM4DTO {

    private Long id;

    private String structuralMemberTitle;

    private List<MeasurementTM4DTO> measurementTM4DTOList;
}
