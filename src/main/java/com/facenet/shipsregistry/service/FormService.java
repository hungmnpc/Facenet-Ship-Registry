package com.facenet.shipsregistry.service;

import com.facenet.shipsregistry.modal.FormDTO;
import com.facenet.shipsregistry.modal.FormTM1DTO;
import com.facenet.shipsregistry.modal.FormTM3DTO;
import com.facenet.shipsregistry.modal.ReportIndexDTO;
import com.facenet.shipsregistry.request.FormTM1RequestBody;
import com.facenet.shipsregistry.request.FormTM3RequestBody;
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

    /**
     *
     * @param requestBody
     * @return
     */
    public FormDTO saveNewFormTM3(FormTM3RequestBody requestBody, Long reportIndex);

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

    public FormTM3DTO getFormTM3ByID(Long id);

    /**
     *
     * @param id
     * @return
     */
    public ReportIndexDTO getReportIndexByID(Long id);
}
