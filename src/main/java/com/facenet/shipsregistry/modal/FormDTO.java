package com.facenet.shipsregistry.modal;

import lombok.*;
import lombok.experimental.SuperBuilder;

/**
 * @author: hungdinh
 * Date created: 11/04/2023
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@SuperBuilder
public class FormDTO {

    public Long id;

    private String type;

    private String displayname;

    private String code;

    private Integer formIndex;
}
