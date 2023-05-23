package com.facenet.shipsregistry.entity;


import lombok.*;

import javax.persistence.*;

/**
 * @author: hungdinh
 * Date created: 11/04/2023
 */

@Entity
@Table(name = "sketch")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class Sketch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "value", length = 1000)
    @Lob
    private byte[] value;

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    private String type;

    @Column(name = "form_id")
    private Long formId;

    @Column(name = "form_type")
    private String formType;
}