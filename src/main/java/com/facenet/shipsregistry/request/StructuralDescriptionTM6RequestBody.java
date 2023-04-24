package com.facenet.shipsregistry.request;

import com.facenet.shipsregistry.entity.FormTM6;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class StructuralDescriptionTM6RequestBody {

    private String structuralDescriptionTitle;

    private List<MeasurementTM6RequestBody> measurementTM6List;
}