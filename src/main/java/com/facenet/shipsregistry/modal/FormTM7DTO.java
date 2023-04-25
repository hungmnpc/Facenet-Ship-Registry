package com.facenet.shipsregistry.modal;

import com.facenet.shipsregistry.entity.FrameNumber;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FormTM7DTO extends FormDTO{

    private String type = "FORM TM7";

    private Long id;

    private String name;

    private String description;

    private List<FrameNumberDTO> frameNumber;
}