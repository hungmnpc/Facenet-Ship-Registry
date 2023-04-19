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
public class FormTM3DTO implements FormDTO{

    private String type = "FORM TM3";

    private Long id;

    private String firstFrameNo;

    private String secondFrameNo;

    private String thirdFrameNo;

    private List<MeasurementTM3DTO> measurementTM3DTOList;
}
