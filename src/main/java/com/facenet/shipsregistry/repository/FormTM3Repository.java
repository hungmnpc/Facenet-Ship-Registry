package com.facenet.shipsregistry.repository;

import com.facenet.shipsregistry.entity.FormTM3;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author: CongTu
 * Date Created: 17/04/2023
 */

@Repository
public interface FormTM3Repository extends JpaRepository<FormTM3, Long> {
}
