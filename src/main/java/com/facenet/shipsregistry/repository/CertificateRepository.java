package com.facenet.shipsregistry.repository;

import com.facenet.shipsregistry.entity.Certificate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author: hungdinh
 * Date created: 11/04/2023
 */

@Repository
public interface CertificateRepository extends JpaRepository<Certificate, Long> {

    public Optional<Certificate> findCertificateByCertificateNo(String certificateNo);

    @Query("select c from Certificate c where upper(c.certificateNo) like upper(concat(:cn, '%') ) " +
            "and upper(c.certificateOrganization) like upper(concat(:co, '%') ) ")
    public List<Certificate> search(@Param("cn") String certificateNo,
                                    @Param("co") String certificateOrganization);
}
