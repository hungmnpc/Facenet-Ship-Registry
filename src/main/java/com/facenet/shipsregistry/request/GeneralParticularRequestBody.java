package com.facenet.shipsregistry.request;

import com.facenet.shipsregistry.entity.Ship;
import jakarta.persistence.Column;
import lombok.*;

/**
 * @author: hungdinh
 * Date created: 11/04/2023
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GeneralParticularRequestBody {

    private ShipInfoRequestBody ship;

    private String reportNo;

    private String surveyorInfo;

    private String certificateNo;

    private String measurementEquipmentInfo;

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return reportNo;
    }
}