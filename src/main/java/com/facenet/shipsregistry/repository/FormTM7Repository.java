package com.facenet.shipsregistry.repository;

import com.facenet.shipsregistry.entity.FormTM7;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FormTM7Repository extends JpaRepository<FormTM7,Long> {
}
