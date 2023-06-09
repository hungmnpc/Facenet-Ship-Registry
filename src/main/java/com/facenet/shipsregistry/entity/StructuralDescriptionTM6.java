package com.facenet.shipsregistry.entity;

import lombok.*;
import javax.persistence.*;
import java.util.List;

/**
 * @author: CongTu
 * Date created: 14/04/2023
 */

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

    @Column(name = "structural_description_title")
    private String structuralDescriptionTitle;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "form_TM6_id")
    private FormTM6 formTM6;

    @OneToMany(mappedBy = "structuralDescriptionTM6", cascade = {CascadeType.ALL})
    private List<MeasurementTM6> measurementTM6List;
}
