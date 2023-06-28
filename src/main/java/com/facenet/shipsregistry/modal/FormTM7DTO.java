package com.facenet.shipsregistry.modal;

import com.facenet.shipsregistry.entity.FrameNumber;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FormTM7DTO extends FormDTO{

    private String type = "TM7";

    private String name;

    private String displayName;

    private List<FrameNumberDTO> frameNumberList;
}