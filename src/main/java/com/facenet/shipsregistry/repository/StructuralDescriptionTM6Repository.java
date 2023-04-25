package com.facenet.shipsregistry.repository;

import com.facenet.shipsregistry.entity.StructuralDescriptionTM6;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author: CongTu
 * Date created: 24/04/2023
 */

@Repository
public interface StructuralDescriptionTM6Repository extends JpaRepository<StructuralDescriptionTM6, Long> {
}
