package com.facenet.shipsregistry.request;

import com.facenet.shipsregistry.entity.FormTM7;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class FrameNumberRequestBody {

    private String name;

    private List<MeasurementTM7RequestBody> measurementTM7List;
}