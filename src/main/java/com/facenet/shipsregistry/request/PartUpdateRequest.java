package com.facenet.shipsregistry.request;

import lombok.*;

/**
 * @author: hungdinh
 * Date created: 11/04/2023
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PartUpdateRequest {

    String item;

    Integer partIndex;
}