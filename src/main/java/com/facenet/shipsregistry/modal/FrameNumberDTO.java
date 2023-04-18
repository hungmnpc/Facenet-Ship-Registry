package com.facenet.shipsregistry.modal;


import com.facenet.shipsregistry.entity.FormTM7;
import com.facenet.shipsregistry.entity.MeasurementTM7;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FrameNumberDTO {

    private Long id;
    private String name;
    private FormTM7 formTM7;
    private List<MeasurementTM7> measurementTM7List;

}