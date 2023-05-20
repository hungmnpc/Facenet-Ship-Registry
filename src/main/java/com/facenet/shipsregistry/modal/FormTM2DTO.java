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
public class FormTM2DTO extends FormDTO {

    String type = "TM2";

    private String name;

    private List<MeasurementTM2DTO> measurementTM2DTOList;

    private String firstFrameNoTM2;

    private String secondFrameNoTM2;

    private String thirdFrameNoTM2;
}
