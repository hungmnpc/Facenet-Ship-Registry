package com.facenet.shipsregistry.request;

import lombok.*;

import java.time.LocalDate;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class FormTM6RequestBody {

    private String description;

    private String name;

    private String structuralMembers;

    private String locationOfStructure;

}