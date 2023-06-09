package com.facenet.shipsregistry.repository;

import com.facenet.shipsregistry.entity.ReportIndex;
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
public interface ReportIndexRepository extends JpaRepository<ReportIndex, Long> {


    @Query(value = "select r from ReportIndex r " +
            "join r.generalParticulars gp where gp.id = :gpID and r.item = :item")
    List<ReportIndex> findReportIndexExist(@Param("gpID") Long id, @Param("item") String item);

    @Query(value = "select r from ReportIndex r where r.generalParticulars.id = :id")
    List<ReportIndex> findAllReportIndexInGP(@Param("id") Long id);

    /**
     *
     * @param gpId
     * @return
     */
    @Query("select r from ReportIndex  r where r.generalParticulars.id = :gpId order by r.partIndex ASC ")
    List<ReportIndex> getALlReportIndexInGPSortASC(@Param("gpId") Long gpId);


    @Query(value = "select p.item from report_index p inner join :FORMNAME " +
            "on p.id = :FOMRNAME.", nativeQuery = true)
    String getPartName();
}
