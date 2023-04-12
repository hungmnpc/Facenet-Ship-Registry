package com.facenet.shipsregistry.repository;

import com.facenet.shipsregistry.entity.Ship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author: hungdinh
 * Date created: 11/04/2023
 */
@Repository
public interface ShipRepository extends JpaRepository<Ship, Long> {
}
