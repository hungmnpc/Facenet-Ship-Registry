package com.facenet.shipsregistry.modal;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

/**
 * @author: hungdinh
 * Date created: 12/04/2023
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ShipDTO {

    private Long ship_id;

    private String name;

    private String imoNumber;

    private String absIdentification;

    private String postOfRegistry;

    private Integer grossTons;

    private Integer deadweight;

    private Date dateOfBuild;
}