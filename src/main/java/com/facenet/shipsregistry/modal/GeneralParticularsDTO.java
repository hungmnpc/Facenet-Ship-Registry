package com.facenet.shipsregistry.modal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author: hungdinh
 * Date created: 11/04/2023
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GeneralParticularsDTO {

    private Long id;

    private ShipDTO shipInfo;

    private CertificateDTO certificateDTO;

    private String reportNo;

    private String surveyorInfo;

    private String measurementEquipmentInfo;
}