package com.facenet.shipsregistry.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

/**
 * @author: CongTu
 * Date created: 14/04/2023
 */
@Entity
@Table(name = "form_TM1")
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class FormTM1 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "strake_position")
    private String strakePosition;

    @OneToMany(mappedBy = "formTM1", fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private List<MeasurementTM1> measurementTM1List;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "report_id")
    private ReportIndex reportIndex;

    @Override
    public String toString() {
        return "FormTM1{" +
                "id=" + id +
                ", strakePosition='" + strakePosition + '\'' +
                ", reportIndex=" + reportIndex +
                '}';
    }
}
