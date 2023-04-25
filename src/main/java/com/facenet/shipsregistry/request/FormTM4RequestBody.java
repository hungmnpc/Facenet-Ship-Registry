package com.facenet.shipsregistry.request;

import lombok.*;

import java.util.List;

/**
 * @author: CongTu
 * Date created: 17/04/2023
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class FormTM4RequestBody {

    private String tankDescription;

    private String locationOfStructure;

    private List<StructuralMemberTM4RequestBody> structuralMemberTM4List;
}
