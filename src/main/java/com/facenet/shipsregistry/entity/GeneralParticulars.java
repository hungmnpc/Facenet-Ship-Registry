package com.facenet.shipsregistry.entity;

import jakarta.persistence.*;
import lombok.*;

/**
 * @author: hungdinh
 * Date created: 11/04/2023
 */

@Entity
@Table(name = "general_particulars")
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class GeneralParticulars {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

}