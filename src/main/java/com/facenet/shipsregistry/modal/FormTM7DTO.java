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
public class FormTM7DTO {
    private Long id;
    private String name;
    private String description;
    private List<FrameNumber> frameNumber;
}