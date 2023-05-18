package com.facenet.shipsregistry.service;

import com.facenet.shipsregistry.modal.RoleDTO;
import com.facenet.shipsregistry.modal.UserDTO;
import com.facenet.shipsregistry.request.ChangePasswordRequest;
import com.facenet.shipsregistry.request.NewUserRequest;

/**
 * @author: hungdinh
 * Date created: 11/04/2023
 */
public interface AuthService {

    /**
     * @param roleName
     * @return
     */
    public RoleDTO saveNewRole(String roleName);

    public UserDTO saveNewUser(NewUserRequest request);

    /**
     *
     * @param userId
     * @return
     */
    public String deleteUser(Long userId);

    /**
     *
     * @param newPassword
     * @return
     */
    public String updatePassword(ChangePasswordRequest request);
}
