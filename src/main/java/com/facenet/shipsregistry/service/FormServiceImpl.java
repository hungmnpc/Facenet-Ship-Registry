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
    MapperUtils mapperUtils;

    /**
     * @param requestBody
     * @return
     */
    @Override
    public FormDTO saveNewFormTM1(FormTM1RequestBody requestBody, Long reportIndexID) {

        Optional<ReportIndex> reportIndex = reportIndexRepository.findById(reportIndexID);
        FormTM1 formTM1 = new FormTM1(null, requestBody.getStrakePosition(), null, null, null);
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
    @Override
    public FormTM1DTO getFormTM1ByID(Long id) {
        Optional<FormTM1> formTM1 = formTM1Repository.findById(id);
        return formTM1.map(tm1 -> mapperUtils.formTM1Mapper(tm1)).orElse(null);
    }
    @Override
    public FormDTO saveNewFormTM5(FormTM5RequestBody requestBody, Long reportIndexID) {

        Optional<ReportIndex> reportIndex = reportIndexRepository.findById(reportIndexID);
        FormTM5 formTM5 = new FormTM5(null, requestBody.getDescription(),
                requestBody.getName(), requestBody.getLocationOfStructure(),
                requestBody.getTankHolDescription(),requestBody.getFrameNo(),
                null,null);;
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
        FormTM3 formTM3 = new FormTM3(null, requestBody.getFirstFrameNo(), requestBody.getSecondFrameNo()
                , requestBody.getThirdFrameNo(), null, null);
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

    /**
     *
     * @param requestBody
     * @param reportIndexID
     * @return
     */
    @Override
    public FormDTO saveNewFormTM7(FormTM7RequestBody requestBody, Long reportIndexID) {
        Optional<ReportIndex> reportIndex = reportIndexRepository.findById(reportIndexID);
        FormTM7 formTM7 = new FormTM7(requestBody.getName(), requestBody.getDescription());
        reportIndex.ifPresent(formTM7::setReportIndex);
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
        FormTM6 formTM6 = new FormTM6(requestBody.getStructuralMembers(), requestBody.getLocationOfStructure());
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
}