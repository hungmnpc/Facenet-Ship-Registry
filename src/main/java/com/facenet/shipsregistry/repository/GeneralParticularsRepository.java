package com.facenet.shipsregistry.repository;

import com.facenet.shipsregistry.entity.GeneralParticulars;
import com.facenet.shipsregistry.modal.ReportNameAndPartName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Objects;
import java.util.Optional;

/**
 * @author: hungdinh
 * Date created: 11/04/2023
 */
public interface GeneralParticularsRepository extends JpaRepository<GeneralParticulars, Long> {

    public Optional<GeneralParticulars> findGeneralParticularsByReportNo(String reportNo);

    @Query(value = "select gp.reportNo from GeneralParticulars gp where gp.id = :id")
    public String getReportNo(@Param("id") Long id);

    @Query("select gp.reportNo from GeneralParticulars gp inner join " +
            "ReportIndex rp on gp.id = rp.generalParticulars.id inner join FormTM1 tm1 " +
            "on rp.id = tm1.reportIndex.id where tm1.id = :id" )
    public String getReportNoByFormTM1Id(@Param("id") Long id);


    @Query(value = "CALL getReportNoAndPartNameByForm(:formType, :formId)", nativeQuery = true)
    public Object[][] getReportAndPartNameByForm(@Param("formType") String formType,
                                              @Param("formId") Long formId);
}
