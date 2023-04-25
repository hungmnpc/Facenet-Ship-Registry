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

    @Column(name = "structural_members")
    private String structuralMembers;

    @Column(name = "location_of_structure")
    private String locationOfStructure;

    @OneToMany(mappedBy = "formTM6", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private List<StructuralDescriptionTM6> structuralDescriptionTM6List;

    @ManyToOne
    @JoinColumn(name = "report_id")
    private ReportIndex reportIndex;

    public FormTM6(String structuralMembers, String locationOfStructure) {
        this.structuralMembers = structuralMembers;
        this.locationOfStructure = locationOfStructure;
    }
}
