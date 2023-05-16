package com.facenet.shipsregistry.modal;

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
public class UserDTO {

    private Long id;

    private String username;

    private String password;

    private String firstName;

    private String lastName;

    private String phoneNumber;

    private RoleDTO roleDTO;
}