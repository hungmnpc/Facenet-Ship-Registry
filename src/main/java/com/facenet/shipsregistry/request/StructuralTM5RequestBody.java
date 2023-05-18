package com.facenet.shipsregistry.request;

import com.facenet.shipsregistry.entity.FormTM5;
import com.facenet.shipsregistry.entity.MeasurementTM5;
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
public class StructuralTM5RequestBody {
    @Column(name = "name")
    private String name;

    private List<MeasurementTM5RequestBody> measurementTM5List;
}