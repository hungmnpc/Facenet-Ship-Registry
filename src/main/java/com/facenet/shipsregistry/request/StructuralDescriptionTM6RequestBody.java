package com.facenet.shipsregistry.request;

import com.facenet.shipsregistry.entity.FormTM6;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class StructuralDescriptionTM6RequestBody {

    private String structuralDescription;
    private FormTM6 formTM6;
}