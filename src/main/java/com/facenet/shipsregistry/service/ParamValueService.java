package com.facenet.shipsregistry.service;

import com.facenet.shipsregistry.entity.ParamType;
import com.facenet.shipsregistry.entity.ParamValue;
import com.facenet.shipsregistry.modal.ParamValueDTO;

import java.util.List;

/**
 * @author: hungdinh
 * Date created: 11/04/2023
 */
public interface ParamValueService {

    /**
     *
     * @param paramType
     * @return
     */
    public List<ParamValueDTO> getParamValueByType(Integer type);

    /**
     *
     * @param paramValue
     * @return
     */
    public ParamValueDTO saveNewParamValue(ParamValue paramValue);

}
