package com.facenet.shipsregistry.request;

import com.facenet.shipsregistry.modal.RoleDTO;
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
public class NewUserRequest {

    private String username;

    private String password;

    private String firstName;

    private String lastName;

    private String phoneNumber;
}