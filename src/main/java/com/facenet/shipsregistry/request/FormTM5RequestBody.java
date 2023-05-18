package com.facenet.shipsregistry.request;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class FormTM5RequestBody {

    private String code;

    private String locationOfStructure;

    private String tankHolDescription;

    private String frameNo;

    private List<StructuralTM5RequestBody> structuralTM5List;
}