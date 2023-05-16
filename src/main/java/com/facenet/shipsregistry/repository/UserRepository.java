package com.facenet.shipsregistry.repository;

import com.facenet.shipsregistry.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author: hungdinh
 * Date created: 11/04/2023
 */

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     *
     * @param username
     * @return
     */
    User findUserByUsername(String username);
}
