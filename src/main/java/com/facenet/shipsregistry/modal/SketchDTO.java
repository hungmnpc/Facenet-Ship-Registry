package com.facenet.shipsregistry.modal;

import lombok.*;

/**
 * @author: hungdinh
 * Date created: 11/04/2023
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SketchDTO {

    private Long id;

    private String value;

    private String name;

    private String type;

    private Long formId;

    private String formType;
}