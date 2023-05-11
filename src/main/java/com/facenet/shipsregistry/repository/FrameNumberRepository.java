package com.facenet.shipsregistry.repository;
import com.facenet.shipsregistry.entity.FrameNumber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FrameNumberRepository extends JpaRepository<FrameNumber,Long> {
}
