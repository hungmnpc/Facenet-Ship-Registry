package com.facenet.shipsregistry.entity;

import com.facenet.shipsregistry.request.FormTM2RequestBody;
import lombok.*;
import javax.persistence.*;
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

    @Column(name = "form_index")
    private Integer formIndex;

    @OneToMany(mappedBy = "formTM2", cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    private List<MeasurementTM2> measurementTM2List;

    private String firstFrameNoTM2;

    private String secondFrameNoTM2;

    private String thirdFrameNoTM2;

    public FormTM2(String name, String firstFrameNoTM2, String secondFrameNoTM2, String thirdFrameNoTM2, String code) {
        this.name = name;
        this.firstFrameNoTM2 = firstFrameNoTM2;
        this.secondFrameNoTM2 = secondFrameNoTM2;
        this.thirdFrameNoTM2 = thirdFrameNoTM2;
        this.measurementTM2List = new ArrayList<>();
        this.code = code;
        this.id = null;
    }

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "report_id")
    private ReportIndex reportIndex;

    public void update(FormTM2RequestBody requestBody) {
        this.setName(requestBody.getName());
        this.setFirstFrameNoTM2(requestBody.getFirstFrameNoTM2());
        this.setSecondFrameNoTM2(requestBody.getSecondFrameNoTM2());
        this.setThirdFrameNoTM2(requestBody.getThirdFrameNoTM2());
        this.setCode(requestBody.getCode());
    }
}
