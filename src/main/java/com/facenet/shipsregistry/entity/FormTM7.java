package com.facenet.shipsregistry.entity;


import com.facenet.shipsregistry.request.FormTM7RequestBody;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "form_TM7")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class FormTM7 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String code;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "formTM7", cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    private List<FrameNumber> frameNumber;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "report_id")
    private ReportIndex reportIndex;

    private String displayName;

    public FormTM7(String name, String code) {
        this.name = name;
        this.code = code;
    }
    public void update(FormTM7RequestBody requestBody) {
        this.setName(requestBody.getName());
        this.setCode(requestBody.getCode());
    }

    @Column(name = "form_index")
    private Integer formIndex;
}
