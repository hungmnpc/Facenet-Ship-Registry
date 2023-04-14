package com.facenet.shipsregistry.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

/**
 * @author: CongTu
 * Date created: 14/02/2023
 */
@Entity
@Table(name = "form_TM1")
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class FormTM1 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "description")
    private String description;

    @Column(name = "name")
    private String name;

    @Column(name = "strake_position")
    private String strakePosition;

    @OneToMany(mappedBy = "formTM1", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private List<MeasurementTM1> measurementTM1List;
}
