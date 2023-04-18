package com.facenet.shipsregistry.modal;

import lombok.*;

/**
 * @author: CongTu
 * Date created: 15/04/2023
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class FormTM4DTO {

    private Long id;

    private String description;

    private String name;

    private String tankDescription;

    private String locationOfStructure;
}
