package com.facenet.shipsregistry.modal;

import lombok.*;

import java.util.List;

/**
 * @author: CongTu
 * Date created: 15/04/2023
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class FormTM4DTO extends FormDTO{

    private String type = "FORM TM4";

    private String tankDescription;

    private String locationOfStructure;

    private List<StructuralMemberTM4DTO> structuralMemberTM4List;
}