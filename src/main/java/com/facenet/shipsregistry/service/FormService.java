package com.facenet.shipsregistry.service;

import com.facenet.shipsregistry.modal.FormDTO;
import com.facenet.shipsregistry.modal.FormTM1DTO;
import com.facenet.shipsregistry.modal.ReportIndexDTO;
import com.facenet.shipsregistry.request.FormTM1RequestBody;
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
    public ReportIndexDTO saveNewReportIndex(ReportIndexRequestBody requestBody, Long id);
}
