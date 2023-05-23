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
     * @param request
     * @return
     */
    public String updatePassword(ChangePasswordRequest request);

    /**
     *
     * @param username
     * @return
     */
    public boolean isExists(String username);

    /**
     *
     * @param username
     * @param roleName
     */
    public void setRoleForUser(String username, String roleName);
}
