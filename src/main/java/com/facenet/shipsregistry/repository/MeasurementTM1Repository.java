package com.facenet.shipsregistry.repository;

import com.facenet.shipsregistry.entity.MeasurementTM1;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: CongTu
 * Date Created: 17/04/2023
 */

@Repository
public interface MeasurementTM1Repository extends JpaRepository<MeasurementTM1, Long> {
}
