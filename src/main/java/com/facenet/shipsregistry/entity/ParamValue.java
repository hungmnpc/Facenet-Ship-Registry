package com.facenet.shipsregistry.entity;

import jakarta.persistence.*;
import lombok.*;

/**
 * @author: hungdinh
 * Date created: 11/04/2023
 */

@Entity
@Table(name = "param_value_custom")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ParamValue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "param_name")
    private String param;

    @Column(name = "param_value")
    private String value;

    @Column(name = "type")
    @Enumerated(EnumType.ORDINAL)
    private ParamType type;

}