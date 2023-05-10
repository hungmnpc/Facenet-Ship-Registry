package com.facenet.shipsregistry.entity;

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

    private String code;
}
