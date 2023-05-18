package com.facenet.shipsregistry.repository;

import com.facenet.shipsregistry.entity.StructuralTM5;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author: hungdinh
 * Date created: 11/04/2023
 */

@Repository
public interface StructuralTM5Repository extends JpaRepository<StructuralTM5, Long> {
}