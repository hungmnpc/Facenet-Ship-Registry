package com.facenet.shipsregistry.repository;
import com.facenet.shipsregistry.entity.FormTM5;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FormTM5Repository extends JpaRepository<FormTM5,Long> {
}
