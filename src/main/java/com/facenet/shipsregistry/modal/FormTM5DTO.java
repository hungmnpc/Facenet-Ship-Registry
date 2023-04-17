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
public class FormTM5DTO {

    private Long id;
    private String description;
    private String name;
    private String locationOfStructure;
    private String tankHolDescription;
    private String frameNo;

}