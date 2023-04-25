package com.facenet.shipsregistry.modal;

import com.facenet.shipsregistry.entity.StructuralDescriptionTM6;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FormTM6DTO extends FormDTO{

    private String type = "FORM TM6";

    private Long id;

    private String structuralMembers;

    private String locationOfStructure;

    private List<StructuralDescriptionTM6DTO> structuralDescriptionTM6List;
}