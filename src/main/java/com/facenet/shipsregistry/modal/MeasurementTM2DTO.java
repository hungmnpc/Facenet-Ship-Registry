package com.facenet.shipsregistry.modal;

import com.facenet.shipsregistry.entity.DetailMeasurement;
import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
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

    private DetailMeasurementDTO firstTransverseSectionMeasurementDetailTM2;

    private DetailMeasurementDTO secondTransverseSectionMeasurementDetailTM2;

    private DetailMeasurementDTO thirdTransverseSectionMeasurementDetailTM2;
}