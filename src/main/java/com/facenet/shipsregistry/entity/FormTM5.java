package com.facenet.shipsregistry.entity;

import com.facenet.shipsregistry.request.FormTM5RequestBody;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
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

    private String code;

    @Column(name = "location_of_structure")
    private String locationOfStructure;

    @Column(name = "tank_hold_description")
    private String tankHolDescription;

    @Column(name = "frame_no")
    private String frameNo;

    public FormTM5(String locationOfStructure, String tankHolDescription, String frameNo, String code) {
        this.locationOfStructure = locationOfStructure;
        this.tankHolDescription = tankHolDescription;
        this.frameNo = frameNo;
        this.structuralTM5List = new ArrayList<>();
        this.code = code;
        this.id = null;
    }

    @OneToMany(mappedBy = "formTM5", cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    private List<StructuralTM5> structuralTM5List;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "report_id")
    private ReportIndex reportIndex;

    @Column(name = "form_index")
    private Integer formIndex;

    public void update(FormTM5RequestBody requestBody) {
        this.setFrameNo(requestBody.getFrameNo());
        this.setLocationOfStructure(requestBody.getLocationOfStructure());
        this.setTankHolDescription(requestBody.getTankHolDescription());
        this.setCode(requestBody.getCode());
    }

}
