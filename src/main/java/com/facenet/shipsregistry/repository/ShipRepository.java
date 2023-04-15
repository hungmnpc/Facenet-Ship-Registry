package com.facenet.shipsregistry.repository;

import com.facenet.shipsregistry.entity.Ship;
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
public interface ShipRepository extends JpaRepository<Ship, Long> {

    Optional<Ship> findShipByImoNumber(String imoNumber);

    List<Ship> findShipsByName(String shipName);

    @Query(value = "select s from Ship s where s.imoNumber like %:i% and  upper(s.name) like upper(concat('%', :n, '%') )")
    List<Ship> search(@Param("i") String imoNumber, @Param("n") String name);
}
