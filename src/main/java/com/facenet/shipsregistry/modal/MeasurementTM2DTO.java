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
public class MeasurementTM2DTO {

    private Long id;

    private String strakePosition;

    private String noOrLetter;

    private FormTM2DTO formTM2DTO;

    //Thieu detailmeasurementDTO
}
