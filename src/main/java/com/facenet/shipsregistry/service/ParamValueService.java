package com.facenet.shipsregistry.service;

import com.facenet.shipsregistry.entity.ParamType;
import com.facenet.shipsregistry.entity.ParamValue;
import com.facenet.shipsregistry.modal.ParamValueDTO;
import com.facenet.shipsregistry.request.ParamValueRequestBody;

import java.util.List;

/**
 * @author: hungdinh
 * Date created: 11/04/2023
 */
public interface ParamValueService {

    /**
     *
     * @param type
     * @return
     */
    public List<ParamValueDTO> getParamValueByType(Integer type);

    /**
     *
     * @param requestBody
     * @return
     */
    public ParamValueDTO saveNewParamValue(ParamValueRequestBody requestBody);

}
