package com.facenet.shipsregistry.request;

import lombok.*;

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
public class StructuralMemberTM4RequestBody {

    private String structuralMemberTitle;

    private List<MeasurementTM4RequestBody> measurementTM4List;
}
