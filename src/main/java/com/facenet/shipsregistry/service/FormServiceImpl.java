package com.facenet.shipsregistry.service;
import com.facenet.shipsregistry.entity.*;
import com.facenet.shipsregistry.modal.*;
import com.facenet.shipsregistry.repository.*;
import com.facenet.shipsregistry.request.*;
import com.facenet.shipsregistry.repository.GeneralParticularsRepository;
import com.facenet.shipsregistry.repository.ReportIndexRepository;
import com.facenet.shipsregistry.request.FormTM1RequestBody;
import com.facenet.shipsregistry.utils.MapperUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
    DetailMeasurementRepository detailMeasurementRepository;

    @Autowired
    FormTM1Repository formTM1Repository;

    @Autowired
    FormTM3Repository formTM3Repository;
    
    @Autowired
    FormTM2Repository formTM2Repository;

    @Autowired
    FormTM4Repository formTM4Repository;

    @Autowired
    FormTM6Repository formTM6Repository;

    @Autowired
    FormTM5Repository formTM5Repository;

    @Autowired
    FormTM7Repository formTM7Repository;

    @Autowired
    ReportIndexRepository reportIndexRepository;

    @Autowired
    GeneralParticularsRepository generalParticularsRepository;

    @Autowired
    MeasurementTM1Repository measurementTM1Repository;

    @Autowired
    MeasurementTM2Repository measurementTM2Repository;

    @Autowired
    MeasurementTM3Repository measurementTM3Repository;

    @Autowired
    MeasurementTM5Repository measurementTM5Repository;

    @Autowired
    MeasurementTM7Repository measurementTM7Repository;

    @Autowired
    FrameNumberRepository frameNumberRepository;

    @Autowired
    StructuralDescriptionTM6Repository structuralDescriptionTM6Repository;

    @Autowired
    StructuralMemberDetailsTM4Repository structuralMemberDetailsTM4Repository;

    @Autowired
    MapperUtils mapperUtils;

    /**
     * @param requestBody
     * @return
     */
    @Override
    public FormDTO saveNewFormTM1(FormTM1RequestBody requestBody, Long reportIndexID) {

        Optional<ReportIndex> reportIndex = reportIndexRepository.findById(reportIndexID);
        FormTM1 formTM1 = new FormTM1(null,requestBody.getCode(), requestBody.getStrakePosition(), null, null);
        reportIndex.ifPresent(formTM1::setReportIndex);
        List<MeasurementTM1> measurementTM1List =
                requestBody.getMeasurementTM1List().stream()
                        .map(measurementTM1DTO -> {
                            DetailMeasurement detailMeasurementForward =
                                    createNewDetailMeasurement(measurementTM1DTO.getForwardReadingMeasurementDetail());
                            DetailMeasurement detailMeasurementAfter =
                                    createNewDetailMeasurement(measurementTM1DTO.getAfterReadingMeasurementDetail());
                             return new MeasurementTM1(null,  measurementTM1DTO.getPlatePosition(),
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
    @Override
    public FormTM1DTO getFormTM1ByID(Long id) {
        FormTM1 formTM1 = formTM1Repository.getById(id);
        if (formTM1 != null) {
            log.info("{}", formTM1.getReportIndex());
            return mapperUtils.formTM1Mapper(formTM1);
        } else {
            return null;
        }
    }
    @Override
    public FormDTO saveNewFormTM5(FormTM5RequestBody requestBody, Long reportIndexID) {

        Optional<ReportIndex> reportIndex = reportIndexRepository.findById(reportIndexID);
        FormTM5 formTM5 = new FormTM5(null, requestBody.getDescription(),
                requestBody.getName(), requestBody.getLocationOfStructure(),
                requestBody.getTankHolDescription(),requestBody.getFrameNo(),
                requestBody.getCode(),null, null);;
        reportIndex.ifPresent(formTM5::setReportIndex);
        List<MeasurementTM5> measurementTM5List =
                requestBody.getMeasurementTM5List().stream()
                        .map(measurementTM5DTO -> {
                            DetailMeasurement measurementDetail =
                                    createNewDetailMeasurement(measurementTM5DTO.getMeasurementDetail());
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

    /**
     * @param id
     * @return
     */


    /**
     * @param requestBody
     * @return
     */
    @Override
    public FormDTO saveNewFormTM3(FormTM3RequestBody requestBody, Long reportIndexID) {

        Optional<ReportIndex> reportIndex = reportIndexRepository.findById(reportIndexID);
        FormTM3 formTM3 = new FormTM3( requestBody.getCode(), requestBody.getFirstFrameNo(), requestBody.getSecondFrameNo(), requestBody.getThirdFrameNo());
        reportIndex.ifPresent(formTM3::setReportIndex);
        List<MeasurementTM3> measurementTM3List =
                requestBody.getMeasurementTM3List().stream()
                        .map(measurementTM3DTO -> {
                            DetailMeasurement detailMeasurementFirst =
                                    createNewDetailMeasurement(measurementTM3DTO.getFirstTransverseSectionMeasurementDetail());
                            DetailMeasurement detailMeasurementSecond =
                                    createNewDetailMeasurement(measurementTM3DTO.getSecondTransverseSectionMeasurementDetail());
                            DetailMeasurement detailMeasurementThird =
                                    createNewDetailMeasurement(measurementTM3DTO.getThirdTransverseSectionMeasurementDetail());
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
                requestBody.getSecondFrameNoTM2(), requestBody.getThirdFrameNoTM2(),
                requestBody.getCode()
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
        FormTM4 formTM4 = new FormTM4(requestBody.getTankDescription(), requestBody.getLocationOfStructure(),
                requestBody.getCode());
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

    /**
     *
     * @param requestBody
     * @param reportIndexID
     * @return
     */
    @Override
    public FormDTO saveNewFormTM7(FormTM7RequestBody requestBody, Long reportIndexID) {
        Optional<ReportIndex> reportIndex = reportIndexRepository.findById(reportIndexID);
        FormTM7 formTM7 = new FormTM7(requestBody.getName(), requestBody.getDescription(), requestBody.getCode());
        reportIndex.ifPresent(formTM7::setReportIndex);
        if (reportIndex.isEmpty()) {
            return null;
        }
        List<FrameNumber> frameNumberList =
                requestBody.getFrameNumberList().stream()
                        .map(frameNumberRequestBody -> {
                            FrameNumber frameNumberTM7 = new FrameNumber(
                                    null, frameNumberRequestBody.getName(),
                                    formTM7, null
                            );
                            List<MeasurementTM7> measurementTM7List =
                                    frameNumberRequestBody.getMeasurementTM7List().stream()
                                            .map(measurementTM7RequestBody -> {
                                        DetailMeasurement first =
                                                mapperUtils.mapperToDetailMeasurement(
                                                        measurementTM7RequestBody.getLowerPart());
                                        DetailMeasurement second =
                                                mapperUtils.mapperToDetailMeasurement(
                                                        measurementTM7RequestBody.getMidPart()
                                                );
                                        DetailMeasurement third =
                                                mapperUtils.mapperToDetailMeasurement(
                                                        measurementTM7RequestBody.getUpperPart()
                                                );
                                                return new MeasurementTM7(null, measurementTM7RequestBody.getItem(),
                                                        frameNumberTM7, second,
                                                        third, first);
                                            }).toList();
                            frameNumberTM7.setMeasurementTM7List(measurementTM7List);
                            return frameNumberTM7;
                        }).toList();
        formTM7.setFrameNumber(frameNumberList);
        try {
            FormTM7 formTM7Saved = formTM7Repository.save(formTM7);
            if (formTM7Saved.getId() > 0) {
                return mapperUtils.formTM7Mapper(formTM7);
            } else {
                return null;
            }
        } catch (Exception exception) {
            log.error("{}", exception.getMessage());
            return null;
        }
    }
    @Override
    public FormTM7DTO getFormTM7ByID(Long id) {
        Optional<FormTM7> formTM7 = formTM7Repository.findById(id);
        return formTM7.map(tm7 -> mapperUtils.formTM7Mapper(tm7)).orElse(null);
    }

    /**
     * @param requestBody
     * @param reportIndexID
     * @return
     */
    @Override
    public FormDTO saveNewFormTM6(FormTM6RequestBody requestBody, Long reportIndexID) {
        Optional<ReportIndex> reportIndex = reportIndexRepository.findById(reportIndexID);
        FormTM6 formTM6 = new FormTM6(requestBody.getStructuralMembers(),
                requestBody.getLocationOfStructure(), requestBody.getCode());
        reportIndex.ifPresent(formTM6::setReportIndex);
        List<StructuralDescriptionTM6> structuralDescriptionTM6List =
                requestBody.getStructuralDescriptionTM6List().stream()
                        .map(structuralDescriptionTM6RequestBody -> {
                            StructuralDescriptionTM6 structuralDescriptionTM6 = new StructuralDescriptionTM6(
                                    null, structuralDescriptionTM6RequestBody.getStructuralDescriptionTitle(),
                                    formTM6, null
                            );
                            List<MeasurementTM6> measurementTM6List =
                                    structuralDescriptionTM6RequestBody.getMeasurementTM6List().stream()
                                            .map(measurementTM6RequestBody -> {
                                                DetailMeasurement detailMeasurement =
                                                        mapperUtils.mapperToDetailMeasurement(
                                                                measurementTM6RequestBody.getDetailMeasurement());

                                                return new MeasurementTM6(null,
                                                        measurementTM6RequestBody.getDescription(),
                                                        measurementTM6RequestBody.getItem(),
                                                        detailMeasurement,
                                                        structuralDescriptionTM6);
                                            }).toList();
                            structuralDescriptionTM6.setMeasurementTM6List(measurementTM6List);
                            return structuralDescriptionTM6;
                        }).toList();
        formTM6.setStructuralDescriptionTM6List(structuralDescriptionTM6List);
        try {
            FormTM6 formTM6Saved = formTM6Repository.save(formTM6);
            if (formTM6Saved.getId() > 0) {
                return mapperUtils.formTM6Mapper(formTM6);
            } else {
                return null;
            }
        } catch (Exception exception) {
            log.error("{}", exception.getMessage());
            return null;
        }
    }

    /**
     * @param id
     * @param requestBody
     * @return
     */
    @Override
    public FormDTO updateFormTM1(Long id, FormTM1RequestBody requestBody) {
        Optional<FormTM1> formTM1Optional = formTM1Repository.findById(id);
        if (formTM1Optional.isPresent()) {
            FormTM1 formTM1 = formTM1Optional.get();
            formTM1.update(requestBody);
            measurementTM1Repository.deleteAll(formTM1.getMeasurementTM1List());
            List<MeasurementTM1> measurementTM1List = requestBody.getMeasurementTM1List()
                    .stream().map(requestBodyMTM1 -> {
                        MeasurementTM1 measurementTM1 = createMeasurementTM1(requestBodyMTM1);
                        measurementTM1.setFormTM1(formTM1);
                        return measurementTM1;
                    }).toList();
            formTM1.setMeasurementTM1List(measurementTM1List);
            return mapperUtils.formTM1Mapper(formTM1);
        }
        return null;
    }

    /**
     * @param id
     */
    @Override
    public Boolean deleteFormTM1(Long id) {
        formTM1Repository.deleteById(id);
        boolean exist = formTM1Repository.existsById(id);
        return !exist;
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Boolean isFormTM1Exist(Long id) {
        return formTM1Repository.existsById(id);
    }

    private MeasurementTM1 createMeasurementTM1(MeasurementTM1RequestBody requestBody) {
        DetailMeasurement after = new DetailMeasurement(requestBody.getAfterReadingMeasurementDetail().getOriginalThickness(),
                requestBody.getAfterReadingMeasurementDetail().getMaxAlwbDim(),
                requestBody.getAfterReadingMeasurementDetail().getGaugedP(),
                requestBody.getAfterReadingMeasurementDetail().getGaugedS(),
                requestBody.getAfterReadingMeasurementDetail().getPercent());

        DetailMeasurement forward = new DetailMeasurement(requestBody.getForwardReadingMeasurementDetail().getOriginalThickness(),
                requestBody.getForwardReadingMeasurementDetail().getMaxAlwbDim(),
                requestBody.getForwardReadingMeasurementDetail().getGaugedP(),
                requestBody.getForwardReadingMeasurementDetail().getGaugedS(),
                requestBody.getForwardReadingMeasurementDetail().getPercent());

        return new MeasurementTM1(null, requestBody.getPlatePosition(), requestBody.getNoOrLetter(), null, forward, after);
    }

    /**
     * @param id
     * @param requestBody
     * @return
     */
    @Override
    public DetailMeasurementDTO updateDetailMeasurement(Long id, DetailMeasurementRequestBody requestBody) {
        Optional<DetailMeasurement> detailMeasurementOptional = detailMeasurementRepository.findById(id);
        if (detailMeasurementOptional.isPresent()) {
            DetailMeasurement detailMeasurement = detailMeasurementOptional.get();
            detailMeasurement.update(requestBody);
            return mapperUtils.detailMeasurementMapper(detailMeasurement);
        }
        return null;
    }

    /**
     * @param id
     * @param requestBody
     * @return
     */
    @Override
    public MeasurementTM1DTO updateMeasurementTM1(Long id, MeasurementTM1RequestBody requestBody) {
        Optional<MeasurementTM1> measurementTM1Optional = measurementTM1Repository.findById(id);
        if (measurementTM1Optional.isPresent()) {
            MeasurementTM1 measurementTM1 = measurementTM1Optional.get();
            measurementTM1.update(requestBody);
            updateDetailMeasurement(
                    measurementTM1.getForwardReadingMeasurementDetail().getId(),
                    requestBody.getForwardReadingMeasurementDetail()
            );
            updateDetailMeasurement(
                    measurementTM1.getAfterReadingMeasurementDetail().getId(),
                    requestBody.getAfterReadingMeasurementDetail()
            );

        }
        return null;
    }

    private DetailMeasurement createNewDetailMeasurement(DetailMeasurementRequestBody requestBody) {
        return new DetailMeasurement(requestBody.getOriginalThickness(), requestBody.getMaxAlwbDim(),
                requestBody.getGaugedP(), requestBody.getGaugedS(), requestBody.getPercent());
    }

    /**
     *
     * @param id
     * @param requestBody
     * @return
     */
    @Override
    public FormDTO updateFormTM2(Long id, FormTM2RequestBody requestBody) {
        FormTM2 formTM2 = formTM2Repository.findById(id).orElse(null);
        if (formTM2 != null) {
            formTM2.update(requestBody);
            measurementTM2Repository.deleteAll(formTM2.getMeasurementTM2List());
            List<MeasurementTM2> measurementTM2List = requestBody.getMeasurementTM2List().stream()
                    .map(requestBodyMTM2 -> {
                        MeasurementTM2 measurementTM2 = createMeasurementTM2(requestBodyMTM2);
                        measurementTM2.setFormTM2(formTM2);
                        return measurementTM2;
                    }).toList();
            formTM2.setMeasurementTM2List(measurementTM2List);

            return mapperUtils.formTM2Mapper(formTM2);
        }
        return null;
    }

    private MeasurementTM2 createMeasurementTM2(MeasurementTM2RequestBody requestBody) {
        DetailMeasurement first =
                new DetailMeasurement(requestBody.getFirstTransverseSectionMeasurementDetailTM2().getOriginalThickness(),
                        requestBody.getFirstTransverseSectionMeasurementDetailTM2().getMaxAlwbDim(),
                        requestBody.getFirstTransverseSectionMeasurementDetailTM2().getGaugedP(),
                        requestBody.getFirstTransverseSectionMeasurementDetailTM2().getGaugedS(),
                        requestBody.getFirstTransverseSectionMeasurementDetailTM2().getPercent());
        DetailMeasurement second =
                new DetailMeasurement(requestBody.getSecondTransverseSectionMeasurementDetailTM2().getOriginalThickness(),
                        requestBody.getSecondTransverseSectionMeasurementDetailTM2().getMaxAlwbDim(),
                        requestBody.getSecondTransverseSectionMeasurementDetailTM2().getGaugedP(),
                        requestBody.getSecondTransverseSectionMeasurementDetailTM2().getGaugedS(),
                        requestBody.getSecondTransverseSectionMeasurementDetailTM2().getPercent());
        DetailMeasurement third =
                new DetailMeasurement(requestBody.getFirstTransverseSectionMeasurementDetailTM2().getOriginalThickness(),
                        requestBody.getFirstTransverseSectionMeasurementDetailTM2().getMaxAlwbDim(),
                        requestBody.getFirstTransverseSectionMeasurementDetailTM2().getGaugedP(),
                        requestBody.getFirstTransverseSectionMeasurementDetailTM2().getGaugedS(),
                        requestBody.getFirstTransverseSectionMeasurementDetailTM2().getPercent());

        return new MeasurementTM2(null, requestBody.getStrakePosition(), requestBody.getNoOrLetter(), null,
                first, second, third);
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Boolean deletedFormTM2(Long id) {
        formTM2Repository.deleteById(id);
        boolean isSuccess = !formTM2Repository.existsById(id);
        return isSuccess;
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Boolean isFormTM2Exist(Long id) {
        return formTM2Repository.existsById(id);
    }

    @Override
    public FormDTO updateFormTM3(Long id, FormTM3RequestBody requestBody) {
        FormTM3 formTM3 = formTM3Repository.findById(id).orElse(null);
        if (formTM3 != null) {
            formTM3.update(requestBody);
            measurementTM3Repository.deleteAll(formTM3.getMeasurementTM3List());
            List<MeasurementTM3> measurementTM3List = requestBody.getMeasurementTM3List().stream()
                    .map(requestBodyMTM3 -> {
                        MeasurementTM3 measurementTM3 = createMeasurementTM3(requestBodyMTM3);
                        measurementTM3.setFormTM3(formTM3);
                        return measurementTM3;
                    }).toList();
            formTM3.setMeasurementTM3List(measurementTM3List);

            return mapperUtils.formTM3Mapper(formTM3);
        }
        return null;
    }

    private MeasurementTM3 createMeasurementTM3(MeasurementTM3RequestBody requestBody) {
        DetailMeasurement first =
                new DetailMeasurement(requestBody.getFirstTransverseSectionMeasurementDetail().getOriginalThickness(),
                        requestBody.getFirstTransverseSectionMeasurementDetail().getMaxAlwbDim(),
                        requestBody.getFirstTransverseSectionMeasurementDetail().getGaugedP(),
                        requestBody.getFirstTransverseSectionMeasurementDetail().getGaugedS(),
                        requestBody.getFirstTransverseSectionMeasurementDetail().getPercent());
        DetailMeasurement second =
                new DetailMeasurement(requestBody.getSecondTransverseSectionMeasurementDetail().getOriginalThickness(),
                        requestBody.getSecondTransverseSectionMeasurementDetail().getMaxAlwbDim(),
                        requestBody.getSecondTransverseSectionMeasurementDetail().getGaugedP(),
                        requestBody.getSecondTransverseSectionMeasurementDetail().getGaugedS(),
                        requestBody.getSecondTransverseSectionMeasurementDetail().getPercent());
        DetailMeasurement third =
                new DetailMeasurement(requestBody.getThirdTransverseSectionMeasurementDetail().getOriginalThickness(),
                        requestBody.getThirdTransverseSectionMeasurementDetail().getMaxAlwbDim(),
                        requestBody.getThirdTransverseSectionMeasurementDetail().getGaugedP(),
                        requestBody.getThirdTransverseSectionMeasurementDetail().getGaugedS(),
                        requestBody.getThirdTransverseSectionMeasurementDetail().getPercent());

        return new MeasurementTM3(null, requestBody.getStructuralMember(), requestBody.getNoOrLetter(), null,
                first, second, third);
    }

    @Override
    public Boolean deletedFormTM3(Long id) {
        formTM3Repository.deleteById(id);
        boolean isSuccess = !formTM3Repository.existsById(id);
        return isSuccess;
    }

    @Override
    public Boolean isFormTM3Exist(Long id) {
        return formTM3Repository.existsById(id);
    }

    @Override
    public FormDTO updateFormTM5(Long id, FormTM5RequestBody requestBody) {
        FormTM5 formTM5 = formTM5Repository.findById(id).orElse(null);
        if (formTM5 != null) {
            formTM5.update(requestBody);
            measurementTM5Repository.deleteAll(formTM5.getMeasurementTM5List());
            List<MeasurementTM5> measurementTM5List = requestBody.getMeasurementTM5List().stream()
                    .map(requestBodyMTM5 -> {
                        MeasurementTM5 measurementTM5 = createMeasurementTM5(requestBodyMTM5);
                        measurementTM5.setFormTM5(formTM5);
                        return measurementTM5;
                    }).toList();
            formTM5.setMeasurementTM5List(measurementTM5List);

            return mapperUtils.formTM5Mapper(formTM5);
        }
        return null;
    }
    private MeasurementTM5 createMeasurementTM5(MeasurementTM5RequestBody requestBody) {
        DetailMeasurement first =
                new DetailMeasurement(requestBody.getMeasurementDetail().getOriginalThickness(),
                        requestBody.getMeasurementDetail().getMaxAlwbDim(),
                        requestBody.getMeasurementDetail().getGaugedP(),
                        requestBody.getMeasurementDetail().getGaugedS(),
                        requestBody.getMeasurementDetail().getPercent());

        return new MeasurementTM5(null, requestBody.getStructuralComponentType(), requestBody.getStructuralComponent(), null, first);
    }
    @Override
    public Boolean deletedFormTM5(Long id) {
        formTM5Repository.deleteById(id);
        boolean isSuccess = !formTM5Repository.existsById(id);
        return isSuccess;
    }

    @Override
    public Boolean isFormTM5Exist(Long id) {
        return formTM5Repository.existsById(id);
    }

    @Override
    public FormDTO updateFormTM7(Long id, FormTM7RequestBody requestBody) {
        FormTM7 formTM7 = formTM7Repository.findById(id).orElse(null);
        if (formTM7 != null) {
            formTM7.update(requestBody);
            frameNumberRepository.deleteAll(formTM7.getFrameNumber());
            formTM7.setFrameNumber(new ArrayList<>());
            List<FrameNumber> frameNumberList =
                    requestBody.getFrameNumberList().stream()
                            .map(frameNumberRequestBody -> {
                                FrameNumber frameNumberTM7 = new FrameNumber(
                                        null, frameNumberRequestBody.getName(),
                                        formTM7, null
                                );
                                List<MeasurementTM7> measurementTM7List =
                                        frameNumberRequestBody.getMeasurementTM7List().stream()
                                                .map(measurementTM7RequestBody -> {
                                                    DetailMeasurement first =
                                                            mapperUtils.mapperToDetailMeasurement(
                                                                    measurementTM7RequestBody.getLowerPart());
                                                    DetailMeasurement second =
                                                            mapperUtils.mapperToDetailMeasurement(
                                                                    measurementTM7RequestBody.getMidPart()
                                                            );
                                                    DetailMeasurement third =
                                                            mapperUtils.mapperToDetailMeasurement(
                                                                    measurementTM7RequestBody.getUpperPart()
                                                            );
                                                    return new MeasurementTM7(null, measurementTM7RequestBody.getItem(),
                                                            frameNumberTM7, second,
                                                            third, first);
                                                }).toList();
                                frameNumberTM7.setMeasurementTM7List(measurementTM7List);
                                return frameNumberTM7;
                            }).toList();
            formTM7.setFrameNumber(frameNumberList);
            return mapperUtils.formTM7Mapper(formTM7);
        }

        return null;
    }
    @Override
    public FormDTO updateFormTM6(Long id, FormTM6RequestBody requestBody) {
        FormTM6 formTM6 = formTM6Repository.findById(id).orElse(null);
        if (formTM6 != null) {
            formTM6.update(requestBody);
            structuralDescriptionTM6Repository.deleteAll(formTM6.getStructuralDescriptionTM6List());
            formTM6.setStructuralDescriptionTM6List(new ArrayList<>());
            List<StructuralDescriptionTM6> structuralDescriptionTM6List =
                    requestBody.getStructuralDescriptionTM6List().stream()
                            .map(structuralDescriptionTM6RequestBody -> {
                                StructuralDescriptionTM6 structuralDescriptionTM6 = new StructuralDescriptionTM6(
                                        null, structuralDescriptionTM6RequestBody.getStructuralDescriptionTitle(),
                                        formTM6, null
                                );
                                List<MeasurementTM6> measurementTM6List =
                                        structuralDescriptionTM6RequestBody.getMeasurementTM6List().stream()
                                                .map(measurementTM6RequestBody -> {
                                                    DetailMeasurement detailMeasurement =
                                                            mapperUtils.mapperToDetailMeasurement(
                                                                    measurementTM6RequestBody.getDetailMeasurement());

                                                    return new MeasurementTM6(null,
                                                            measurementTM6RequestBody.getDescription(),
                                                            measurementTM6RequestBody.getItem(),
                                                            detailMeasurement,
                                                            structuralDescriptionTM6);
                                                }).toList();
                                structuralDescriptionTM6.setMeasurementTM6List(measurementTM6List);
                                return structuralDescriptionTM6;
                            }).toList();
            formTM6.setStructuralDescriptionTM6List(structuralDescriptionTM6List);
            return mapperUtils.formTM6Mapper(formTM6);
        }
        return null;
    }
    @Override
    public FormDTO updateFormTM4(Long id, FormTM4RequestBody requestBody) {
        FormTM4 formTM4 = formTM4Repository.findById(id).orElse(null);
        if (formTM4 != null) {
            formTM4.update(requestBody);
            structuralMemberDetailsTM4Repository.deleteAll(formTM4.getStructuralMemberTM4List());
            formTM4.setStructuralMemberTM4List(new ArrayList<>());
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
            return mapperUtils.formTM4Mapper(formTM4);
        }
        return null;
    }

}