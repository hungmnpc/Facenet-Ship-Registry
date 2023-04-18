package com.facenet.shipsregistry.repository;
import com.facenet.shipsregistry.entity.StructuralDescriptionTM6;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StructuralDescriptionTM6Repository extends JpaRepository<StructuralDescriptionTM6,Long> {
}