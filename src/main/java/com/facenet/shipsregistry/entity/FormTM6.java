package com.facenet.shipsregistry.entity;

import com.facenet.shipsregistry.request.FormTM6RequestBody;
import lombok.*;

import javax.persistence.*;
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

    private String code;

    @Column(name = "structural_members")
    private String structuralMembers;

    @Column(name = "location_of_structure")
    private String locationOfStructure;

    private String displayName;

    @OneToMany(mappedBy = "formTM6", cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    private List<StructuralDescriptionTM6> structuralDescriptionTM6List;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "report_id")
    private ReportIndex reportIndex;

    public FormTM6(String structuralMembers, String locationOfStructure, String code) {
        this.structuralMembers = structuralMembers;
        this.locationOfStructure = locationOfStructure;
        this.code = code;
    }
    public void update(FormTM6RequestBody requestBody) {
        this.setStructuralMembers(requestBody.getStructuralMembers());
        this.setLocationOfStructure(requestBody.getLocationOfStructure());
        this.setCode(requestBody.getCode());
    }

    @Column(name = "form_index")
    private Integer formIndex;
}
