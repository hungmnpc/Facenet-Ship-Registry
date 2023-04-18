package com.facenet.shipsregistry.repository;

import com.facenet.shipsregistry.entity.FormTM4;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author: CongTu
 * Date Created: 17/04/2023
 */

@Repository
public interface FormTM4Repository extends JpaRepository<FormTM4,Long> {
}
