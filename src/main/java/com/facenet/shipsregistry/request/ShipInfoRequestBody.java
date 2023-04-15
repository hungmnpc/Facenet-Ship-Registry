package com.facenet.shipsregistry.request;

import lombok.*;

import java.time.LocalDate;
import java.util.Date;

/**
 * @author: hungdinh
 * Date created: 11/04/2023
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ShipInfoRequestBody {

    private String name;

    private String imoNumber;

    private String absIdentification;

    private String postOfRegistry;

    private Integer grossTons;

    private Integer deadweight;

    private LocalDate dateOfBuild;
}