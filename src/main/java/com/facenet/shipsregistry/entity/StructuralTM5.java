package com.facenet.shipsregistry.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

/**
 * @author: hungdinh
 * Date created: 11/04/2023
 */

@Entity
@Table(name = "structuralTM5")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StructuralTM5 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "form_TM5_id")
    private FormTM5 formTM5;

    @OneToMany(mappedBy = "structuralTM5", cascade = {CascadeType.ALL})
    private List<MeasurementTM5> measurementTM5List;
}