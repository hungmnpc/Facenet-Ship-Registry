package com.facenet.shipsregistry.entity;

import lombok.*;
import javax.persistence.*;
import java.util.List;

/**
 * @author: CongTu
 * Date created: 14/04/2023
 */

@Entity
@Table(name = "structural_member_TM4")
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class StructuralMemberTM4 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "structural_member_title")
    private String structuralMemberTitle;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "form_tm4_id")
    private FormTM4 formTM4;

    @OneToMany(mappedBy = "structuralMemberTM4",cascade = {CascadeType.ALL})
    private List<MeasurementTM4> measurementTM4List;
}
