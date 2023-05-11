package com.facenet.shipsregistry.entity;

import com.facenet.shipsregistry.request.FormTM2RequestBody;
import com.facenet.shipsregistry.request.FormTM7RequestBody;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

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

    @Column(name = "description")
    private String description;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "formTM7", cascade = {CascadeType.ALL})
    private List<FrameNumber> frameNumber;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "report_id")
    private ReportIndex reportIndex;

    public FormTM7(String name, String description, String code) {
        this.name = name;
        this.description = description;
        this.code = code;
    }
    public void update(FormTM7RequestBody requestBody) {
        this.setName(requestBody.getName());
        this.setDescription(requestBody.getDescription());
        this.setCode(requestBody.getCode());
    }

    private String code;

    @Column(name = "form_index")
    private Integer formIndex;
}
