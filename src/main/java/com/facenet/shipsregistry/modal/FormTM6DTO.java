package com.facenet.shipsregistry.modal;

import com.facenet.shipsregistry.entity.StructuralDescriptionTM6;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class FormTM6DTO extends FormDTO{

    private String type = "TM6";

    private String structuralMembers;

    private String displayName;

    private String locationOfStructure;

    private List<StructuralDescriptionTM6DTO> structuralDescriptionTM6List;
}