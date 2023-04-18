package com.facenet.shipsregistry.request;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class FormTM5RequestBody {

    private String description;

    private String name;

    private String locationOfStructure;

    private String tankHolDescription;
    private String frameNo;
}