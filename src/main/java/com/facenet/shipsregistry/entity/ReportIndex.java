package com.facenet.shipsregistry.entity;

import lombok.*;

import javax.persistence.*;
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

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "general_particulars_id")
    private GeneralParticulars generalParticulars;

    @OneToMany(mappedBy = "reportIndex", fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    private List<FormTM1> formTM1List;

    @OneToMany(mappedBy = "reportIndex", fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    private List<FormTM5> formTM5List;

    @OneToMany(mappedBy = "reportIndex", fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    private List<FormTM2> formTM2List;

    @OneToMany(mappedBy = "reportIndex", fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    private List<FormTM4> formTM4List;

    @OneToMany(mappedBy = "reportIndex", fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    private List<FormTM7> formTM7List;

    @OneToMany(mappedBy = "reportIndex", fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    private List<FormTM6> formTM6List;

    @OneToMany(mappedBy = "reportIndex", fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    private List<FormTM3> formTM3List;

    /**
     *
     * @param partIndex
     * @param item
     */
    public ReportIndex(Integer partIndex, String item) {
        this.partIndex = partIndex;
        this.item = item;
    }

    /**
     *
     * @return
     */
    public Integer currentFormIndex() {
        return getFormTM1List().size() + getFormTM2List().size()
                +getFormTM3List().size() + getFormTM4List().size()
                + getFormTM5List().size() + getFormTM6List().size()
                + getFormTM7List().size() + 1;
    }
}