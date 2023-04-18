package com.facenet.shipsregistry.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

/**
 * @author: CongTu
 * Date created: 14/04/2023
 */

@Entity
@Table(name = "measurement_TM4")
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class MeasurementTM4 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "form_TM4_id")
    private FormTM4 formTM4;

    @OneToMany(mappedBy = "measurementTM4", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private List<StructuralMemberDetailsTM4> structuralMemberDetailsTM4List;
}
