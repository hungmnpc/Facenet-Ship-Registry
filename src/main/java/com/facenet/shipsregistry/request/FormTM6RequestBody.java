package com.facenet.shipsregistry.request;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

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
    private List<MeasurementTM6RequestBody> measurementTM6List;
}