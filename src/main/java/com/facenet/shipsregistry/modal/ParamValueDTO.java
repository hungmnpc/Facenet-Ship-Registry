package com.facenet.shipsregistry.modal;

import com.facenet.shipsregistry.entity.ParamType;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
public class ParamValueDTO {
    private Long id;

    private String param;

    private String value;

    private String type;
}