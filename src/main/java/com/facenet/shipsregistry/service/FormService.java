package com.facenet.shipsregistry.service;

import com.facenet.shipsregistry.modal.*;
import com.facenet.shipsregistry.request.*;

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

    FormTM5DTO getFormTM5ByID(Long id);



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

    /**
     *
     * @param requestBody
     * @return
     */
    public FormDTO saveNewFormTM2 (FormTM2RequestBody requestBody, Long id);

    /**
     *
     * @param requestBody
     * @param id
     * @return
     */
    public FormDTO saveNewFormTM4 (FormTM4RequestBody requestBody, Long id);

    /**
     *
     * @param requestBody
     * @param reportIndexID
     * @return
     */
    FormDTO saveNewFormTM7(FormTM7RequestBody requestBody, Long reportIndexID);

    FormTM6DTO getFormTM6ByID(Long id);

    /**
     *
     * @param id
     * @return
     */
    FormTM7DTO getFormTM7ByID(Long id);

    /**
     *
     * @param requestBody
     * @param id
     * @return
     */
    public FormDTO saveNewFormTM6 (FormTM6RequestBody requestBody, Long id);

    FormTM2DTO getFormTM2ByID(Long id);

    FormTM4DTO getFormTM4ByID(Long id);


    /**
     *
     * @param requestBody
     * @param reportIndex
     * @return
     */
    public FormDTO saveNewFormTM5(FormTM5RequestBody requestBody, Long reportIndex);

    /**
     *
     * @param id
     * @param requestBody
     * @return
     */
    public FormDTO updateFormTM1(Long id, FormTM1RequestBody requestBody);

    /**
     *
     * @param id
     */
    public Boolean deleteFormTM1(Long id);

    public Boolean deleteFormTM4(Long id);

    public Boolean deleteFormTM6(Long id);

    public Boolean deleteFormTM7(Long id);

    /**
     *
     * @param id
     * @return
     */
    public Boolean isFormTM1Exist(Long id);

    Boolean isFormTM4Exist(Long id);


    Boolean isFormTM6Exist(Long id);

    Boolean isFormTM7Exist(Long id);

    /**
     *
     * @param id
     * @param requestBody
     * @return
     */
    public DetailMeasurementDTO updateDetailMeasurement(Long id, DetailMeasurementRequestBody requestBody);



    MeasurementTM1DTO updateMeasurementTM1(Long id, MeasurementTM1RequestBody requestBody);

    /**
     *
     * @param id
     * @param requestBody
     * @return
     */
    public FormDTO updateFormTM2 (Long id, FormTM2RequestBody requestBody);

    /**
     *
     * @param id
     * @return
     */
    public Boolean deletedFormTM2(Long id);

    /**
     *
     * @param id
     * @return
     */
    public Boolean isFormTM2Exist(Long id);

    /**
     *
     * @param id
     * @param requestBody
     * @return
     */
    FormDTO updateFormTM3(Long id, FormTM3RequestBody requestBody);

    /**
     *
     * @param id
     * @return
     */
    Boolean deletedFormTM3(Long id);

    /**
     *
     * @param id
     * @return
     */
    Boolean isFormTM5Exist(Long id);

    /**
     *
     * @param id
     * @param requestBody
     * @return
     */
    FormDTO updateFormTM5(Long id, FormTM5RequestBody requestBody);

    /**
     *
     * @param id
     * @return
     */
    Boolean deletedFormTM5(Long id);

    /**
     *
     * @param id
     * @return
     */
    Boolean isFormTM3Exist(Long id);

    /**
     *
     * @param id
     * @param requestBody
     * @return
     */
    FormDTO updateFormTM7(Long id, FormTM7RequestBody requestBody);

    /**
     *
     * @param id
     * @param requestBody
     * @return
     */
    FormDTO updateFormTM6(Long id, FormTM6RequestBody requestBody);

    /**
     *
     * @param id
     * @param requestBody
     * @return
     */
    FormDTO updateFormTM4(Long id, FormTM4RequestBody requestBody);

    /**
     *
     * @param partId
     * @param formIndex
     * @return
     */
    public Boolean deletedFormUsingPart(Long partId, Integer formIndex);

    /**
     * @param formId
     * @param formType
     * @param newIndex
     */
    public void updateFormIndex(Long formId, String formType, Integer newIndex);

}
