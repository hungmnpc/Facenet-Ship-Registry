package com.facenet.shipsregistry.service;

import com.facenet.shipsregistry.entity.FormTM5;
import com.facenet.shipsregistry.modal.*;
import com.facenet.shipsregistry.request.FormTM1RequestBody;
import com.facenet.shipsregistry.request.FormTM5RequestBody;
import com.facenet.shipsregistry.request.ReportIndexRequestBody;

import java.util.List;

/**
 * @author: hungdinh
 * Date created: 11/04/2023
 */
public interface FormService {

    /**
     *
     * @param requestBody
     * @return
     */
    public FormDTO saveNewFormTM1(FormTM1RequestBody requestBody, Long reportIndex);

    public List<ReportIndexDTO> getAllReportIndexes();

    /**
     *
     * @param id
     * @return
     */
    public List<FormDTO> findAllFormInReportIndex(Long id);

    /**
     *
     * @param requestBody
     * @return
     */
    public ReportIndexDTO saveNewReportIndex(ReportIndexRequestBody requestBody, Long id);

    /**
     *
     * @param id
     * @return
     */
    public FormTM1DTO getFormTM1ByID(Long id);

    /**
     *
     * @param id
     * @return
     */
    public ReportIndexDTO getReportIndexByID(Long id);

    public FormDTO saveNewFormTM5(FormTM5RequestBody requestBody, Long reportIndex);

    FormTM5DTO getFormTM5ByID(Long id);
}
