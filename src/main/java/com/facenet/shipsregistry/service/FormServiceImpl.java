package com.facenet.shipsregistry.service;

import com.facenet.shipsregistry.entity.*;
import com.facenet.shipsregistry.modal.FormDTO;
import com.facenet.shipsregistry.modal.FormTM1DTO;
import com.facenet.shipsregistry.modal.FormTM3DTO;
import com.facenet.shipsregistry.modal.ReportIndexDTO;
import com.facenet.shipsregistry.repository.*;
import com.facenet.shipsregistry.request.*;
import com.facenet.shipsregistry.repository.GeneralParticularsRepository;
import com.facenet.shipsregistry.repository.ReportIndexRepository;
import com.facenet.shipsregistry.request.FormTM1RequestBody;
import com.facenet.shipsregistry.utils.MapperUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
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
    FormTM3Repository formTM3Repository;
    
    @Autowired
    FormTM2Repository formTM2Repository;

    @Autowired
    FormTM4Repository formTM4Repository;

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
        log.info(id.toString());
        FormTM1 formTM1 = formTM1Repository.getById(id);
        if (formTM1 != null) {
            log.info("{}", formTM1.getReportIndex());
            return mapperUtils.formTM1Mapper(formTM1);
        } else {
            return null;
        }
    }

    /**
     * @param requestBody
     * @return
     */
    @Override
    public FormDTO saveNewFormTM3(FormTM3RequestBody requestBody, Long reportIndexID) {

        Optional<ReportIndex> reportIndex = reportIndexRepository.findById(reportIndexID);
        FormTM3 formTM3 = new FormTM3(null, requestBody.getFirstFrameNo(), requestBody.getSecondFrameNo()
                , requestBody.getThirdFrameNo(), null, null);;
        reportIndex.ifPresent(formTM3::setReportIndex);
        List<MeasurementTM3> measurementTM3List =
                requestBody.getMeasurementTM3List().stream()
                        .map(measurementTM3DTO -> {
                            DetailMeasurement detailMeasurementFirst = new DetailMeasurement(
                                    measurementTM3DTO.getFirstTransverseSectionMeasurementDetail().getOriginalThickness(),
                                    measurementTM3DTO.getFirstTransverseSectionMeasurementDetail().getMaxAlwbDim(),
                                    measurementTM3DTO.getFirstTransverseSectionMeasurementDetail().getGaugedP(),
                                    measurementTM3DTO.getFirstTransverseSectionMeasurementDetail().getGaugedS()
                            );
                            DetailMeasurement detailMeasurementSecond = new DetailMeasurement(
                                    measurementTM3DTO.getSecondTransverseSectionMeasurementDetail().getOriginalThickness(),
                                    measurementTM3DTO.getSecondTransverseSectionMeasurementDetail().getMaxAlwbDim(),
                                    measurementTM3DTO.getSecondTransverseSectionMeasurementDetail().getGaugedP(),
                                    measurementTM3DTO.getSecondTransverseSectionMeasurementDetail().getGaugedS()
                            );
                            DetailMeasurement detailMeasurementThird = new DetailMeasurement(
                                    measurementTM3DTO.getThirdTransverseSectionMeasurementDetail().getOriginalThickness(),
                                    measurementTM3DTO.getThirdTransverseSectionMeasurementDetail().getMaxAlwbDim(),
                                    measurementTM3DTO.getThirdTransverseSectionMeasurementDetail().getGaugedP(),
                                    measurementTM3DTO.getThirdTransverseSectionMeasurementDetail().getGaugedS()
                            );
                            return new MeasurementTM3(null, measurementTM3DTO.getStructuralMember(),
                                    measurementTM3DTO.getNoOrLetter(), formTM3,
                                    detailMeasurementFirst, detailMeasurementSecond, detailMeasurementThird );
                        }).toList();
        formTM3.setMeasurementTM3List(measurementTM3List);
        try {
            FormTM3 formTM3Saved = formTM3Repository.save(formTM3);
            if (formTM3Saved.getId() > 0) {
                return mapperUtils.formTM3Mapper(formTM3Saved);
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
    public FormTM3DTO getFormTM3ByID(Long id) {
        Optional<FormTM3> formTM3 = formTM3Repository.findById(id);
        return formTM3.map(tm3 -> mapperUtils.formTM3Mapper(tm3)).orElse(null);
    }

    /**
     * @param requestBody
     * @return
     */
    @Override
    public ReportIndexDTO saveNewReportIndex(ReportIndexRequestBody requestBody, Long id) {

        Optional<GeneralParticulars> generalParticulars =
                generalParticularsRepository.findById(id);

        List<ReportIndex> reportIndexList = reportIndexRepository.findReportIndexExist(id, requestBody.getItem());
        if (reportIndexList.size() > 0) {
            return null;
        }
        if (generalParticulars.isPresent()) {
            ReportIndex reportIndex = new ReportIndex(
                    generalParticulars.get().getReportIndexList().size() + 1,
                    requestBody.getItem());


            reportIndex.setGeneralParticulars(generalParticulars.get());
            try {
                ReportIndex reportIndexSaved = reportIndexRepository.save(reportIndex);
                if (reportIndexSaved.getId() > 0) {
                    return mapperUtils.reportIndexMapper(reportIndexSaved);
                }
            } catch (Exception exception) {
                log.debug(exception.getMessage());
            }
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

    /**
     * @param requestBody
     * @return
     */
    @Override
    public FormDTO saveNewFormTM2(FormTM2RequestBody requestBody, Long reportIndexID) {

        Optional<ReportIndex> reportIndex = reportIndexRepository.findById(reportIndexID);
        FormTM2 formTM2 = new FormTM2(
                requestBody.getName(), requestBody.getFirstFrameNoTM2(),
                requestBody.getSecondFrameNoTM2(), requestBody.getThirdFrameNoTM2()
        );
        reportIndex.ifPresent(formTM2::setReportIndex);
        List<MeasurementTM2> measurementTM2List = requestBody.getMeasurementTM2List().stream()
                .map(measurementTM2RequestBody -> {
                    DetailMeasurement first =
                            mapperUtils.mapperToDetailMeasurement(
                                    measurementTM2RequestBody.getFirstTransverseSectionMeasurementDetailTM2());
                    DetailMeasurement second =
                            mapperUtils.mapperToDetailMeasurement(
                                    measurementTM2RequestBody.getSecondTransverseSectionMeasurementDetailTM2()
                            );
                    DetailMeasurement third =
                            mapperUtils.mapperToDetailMeasurement(
                                    measurementTM2RequestBody.getThirdTransverseSectionMeasurementDetailTM2()
                            );
                    return new MeasurementTM2(null, measurementTM2RequestBody.getStrakePosition(),
                            measurementTM2RequestBody.getNoOrLetter(), formTM2,
                            first, second, third);
                }).toList();
        formTM2.setMeasurementTM2List(measurementTM2List);
        try {
            FormTM2 formTM2Saved = formTM2Repository.save(formTM2);
            if (formTM2Saved.getId() > 0) {
                return mapperUtils.formTM2Mapper(formTM2Saved);
            }
        } catch (Exception exception) {
            log.debug(exception.getMessage());
            return null;
        }
        return null;
    }

    /**
     * @param requestBody
     * @param reportIndexID
     * @return
     */
    @Override
    public FormDTO saveNewFormTM4(FormTM4RequestBody requestBody, Long reportIndexID) {
        Optional<ReportIndex> reportIndex = reportIndexRepository.findById(reportIndexID);
        FormTM4 formTM4 = new FormTM4(requestBody.getTankDescription(), requestBody.getLocationOfStructure());
        reportIndex.ifPresent(formTM4::setReportIndex);
        List<StructuralMemberTM4> structuralMemberTM4List =
                requestBody.getStructuralMemberTM4List().stream()
                        .map(structuralMemberTM4RequestBody -> {
                            StructuralMemberTM4 structuralMemberTM4 = new StructuralMemberTM4(
                                    null, structuralMemberTM4RequestBody.getStructuralMemberTitle(),
                                    formTM4, null
                            );
                            List<MeasurementTM4> measurementTM4List =
                                    structuralMemberTM4RequestBody.getMeasurementTM4List().stream()
                                            .map(measurementTM4RequestBody -> {
                                                DetailMeasurement detailMeasurement =
                                                        mapperUtils.mapperToDetailMeasurement(
                                                                measurementTM4RequestBody.getDetailMeasurement());

                                                return new MeasurementTM4(null,
                                                        measurementTM4RequestBody.getStructuralMember(),
                                                        measurementTM4RequestBody.getItem(),
                                                        detailMeasurement,
                                                        structuralMemberTM4);
                                            }).toList();
                            structuralMemberTM4.setMeasurementTM4List(measurementTM4List);
                            return structuralMemberTM4;
                        }).toList();
        formTM4.setStructuralMemberTM4List(structuralMemberTM4List);
        try {
            FormTM4 formTM4Saved = formTM4Repository.save(formTM4);
            if (formTM4Saved.getId() > 0) {
                return mapperUtils.formTM4Mapper(formTM4);
            } else {
                return null;
            }
        } catch (Exception exception) {
            log.error("{}", exception.getMessage());
            return null;
        }
    }
}