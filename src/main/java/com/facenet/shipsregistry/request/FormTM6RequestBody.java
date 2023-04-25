package com.facenet.shipsregistry.request;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

/**
 * @author: CongTu
 * Date created: 24/04/2023
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class FormTM6RequestBody {

    private String structuralMembers;

    private String locationOfStructure;

    private List<StructuralDescriptionTM6RequestBody> structuralDescriptionTM6List;

}