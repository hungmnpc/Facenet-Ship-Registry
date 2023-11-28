package com.facenet.shipsregistry.modal;

import lombok.*;
import lombok.experimental.SuperBuilder;

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
@SuperBuilder
public class FormTM3DTO extends FormDTO{

    private String type = "TM3";

    private String firstFrameNo;

    private String secondFrameNo;

    private String thirdFrameNo;

    private List<MeasurementTM3DTO> measurementTM3DTOList;
}