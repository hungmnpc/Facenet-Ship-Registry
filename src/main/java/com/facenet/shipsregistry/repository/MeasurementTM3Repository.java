package com.facenet.shipsregistry.repository;

import com.facenet.shipsregistry.entity.MeasurementTM3;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author: CongTu
 * Date Created: 17/04/2023
 */

@Repository
public interface MeasurementTM3Repository extends JpaRepository<MeasurementTM3, Long> {
}

