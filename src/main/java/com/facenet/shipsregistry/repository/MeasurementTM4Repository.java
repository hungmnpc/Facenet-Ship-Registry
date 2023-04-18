package com.facenet.shipsregistry.repository;

import com.facenet.shipsregistry.entity.MeasurementTM4;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author: CongTu
 * Date Created: 17/04/2023
 */

@Repository
public interface MeasurementTM4Repository extends JpaRepository<MeasurementTM4, Long> {
}
