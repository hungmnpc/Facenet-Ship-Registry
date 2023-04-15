package com.facenet.shipsregistry.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "form_TM5")
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class FormTM5 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "description")
    private String description;
    @Column(name = "name")
    private String name;
    @Column(name = "location_of_structure")
    private String locationOfStructure;
    @Column(name = "tank_hold_description")
    private String tankHolDescription;
    @Column(name = "frame_no")
    private String frameNo;
    @OneToMany(mappedBy = "formTM5", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private List<MeasurementTM5> measurementTM5List;

}
