package com.facenet.shipsregistry.entity;

import com.facenet.shipsregistry.request.FormTM4RequestBody;
import lombok.*;
import javax.persistence.*;
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

    private String code;

    @Column(name = "tank_description")
    private String tankDescription;

    @Column(name = "location_of_structure")
    private String locationOfStructure;

    @Column(name = "form_index")
    private Integer formIndex;

    @OneToMany(mappedBy = "formTM4", cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    private List<StructuralMemberTM4> structuralMemberTM4List;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "report_id")
    private ReportIndex reportIndex;

    public FormTM4(String tankDescription, String locationOfStructure, String code) {
        this.tankDescription = tankDescription;
        this.locationOfStructure = locationOfStructure;
        this.code = code;
    }
  
    public void update(FormTM4RequestBody requestBody) {
        this.setTankDescription(requestBody.getTankDescription());
        this.setLocationOfStructure(requestBody.getLocationOfStructure());
        this.setCode(requestBody.getCode());
    }

}
