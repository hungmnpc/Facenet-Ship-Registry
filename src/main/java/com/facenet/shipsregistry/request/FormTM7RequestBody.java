package com.facenet.shipsregistry.request;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class FormTM7RequestBody {

    private String description;

    private String name;
    private List<MeasurementTM7RequestBody> measurementTM7List;
    }