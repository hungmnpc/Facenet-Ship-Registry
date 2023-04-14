package com.facenet.shipsregistry.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

/**
 * @author: hungdinh
 * Date created: 11/04/2023
 */

@Entity
@Table(name = "certificate_of_operator")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Certificate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "certificate_organization")
    private String certificateOrganization;

    @Column(name = "certificate_no", length = 16, unique = true)
    private String certificateNo;

    @Column(name = "valid_start_date")
    @Temporal(TemporalType.DATE)
    private LocalDate validStartDate;

    @Column(name = "valid_end_date")
    @Temporal(TemporalType.DATE)
    private LocalDate validEndDate;

    @OneToMany(mappedBy = "certificate", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private List<GeneralParticulars> generalParticularsList;
}