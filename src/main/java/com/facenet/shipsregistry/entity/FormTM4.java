package com.facenet.shipsregistry.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

/**
 * @author: CongTu
 * Date created: 14/04/2023
 */
@Entity
@Table(name = "form_TM4")
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class FormTM4 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tank_description")
    private String tankDescription;

    @Column(name = "location_of_structure")
    private String locationOfStructure;

    @OneToMany(mappedBy = "formTM4", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private List<StructuralMemberTM4> structuralMemberTM4List;

    @ManyToOne
    @JoinColumn(name = "report_id")
    private ReportIndex reportIndex;

    public FormTM4(String tankDescription, String locationOfStructure) {
        this.tankDescription = tankDescription;
        this.locationOfStructure = locationOfStructure;
    }
}
