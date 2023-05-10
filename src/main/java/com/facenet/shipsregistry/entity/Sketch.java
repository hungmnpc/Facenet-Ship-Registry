package com.facenet.shipsregistry.entity;

import jakarta.persistence.*;
import lombok.*;

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
public class Sketch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "path")
    private String path;

    @Column(name = "type")
    private String type;

    @Column(name = "form_id")
    private Long formId;

    @Column(name = "form_type")
    private String formType;
}