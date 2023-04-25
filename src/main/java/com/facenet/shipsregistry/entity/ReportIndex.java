package com.facenet.shipsregistry.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

/**
 * @author: hungdinh
 * Date created: 11/04/2023
 */

@Entity
@AllArgsConstructor
@Table(name = "report_index")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class ReportIndex {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "part_index")
    private Integer partIndex;

    @Column(name = "item")
    private String item;

    @ManyToOne
    @JoinColumn(name = "general_particulars_id")
    private GeneralParticulars generalParticulars;

    @OneToMany(mappedBy = "reportIndex", cascade = CascadeType.ALL)
    private List<FormTM1> formTM1List;
    @OneToMany(mappedBy = "reportIndex", cascade = CascadeType.ALL)
    private List<FormTM5> formTM5List;

    @OneToMany(mappedBy = "reportIndex", cascade = CascadeType.ALL)
    private List<FormTM2> formTM2List;

    @OneToMany(mappedBy = "reportIndex", cascade = CascadeType.ALL)
    private List<FormTM4> formTM4List;

    public ReportIndex(Integer partIndex, String item) {
        this.partIndex = partIndex;
        this.item = item;
    }
}