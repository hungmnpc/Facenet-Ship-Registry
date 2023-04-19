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

    private String description;

    private String name;

    private String locationOfStructure;

    private String tankHolDescription;
    private String frameNo;
    private List<MeasurementTM5RequestBody> measurementTM5List;
}