package com.facenet.shipsregistry.service;

import com.facenet.shipsregistry.entity.FormTM2;
import com.facenet.shipsregistry.modal.*;
import com.facenet.shipsregistry.request.*;
import org.springframework.web.multipart.MultipartFile;

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

    /**
     *
     * @param id
     * @return
     */
    public Boolean isFormTM1Exist(Long id);

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

    FormDTO updateFormTM3(Long id, FormTM3RequestBody requestBody);

    Boolean deletedFormTM3(Long id);

    Boolean isFormTM5Exist(Long id);

    FormDTO updateFormTM5(Long id, FormTM5RequestBody requestBody);

    Boolean deletedFormTM5(Long id);

    Boolean isFormTM3Exist(Long id);



    /**
     *
     * @param excelFile
     * @return
     */
    public FormTM1DTO uploadFormTm1FromExcel(Long id, MultipartFile excelFile) throws Exception;

    /**
     *
     * @param id
     * @return
     */
    public FormTM1DTO getFormTM1(Long id);

    FormDTO updateFormTM7(Long id, FormTM7RequestBody requestBody);

    FormDTO updateFormTM6(Long id, FormTM6RequestBody requestBody);

    FormDTO updateFormTM4(Long id, FormTM4RequestBody requestBody);
}
