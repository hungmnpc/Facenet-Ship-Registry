package com.facenet.shipsregistry.service;

import com.facenet.shipsregistry.entity.*;
import com.facenet.shipsregistry.modal.FormDTO;
import com.facenet.shipsregistry.modal.FormTM1DTO;
import com.facenet.shipsregistry.modal.ReportIndexDTO;
import com.facenet.shipsregistry.repository.FormTM1Repository;
import com.facenet.shipsregistry.repository.GeneralParticularsRepository;
import com.facenet.shipsregistry.repository.ReportIndexRepository;
import com.facenet.shipsregistry.request.FormTM1RequestBody;
import com.facenet.shipsregistry.request.ReportIndexRequestBody;
import com.facenet.shipsregistry.utils.MapperUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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
                                    null, measurementTM1DTO.getForwardReadingMeasurementDetail().getOriginalThickness(),
                                    measurementTM1DTO.getForwardReadingMeasurementDetail().getMaxAlwbDim(),
                                    measurementTM1DTO.getForwardReadingMeasurementDetail().getGaugedP(),
                                    measurementTM1DTO.getForwardReadingMeasurementDetail().getGaugedS(),
                                    null,
                                    null
                            );
                            DetailMeasurement detailMeasurementAfter = new DetailMeasurement(
                                    null, measurementTM1DTO.getAfterReadingMeasurementDetail().getOriginalThickness(),
                                    measurementTM1DTO.getAfterReadingMeasurementDetail().getMaxAlwbDim(),
                                    measurementTM1DTO.getAfterReadingMeasurementDetail().getGaugedP(),
                                    measurementTM1DTO.getAfterReadingMeasurementDetail().getGaugedS(),
                                    null,
                                    null
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
        ReportIndex reportIndex = new ReportIndex(null, requestBody.getPartIndex(), requestBody.getItem(), null, null);
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

    /**
     * @param id
     * @return
     */
    @Override
    public List<FormDTO> findAllFormInReportIndex(Long id) {
//        List<FormTM1DTO> formTM1DTOList =
        return null;
    }
}