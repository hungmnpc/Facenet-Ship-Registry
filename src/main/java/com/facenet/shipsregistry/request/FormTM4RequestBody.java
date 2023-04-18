package com.facenet.shipsregistry.request;

import lombok.*;

/**
 * @author: CongTu
 * Date created: 17/04/2023
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class FormTM4RequestBody {

    private String description;

    private String name;

    private String tankDescription;

    private String locationOfStructure;
}
