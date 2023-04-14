package com.facenet.shipsregistry.repository;

import com.facenet.shipsregistry.entity.Certificate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author: hungdinh
 * Date created: 11/04/2023
 */

@Repository
public interface CertificateRepository extends JpaRepository<Certificate, Long> {


    /**
     *
     * @param certificateNo
     * @return
     */
    public Optional<Certificate> findCertificateByCertificateNo(String certificateNo);
}
