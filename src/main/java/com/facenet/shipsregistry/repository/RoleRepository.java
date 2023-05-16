package com.facenet.shipsregistry.repository;

import com.facenet.shipsregistry.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author: hungdinh
 * Date created: 11/04/2023
 */

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    /**
     *
     * @param roleName
     * @return
     */
    Optional<Role> findRoleByRoleName(String roleName);
}
