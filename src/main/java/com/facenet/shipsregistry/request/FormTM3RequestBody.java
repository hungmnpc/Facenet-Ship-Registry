package com.facenet.shipsregistry.request;

import lombok.*;

import java.util.List;

/**
 * @author: CongTu
 * Date created: 17/04/2023
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class FormTM3RequestBody {

    private String firstFrameNo;

    private String secondFrameNo;

    private String thirdFrameNo;

    private List<MeasurementTM3RequestBody> measurementTM3List;
}
