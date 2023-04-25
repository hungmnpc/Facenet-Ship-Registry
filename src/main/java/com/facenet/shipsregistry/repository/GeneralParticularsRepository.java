package com.facenet.shipsregistry.repository;

import com.facenet.shipsregistry.entity.GeneralParticulars;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

/**
 * @author: hungdinh
 * Date created: 11/04/2023
 */
public interface GeneralParticularsRepository extends JpaRepository<GeneralParticulars, Long> {

    public Optional<GeneralParticulars> findGeneralParticularsByReportNo(String reportNo);

    @Query(value = "select gp.reportNo from GeneralParticulars gp where gp.id = :id")
    public String getReportNo(@Param("id") Long id);
}
