package com.facenet.shipsregistry.entity;

import jakarta.persistence.*;
import lombok.*;

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


}