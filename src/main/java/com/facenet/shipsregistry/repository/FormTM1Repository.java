package com.facenet.shipsregistry.repository;

import com.facenet.shipsregistry.entity.FormTM1;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author: hungdinh
 * Date created: 11/04/2023
 */

@Repository
public interface FormTM1Repository extends JpaRepository<FormTM1, Long> {
}
