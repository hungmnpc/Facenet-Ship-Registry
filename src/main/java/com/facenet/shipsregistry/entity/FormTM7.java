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

    @OneToMany(mappedBy = "formTM7", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private List<FrameNumber> frameNumber;

    @ManyToOne
    @JoinColumn(name = "report_id")
    private ReportIndex reportIndex;

    public FormTM7(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
