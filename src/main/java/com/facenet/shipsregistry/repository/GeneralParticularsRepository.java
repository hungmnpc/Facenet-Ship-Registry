package com.facenet.shipsregistry.repository;

import com.facenet.shipsregistry.entity.GeneralParticulars;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author: hungdinh
 * Date created: 11/04/2023
 */
public interface GeneralParticularsRepository extends JpaRepository<GeneralParticulars, Long> {

    public Optional<GeneralParticulars> findGeneralParticularsByReportNo(String reportNo);
}
