package com.facenet.shipsregistry.repository;

import com.facenet.shipsregistry.entity.MeasurementTM6;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author: CongTu
 * Date Created: 24/04/2023
 */

@Repository
public interface MeasurementTM6Repository extends JpaRepository<MeasurementTM6, Long> {
}
