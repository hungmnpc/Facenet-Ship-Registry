package com.facenet.shipsregistry.service;

import com.facenet.shipsregistry.entity.*;
import com.facenet.shipsregistry.modal.FormDTO;
import com.facenet.shipsregistry.modal.FormTM1DTO;
import com.facenet.shipsregistry.modal.FormTM5DTO;
import com.facenet.shipsregistry.modal.ReportIndexDTO;
import com.facenet.shipsregistry.repository.FormTM1Repository;
import com.facenet.shipsregistry.repository.FormTM5Repository;
import com.facenet.shipsregistry.repository.GeneralParticularsRepository;
import com.facenet.shipsregistry.repository.ReportIndexRepository;
import com.facenet.shipsregistry.request.FormTM1RequestBody;
import com.facenet.shipsregistry.request.FormTM5RequestBody;
import com.facenet.shipsregistry.request.ReportIndexRequestBody;
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
public class FormServiceImpl implements FormService{

    @Autowired
    FormTM1Repository formTM1Repository;

    @Autowired
    FormTM5Repository formTM5Repository;

    @Autowired
    ReportIndexRepository reportIndexRepository;

    @Autowired
    GeneralParticularsRepository generalParticularsRepository;

    @Autowired
    MapperUtils mapperUtils;

    /**
     * @param requestBody
     * @return
     */
    @Override
    public FormDTO saveNewFormTM1(FormTM1RequestBody requestBody, Long reportIndexID) {

        Optional<ReportIndex> reportIndex = reportIndexRepository.findById(reportIndexID);
        FormTM1 formTM1 = new FormTM1(null, requestBody.getStrakePosition(), null, null);;
        reportIndex.ifPresent(formTM1::setReportIndex);
        List<MeasurementTM1> measurementTM1List =
                requestBody.getMeasurementTM1List().stream()
                        .map(measurementTM1DTO -> {
                            DetailMeasurement detailMeasurementForward = new DetailMeasurement(
                                    measurementTM1DTO.getForwardReadingMeasurementDetail().getOriginalThickness(),
                                    measurementTM1DTO.getForwardReadingMeasurementDetail().getMaxAlwbDim(),
                                    measurementTM1DTO.getForwardReadingMeasurementDetail().getGaugedP(),
                                    measurementTM1DTO.getForwardReadingMeasurementDetail().getGaugedS()
                            );
                            DetailMeasurement detailMeasurementAfter = new DetailMeasurement(
                                    measurementTM1DTO.getAfterReadingMeasurementDetail().getOriginalThickness(),
                                    measurementTM1DTO.getAfterReadingMeasurementDetail().getMaxAlwbDim(),
                                    measurementTM1DTO.getAfterReadingMeasurementDetail().getGaugedP(),
                                    measurementTM1DTO.getAfterReadingMeasurementDetail().getGaugedS()
                            );
                             return new MeasurementTM1(null, measurementTM1DTO.getPlatePosition(),
                                    measurementTM1DTO.getNoOrLetter(), formTM1,
                                    detailMeasurementForward, detailMeasurementAfter );
                        }).toList();
        formTM1.setMeasurementTM1List(measurementTM1List);
        try {
            FormTM1 formTM1Saved = formTM1Repository.save(formTM1);
            if (formTM1Saved.getId() > 0) {
                return mapperUtils.formTM1Mapper(formTM1Saved);
            } else  {
                return null;
            }
        } catch (Exception exception) {
            log.debug(exception.getMessage());
            return null;
        }
    }

    /**
     * @param id
     * @return
     */
    @Override
    public FormTM1DTO getFormTM1ByID(Long id) {
        Optional<FormTM1> formTM1 = formTM1Repository.findById(id);
        return formTM1.map(tm1 -> mapperUtils.formTM1Mapper(tm1)).orElse(null);
    }

    /**
     * @param requestBody
     * @return
     */
    @Override
    public ReportIndexDTO saveNewReportIndex(ReportIndexRequestBody requestBody, Long id) {
        ReportIndex reportIndex = new ReportIndex(null, requestBody.getPartIndex(), requestBody.getItem(), null, null,null);
        Optional<GeneralParticulars> generalParticulars =
                generalParticularsRepository.findById(id);
        log.info(generalParticulars.get().getReportNo());
        generalParticulars.ifPresent(reportIndex::setGeneralParticulars);
        try {
            ReportIndex reportIndexSaved = reportIndexRepository.save(reportIndex);
            if (reportIndexSaved.getId() > 0) {
                return mapperUtils.reportIndexMapper(reportIndexSaved);
            }
        } catch (Exception exception) {
            log.debug(exception.getMessage());
        }
        return null;
    }

    /**
     * @param id
     * @return
     */
    @Override
    public ReportIndexDTO getReportIndexByID(Long id) {
        Optional<ReportIndex> reportIndex = reportIndexRepository.findById(id);
        return reportIndex.map(index -> mapperUtils.reportIndexMapper(index)).orElse(null);
    }
    @Override
    public List<ReportIndexDTO> getAllReportIndexes() {
        List<ReportIndex> reportIndexes = reportIndexRepository.findAll();
        return reportIndexes.stream()
                .map(index -> mapperUtils.reportIndexMapper(index))
                .collect(Collectors.toList());
    }

    /**
     * @param id
     * @return
     */
    @Override
    public List<FormDTO> findAllFormInReportIndex(Long id) {
//        List<FormTM1DTO> formTM1DTOList =
        return null;
    }

    /**
     *
     * @param requestBody
     * @param reportIndexID
     * @return
     */
    @Override
    public FormDTO saveNewFormTM5(FormTM5RequestBody requestBody, Long reportIndexID) {

        Optional<ReportIndex> reportIndex = reportIndexRepository.findById(reportIndexID);
        FormTM5 formTM5 = new FormTM5(null, requestBody.getDescription(),requestBody.getName(), requestBody.getLocationOfStructure(),requestBody.getTankHolDescription(),null,null,null);;
        reportIndex.ifPresent(formTM5::setReportIndex);
        List<MeasurementTM5> measurementTM5List =
                requestBody.getMeasurementTM5List().stream()
                        .map(measurementTM5DTO -> {
                            DetailMeasurement measurementDetail = new DetailMeasurement(
                                    measurementTM5DTO.getMeasurementDetail().getOriginalThickness(),
                                    measurementTM5DTO.getMeasurementDetail().getMaxAlwbDim(),
                                    measurementTM5DTO.getMeasurementDetail().getGaugedP(),
                                    measurementTM5DTO.getMeasurementDetail().getGaugedS()
                            );
                            return new MeasurementTM5(null, measurementTM5DTO.getStructuralComponentType(),
                                    measurementTM5DTO.getStructuralComponent(), formTM5,
                                    measurementDetail);
                        }).toList();
        formTM5.setMeasurementTM5List(measurementTM5List);
        try {
            FormTM5 formTM5Saved = formTM5Repository.save(formTM5);
            if (formTM5Saved.getId() > 0) {
                return mapperUtils.formTM5Mapper(formTM5Saved);
            } else  {
                return null;
            }
        } catch (Exception exception) {
            log.debug(exception.getMessage());
            return null;
        }
    }
    @Override
    public FormTM5DTO getFormTM5ByID(Long id) {
        Optional<FormTM5> formTM5 = formTM5Repository.findById(id);
        return formTM5.map(tm5 -> mapperUtils.formTM5Mapper(tm5)).orElse(null);
    }

}