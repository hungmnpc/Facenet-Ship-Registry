package com.facenet.shipsregistry.service;

import com.facenet.shipsregistry.entity.Role;
import com.facenet.shipsregistry.entity.User;
import com.facenet.shipsregistry.modal.RoleDTO;
import com.facenet.shipsregistry.modal.UserDTO;
import com.facenet.shipsregistry.principal.UserPrincipal;
import com.facenet.shipsregistry.repository.RoleRepository;
import com.facenet.shipsregistry.repository.UserRepository;
import com.facenet.shipsregistry.request.ChangePasswordRequest;
import com.facenet.shipsregistry.request.NewUserRequest;
import com.facenet.shipsregistry.utils.MapperUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

/**
 * @author: hungdinh
 * Date created: 11/04/2023
 */

@Service
@Transactional
@Slf4j
public class AuthServiceImpl implements AuthService , UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private MapperUtils mapperUtils;

    @Autowired
    private PasswordEncoder passwordEncoder;


    /**
     * @param roleName
     * @return
     */
    @Override
    public RoleDTO saveNewRole(String roleName) {
        Role role = new Role(null, roleName, new ArrayList<>());
        Role roleSaved = roleRepository.save(role);
        if (roleSaved.getId() > 0) {
            return mapperUtils.roleMapper(roleSaved);
        }
        return null;
    }

    @Override
    public UserDTO saveNewUser(NewUserRequest request) {
        Role role = roleRepository.findRoleByRoleName("ROLE_USER").orElse(new Role(null, "CLIENT", null));
        String passwordEncode = passwordEncoder.encode(request.getPassword());
        User user = new User(null, request.getUsername(), passwordEncode, request.getFirstName(),
                request.getLastName(), request.getPhoneNumber(), role);
        User userSaved = userRepository.save(user);
        if (userSaved.getId() > 0 ) {
            return mapperUtils.userMapper(userSaved);
        }
        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = userRepository.findUserByUsername(username);
        if (user == null) {
            log.warn("User {} not found in the database", username);
            throw new UsernameNotFoundException("User not found in the database");
        } else {
            log.info("User found in the database: {}", user.getUsername());
            UserDTO userDTO = mapperUtils.userMapper(user);
            return new UserPrincipal(userDTO);
        }
    }

    /**
     * @param userId
     * @return
     */
    @Override
    public String deleteUser(Long userId) {
        User user = userRepository.findById(userId).orElse(null);
        if (user != null) {
            if (Objects.equals(user.getRole().getRoleName(), "ROLE_ADMIN")) {
                return "Không thể xóa tài khoản role admin";
            } else {
                user.setRole(null);
                userRepository.delete(user);
                return "Xóa thành công user: " + user.getUsername();
            }
        } else {
            return "Không tìm thấy userId: " + userId;
        }
    }

    /**
     * @param request
     * @return
     */
    @Override
    public String updatePassword(ChangePasswordRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        log.info(authentication.getPrincipal().toString());
        User user = userRepository.findUserByUsername(authentication.getPrincipal().toString());
        if (passwordEncoder.matches(request.getOldPassword(), user.getPassword())) {
            user.setPassword(passwordEncoder.encode(request.getNewPassword()));
            return "Thay đổi mật khẩu thành công";
        } else {
            return "Mật khẩu cũ không chính xác";
        }
    }

    /**
     * @param username
     * @return
     */
    @Override
    public boolean isExists(String username) {
        User user = userRepository.findUserByUsername(username);
        return user != null && user.getId() > 0;
    }

    /**
     * @param username
     * @param roleName
     */
    @Override
    public void setRoleForUser(String username, String roleName) {
        User user = userRepository.findUserByUsername(username);
        Role role = roleRepository.findRoleByRoleName(roleName).orElse(null);
        if (user != null && role != null) {
            user.setRole(role);
        }
    }
}