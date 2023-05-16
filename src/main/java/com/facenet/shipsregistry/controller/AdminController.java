package com.facenet.shipsregistry.controller;

import com.facenet.shipsregistry.modal.RoleDTO;
import com.facenet.shipsregistry.modal.UserDTO;
import com.facenet.shipsregistry.request.NewUserRequest;
import com.facenet.shipsregistry.service.AuthService;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author: hungdinh
 * Date created: 11/04/2023
 */

@RestController
@RequestMapping("/admin")
@Slf4j
public class AdminController {

    @Autowired
    private AuthService authService;

    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        try {
            String response = authService.deleteUser(id);
            return ResponseEntity.ok().body(response);
        } catch (Exception exception) {
            log.info(exception.getMessage());
            exception.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping("/users")
    public ResponseEntity<?> saveNewUser(@RequestBody NewUserRequest request) {
        try {
            UserDTO userDTO = authService.saveNewUser(request);
            if (userDTO != null) {
                return ResponseEntity.ok(userDTO);
            } else {
                return ResponseEntity.badRequest().build();
            }
        } catch (Exception exception) {
            log.error(exception.getMessage());
            exception.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping("/roles")
    public ResponseEntity<?> saveNewRole(@RequestBody String roleName) {
        try {
            RoleDTO roleDTO = authService.saveNewRole(roleName);
            if (roleDTO != null) {
                return ResponseEntity.ok(roleDTO);
            } else {
                return ResponseEntity.badRequest().build();
            }
        } catch (Exception exception) {
            log.error(exception.getMessage());
            exception.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }
}