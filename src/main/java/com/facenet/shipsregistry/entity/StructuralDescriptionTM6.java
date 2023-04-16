package com.facenet.shipsregistry.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "structural_description_TM6")
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class StructuralDescriptionTM6 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "structural_description")
    private String structuralDescription;

    @ManyToOne
    @JoinColumn(name = "form_TM6_id")
    private FormTM6 formTM6;
    @OneToMany(mappedBy = "structuralDescriptionTM6", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private List<MeasurementTM6> measurementTM6List;
}