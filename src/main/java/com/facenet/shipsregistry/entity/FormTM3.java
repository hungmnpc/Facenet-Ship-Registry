package com.facenet.shipsregistry.entity;

import com.facenet.shipsregistry.request.FormTM2RequestBody;
import com.facenet.shipsregistry.request.FormTM3RequestBody;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
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

    private String code;

    @Column(name = "1st_frame_no")
    private String firstFrameNo;

    @Column(name = "2nd_frame_no")
    private String secondFrameNo;

    @Column(name = "3rd_frame_no")
    private String thirdFrameNo;

    private String displayName;

    @Column(name = "form_index")
    private Integer formIndex;

    @OneToMany(mappedBy = "formTM3", cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    private List<MeasurementTM3> measurementTM3List;

    public FormTM3( String firstFrameNoTM3, String secondFrameNoTM3, String thirdFrameNoTM3, String code) {
        this.firstFrameNo = firstFrameNoTM3;
        this.secondFrameNo = secondFrameNoTM3;
        this.thirdFrameNo = thirdFrameNoTM3;
        this.measurementTM3List = new ArrayList<>();
        this.code = code;
        this.id = null;
    }
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "report_id")
    private ReportIndex reportIndex;

    public void update(FormTM3RequestBody requestBody) {
        this.setFirstFrameNo(requestBody.getFirstFrameNo());
        this.setSecondFrameNo(requestBody.getSecondFrameNo());
        this.setThirdFrameNo(requestBody.getThirdFrameNo());
        this.setCode(requestBody.getCode());
    }
}
