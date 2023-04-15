package com.facenet.shipsregistry.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "form_TM6")
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class FormTM6 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "description")
    private String description;
    @Column(name = "name")
    private String name;
    @Column(name = "structural_members")
    private String structuralMembers;
    @Column(name = "location_of_structure")
    private String locationOfStructure;
    @OneToMany(mappedBy = "formTM6", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private List<StructuralDescriptionTM6> structuralDescriptionTM6List;

}
