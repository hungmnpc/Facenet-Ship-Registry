package com.facenet.shipsregistry.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

/**
 * @author: CongTu
 * Date created: 14/04/2023
 */
@Entity
@Table(name = "form_TM3")
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class FormTM3 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "1st_frame_no")
    private String firstFrameNo;

    @Column(name = "2nd_frame_no")
    private String secondFrameNo;

    @Column(name = "3rd_frame_no")
    private String thirdFrameNo;

    @OneToMany(mappedBy = "formTM3", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private List<MeasurementTM3> measurementTM3List;

    @ManyToOne
    @JoinColumn(name = "report_id")
    private ReportIndex reportIndex;

    private String code;
}
