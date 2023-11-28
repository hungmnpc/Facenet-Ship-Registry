package com.facenet.shipsregistry.service;

import com.facenet.shipsregistry.entity.ParamType;
import com.facenet.shipsregistry.entity.ParamValue;
import com.facenet.shipsregistry.modal.ParamValueDTO;
import com.facenet.shipsregistry.repository.ParamValueRepository;
import com.facenet.shipsregistry.request.ParamValueRequestBody;
import com.facenet.shipsregistry.utils.MapperUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author: hungdinh
 * Date created: 11/04/2023
 */

@Service
@Transactional
@Slf4j
public class ParamValueServiceImpl implements ParamValueService{

    @Autowired
    ParamValueRepository paramValueRepository;

    @Autowired
    MapperUtils mapperUtils;

    /**
     *
     * @param type
     * @return
     */
    @Override
    public List<ParamValueDTO> getParamValueByType(Integer type) {
        List<ParamValue> paramValueList = paramValueRepository.findParamValuesByType(ParamType.of(type));
        List<ParamValueDTO> paramValueDTOList = paramValueList.stream()
                .map(paramValue -> mapperUtils.paramValueMapper(paramValue))
                .toList();
        return paramValueDTOList;
    }

    /**
     *
     * @param requestBody
     * @return
     */
    @Override
    public ParamValueDTO saveNewParamValue(ParamValueRequestBody requestBody) {
        ParamValue existed = paramValueRepository.findByTypeAndParamAndValue(
                ParamType.of(requestBody.getType()),
                requestBody.getParam(),
                requestBody.getValue()
        ).orElse(null);
        if (existed == null) {
            ParamValue paramValueSaved = paramValueRepository.save(
                    new ParamValue(null, requestBody.getParam(), requestBody.getValue(),
                            ParamType.of(requestBody.getType())));
            if (paramValueSaved.getId() > 0) {
                return mapperUtils.paramValueMapper(paramValueSaved);
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    /**
     * @param id
     * @return
     */
    @Override
    public void deleteParamValue(Long id) {
        paramValueRepository.deleteById(id);
    }

    /**
     * @param id
     * @param requestBody
     * @return
     */
    @Override
    public ParamValueDTO updateParamValue(Long id, ParamValueRequestBody requestBody) {
        Optional<ParamValue> paramValue = paramValueRepository.findById(id);
        if (paramValue.isPresent()) {
            paramValue.get().setParam(requestBody.getParam());
            paramValue.get().setValue(requestBody.getValue());
            return mapperUtils.paramValueMapper(paramValue.get());
        } else {
            return null;
        }
    }

    /**
     * @return
     */
    @Override
    public List<ParamValueDTO> getAllParamValue() {
        List<ParamValue> paramValueList = paramValueRepository.findAll();
        return paramValueList.stream()
                .map(paramValue -> mapperUtils.paramValueMapper(paramValue))
                .toList();
    }
}