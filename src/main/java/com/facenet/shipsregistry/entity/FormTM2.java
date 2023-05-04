package com.facenet.shipsregistry.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: CongTu
 * Date created: 14/04/2023
 */
@Entity
@Table(name = "form_TM2")
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class FormTM2 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    private String code;

    @OneToMany(mappedBy = "formTM2", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private List<MeasurementTM2> measurementTM2List;

    private String firstFrameNoTM2;

    private String secondFrameNoTM2;

    private String thirdFrameNoTM2;

    public FormTM2(String name, String firstFrameNoTM2, String secondFrameNoTM2, String thirdFrameNoTM2) {
        this.name = name;
        this.firstFrameNoTM2 = firstFrameNoTM2;
        this.secondFrameNoTM2 = secondFrameNoTM2;
        this.thirdFrameNoTM2 = thirdFrameNoTM2;
        this.measurementTM2List = new ArrayList<>();
        this.id = null;
    }

    @ManyToOne
    @JoinColumn(name = "report_id")
    private ReportIndex reportIndex;
}
