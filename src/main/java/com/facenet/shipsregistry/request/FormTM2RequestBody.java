package com.facenet.shipsregistry.request;

import lombok.*;

import java.util.List;

/**
 * @author: CongTu
 * Date created: 17/04/2023
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class FormTM2RequestBody {

    private String name;

    private String firstFrameNoTM2;

    private String secondFrameNoTM2;

    private String thirdFrameNoTM2;

    List<MeasurementTM2RequestBody> measurementTM2List;
}
