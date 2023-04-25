package com.facenet.shipsregistry.repository;

import com.facenet.shipsregistry.entity.MeasurementTM7;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author: CongTu
 * Date created: 24/04/2023
 */

@Repository
public interface MeasurementTM7Repository extends JpaRepository<MeasurementTM7, Long> {
}
