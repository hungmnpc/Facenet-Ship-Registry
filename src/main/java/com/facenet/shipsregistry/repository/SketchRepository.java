package com.facenet.shipsregistry.repository;

import com.facenet.shipsregistry.entity.Sketch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: hungdinh
 * Date created: 11/04/2023
 */

@Repository
public interface SketchRepository extends JpaRepository<Sketch, Long> {

    @Query("select s from Sketch s where s.formId = :formId and s.formType = :formType")
    List<Sketch> getAllSketchInForm(@Param("formId") Long formId, @Param("formType") String formType);
}
