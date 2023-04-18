package com.facenet.shipsregistry.request;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class FormTM7RequestBody {

    private String description;

    private String name;
    }