package com.facenet.shipsregistry.modal;

import com.facenet.shipsregistry.entity.FormTM5;
import com.facenet.shipsregistry.entity.MeasurementTM5;
import jdk.jfr.Name;
import lombok.*;

import javax.persistence.*;
import java.util.List;

/**
 * @author: hungdinh
 * Date created: 11/04/2023
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class StructuralTM5DTO {

    private Long id;

    private String name;

    private List<MeasurementTM5DTO> measurementTM5List;
}