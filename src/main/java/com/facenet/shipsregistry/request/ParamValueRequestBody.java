package com.facenet.shipsregistry.request;

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
public class ParamValueRequestBody {

    private String param;

    private String value;

    private Integer type;
}