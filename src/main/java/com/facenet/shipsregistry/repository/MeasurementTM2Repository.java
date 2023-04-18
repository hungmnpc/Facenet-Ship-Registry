package com.facenet.shipsregistry.repository;


import com.facenet.shipsregistry.entity.MeasurementTM2;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author: CongTu
 * Date Created: 17/04/2023
 */

@Repository
public interface MeasurementTM2Repository extends JpaRepository<MeasurementTM2, Long> {
}
