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
public class FormDTO {

    public Long id;

    private String type;

    private String displayname;

    private String code;

    private Integer formIndex;
}
