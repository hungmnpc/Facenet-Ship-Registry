package com.facenet.shipsregistry.modal;


import com.facenet.shipsregistry.entity.FormTM7;
import com.facenet.shipsregistry.entity.MeasurementTM7;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FrameNumberDTO {

    private Long id;

    private String name;

    private List<MeasurementTM7DTO> measurementTM7DTOList;
}