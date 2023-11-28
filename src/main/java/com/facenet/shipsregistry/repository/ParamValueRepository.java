package com.facenet.shipsregistry.repository;

import com.facenet.shipsregistry.entity.ParamType;
import com.facenet.shipsregistry.entity.ParamValue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

/**
 * @author: hungdinh
 * Date created: 11/04/2023
 */
@Repository
public interface ParamValueRepository extends JpaRepository<ParamValue, Long> {

    public List<ParamValue> findParamValuesByType(ParamType paramType);

    public Optional<ParamValue> findByTypeAndParamAndValue(ParamType type, String param, String value);
}
