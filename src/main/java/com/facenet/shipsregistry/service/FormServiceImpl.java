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
    MeasurementTM4Repository measurementTM4Repository;

    @Autowired
    MeasurementTM6Repository measurementTM6Repository;

    @Autowired
    MeasurementTM5Repository measurementTM5Repository;

    @Autowired
    MeasurementTM7Repository measurementTM7Repository;

    @Autowired
    FrameNumberRepository frameNumberRepository;

    @Autowired
    StructuralDescriptionTM6Repository structuralDescriptionTM6Repository;

    @Autowired
    StructuralMemberTM4Repository structuralMemberTM4Repository;

    @Autowired
    StructuralTM5Repository structuralTM5Repository;

    @Autowired
    FormDAO formDAO;

    @Autowired
    MapperUtils mapperUtils;

    /**
     * @param requestBody
     * @return
     */
    @Override
    public FormDTO saveNewFormTM1(FormTM1RequestBody requestBody, Long reportIndexID) {
        Optional<ReportIndex> reportIndex = reportIndexRepository.findById(reportIndexID);
        if (reportIndex.isEmpty()) {
            return null;
        }
        FormTM1 formTM1 = new FormTM1(null,requestBody.getCode(),
                requestBody.getStrakePosition(), reportIndex.get().currentFormIndex(),null, null);
        reportIndex.ifPresent(formTM1::setReportIndex);
        List<MeasurementTM1> measurementTM1List =
                requestBody.getMeasurementTM1List().stream()
                        .map(measurementTM1RequestBody -> {
                            MeasurementTM1 measurementTM1 = createMeasurementTM1(measurementTM1RequestBody);
                            measurementTM1.setFormTM1(formTM1);
                            measurementTM1Repository.save(measurementTM1);
                            return measurementTM1;
                        }).toList();
        formTM1.setMeasurementTM1List(measurementTM1List);
        FormTM1 formTM1Saved = formTM1Repository.save(formTM1);
        if (formTM1Saved.getId() > 0) {
            return mapperUtils.formTM1Mapper(formTM1Saved);
        } else  {
            return null;
        }
    }

    @Override
    public FormTM1DTO getFormTM1ByID(Long id) {
        FormTM1 formTM1 = formTM1Repository.getById(id);
        if (formTM1 != null) {
            return mapperUtils.formTM1Mapper(formTM1);
        } else {
            return null;
        }
    }

    @Override
    public FormTM2DTO getFormTM2ByID(Long id){
       FormTM2 formTM2=formTM2Repository.findById(id).orElse(null);
       if(formTM2 != null) {
           return mapperUtils.formTM2Mapper(formTM2);
       }else {
           return null;
       }
    }
    @Override
    public FormTM3DTO getFormTM3ByID(Long id){
        FormTM3 formTM3=formTM3Repository.findById(id).orElse(null);
        if(formTM3 != null) {
            return mapperUtils.formTM3Mapper(formTM3);
        }else {
            return null;
        }
    }

    @Override
    public FormTM4DTO getFormTM4ByID(Long id){
        FormTM4 formTM4 = formTM4Repository.findById(id).orElse(null);
        if(formTM4 != null) {
            return mapperUtils.formTM4Mapper(formTM4);
        }else {
            return null;
        }
    }

    @Override
    public FormTM5DTO getFormTM5ByID(Long id){
        FormTM5 formTM5 = formTM5Repository.findById(id).orElse(null);
        if (formTM5 != null) {
            return mapperUtils.formTM5Mapper(formTM5);
        } else {
            return null;
        }
    }

    @Override
    public FormTM6DTO getFormTM6ByID(Long id) {
        Optional<FormTM6> formTM6 = formTM6Repository.findById(id);
        if (formTM6.isPresent()) {
            return mapperUtils.formTM6Mapper(formTM6.get());
        } else {
            return null;
        }
    }

    @Override
    public FormTM7DTO getFormTM7ByID(Long id) {
        FormTM7 formTM7 = formTM7Repository.findById(id).orElse(null);
        if (formTM7 != null) {
            return mapperUtils.formTM7Mapper(formTM7);
        } else {
            return null;
        }
    }



    @Override
    public FormDTO saveNewFormTM5(FormTM5RequestBody requestBody, Long reportIndexID) {
        Optional<ReportIndex> reportIndex = reportIndexRepository.findById(reportIndexID);
        if (reportIndex.isEmpty()) {
            return null;
        }
        FormTM5 formTM5 = new FormTM5(null,
                requestBody.getLocationOfStructure(),
                requestBody.getTankHolDescription(),requestBody.getFrameNo(),
                requestBody.getCode(),null, null, reportIndex.get().currentFormIndex());;
        reportIndex.ifPresent(formTM5::setReportIndex);
        List<StructuralTM5> structuralTM5List = requestBody.getStructuralTM5List().stream()
                .map(structuralTM5RequestBody -> {
                    StructuralTM5 structuralTM5 = createStructuralTM5(structuralTM5RequestBody);
                    structuralTM5.setFormTM5(formTM5);
                    structuralTM5Repository.save(structuralTM5);
                    return structuralTM5;
                }).toList();
        formTM5.setStructuralTM5List(structuralTM5List);
        FormTM5 formTM5Saved = formTM5Repository.save(formTM5);
        if (formTM5Saved.getId() > 0) {
            return mapperUtils.formTM5Mapper(formTM5Saved);
        } else  {
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
        if(reportIndex.isEmpty()) {
            return null;
        }
        FormTM3 formTM3 = new FormTM3( requestBody.getCode(), requestBody.getFirstFrameNo(),
                requestBody.getSecondFrameNo(), requestBody.getThirdFrameNo());
        formTM3.setFormIndex(reportIndex.get().currentFormIndex());
        reportIndex.ifPresent(formTM3::setReportIndex);
        List<MeasurementTM3> measurementTM3List =
                requestBody.getMeasurementTM3List().stream()
                        .map(measurementTM3RequestBody -> {
                            MeasurementTM3 measurementTM3 = createMeasurementTM3(measurementTM3RequestBody);
                            measurementTM3.setFormTM3(formTM3);
                            measurementTM3Repository.save(measurementTM3);
                            return measurementTM3;
                        }).toList();
        formTM3.setMeasurementTM3List(measurementTM3List);
            FormTM3 formTM3Saved = formTM3Repository.save(formTM3);
        if (formTM3Saved.getId() > 0) {
            return mapperUtils.formTM3Mapper(formTM3Saved);
        } else  {
            return null;
        }
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
        if (reportIndex.isEmpty()) {
            return null;
        }
        FormTM2 formTM2 = new FormTM2(
                requestBody.getName(), requestBody.getFirstFrameNoTM2(),
                requestBody.getSecondFrameNoTM2(), requestBody.getThirdFrameNoTM2(),
                requestBody.getCode()
        );
        formTM2.setFormIndex(reportIndex.get().currentFormIndex());
        reportIndex.ifPresent(formTM2::setReportIndex);
        List<MeasurementTM2> measurementTM2List = requestBody.getMeasurementTM2List().stream()
                .map(measurementTM2RequestBody -> {
                    MeasurementTM2 measurementTM2 = createMeasurementTM2(measurementTM2RequestBody);
                    measurementTM2.setFormTM2(formTM2);
                    measurementTM2Repository.save(measurementTM2);
                    return measurementTM2;
                }).toList();
        formTM2.setMeasurementTM2List(measurementTM2List);
        FormTM2 formTM2Saved = formTM2Repository.save(formTM2);
        if (formTM2Saved.getId() > 0) {
            return mapperUtils.formTM2Mapper(formTM2Saved);
        }
        return null;
    }

    /**
     *
     * @param requestBody
     * @return
     */
    private MeasurementTM4 createNewMeasurementTM4(MeasurementTM4RequestBody requestBody) {
        DetailMeasurement detailMeasurement = createNewDetailMeasurement(requestBody.getDetailMeasurement());
        return new MeasurementTM4(null, requestBody.getStructuralMember(),
                requestBody.getItem(), detailMeasurement, null);
    }

    /**
     *
     * @param requestBody
     * @return
     */
    private StructuralMemberTM4 createNewStructuralMemberTM4(StructuralMemberTM4RequestBody requestBody) {
        StructuralMemberTM4 structuralMemberTM4 = new StructuralMemberTM4(null,
                requestBody.getStructuralMemberTitle(),
                null, null);
        List<MeasurementTM4> measurementTM4List = requestBody.getMeasurementTM4List().stream()
                .map(measurementTM4RequestBody -> {
                    MeasurementTM4 measurementTM4 = createNewMeasurementTM4(measurementTM4RequestBody);
                    measurementTM4.setStructuralMemberTM4(structuralMemberTM4);
                    measurementTM4Repository.save(measurementTM4);
                    return measurementTM4;
                }).toList();
        structuralMemberTM4.setMeasurementTM4List(measurementTM4List);
        return structuralMemberTM4;
    }

    /**
     * @param requestBody
     * @param reportIndexID
     * @return
     */
    @Override
    public FormDTO saveNewFormTM4(FormTM4RequestBody requestBody, Long reportIndexID) {
        Optional<ReportIndex> reportIndex = reportIndexRepository.findById(reportIndexID);
        if (reportIndex.isEmpty()) {
            return null;
        }
        FormTM4 formTM4 = new FormTM4(requestBody.getTankDescription(), requestBody.getLocationOfStructure(),
                requestBody.getCode());
        formTM4.setFormIndex(reportIndex.get().currentFormIndex());
        reportIndex.ifPresent(formTM4::setReportIndex);
        List<StructuralMemberTM4> structuralMemberTM4List =
                requestBody.getStructuralMemberTM4List().stream()
                        .map(structuralMemberTM4RequestBody -> {
                            StructuralMemberTM4 structuralMemberTM4 =
                                    createNewStructuralMemberTM4(structuralMemberTM4RequestBody);
                            structuralMemberTM4.setFormTM4(formTM4);
                            structuralMemberTM4Repository.save(structuralMemberTM4);
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
     * @return
     */
    private FrameNumber createNewFrameNumber(FrameNumberRequestBody requestBody) {
        FrameNumber frameNumber = new FrameNumber(null, requestBody.getName(),
                null, null);
        List<MeasurementTM7> measurementTM7Lists = requestBody.getMeasurementTM7List().stream()
                .map(measurementTM7RequestBody -> {
                    MeasurementTM7 measurementTM7 = createNewMeasurementTM7(measurementTM7RequestBody);
                    measurementTM7.setFrameNumber(frameNumber);
                    measurementTM7Repository.save(measurementTM7);
                    return measurementTM7;
                }).toList();
        frameNumber.setMeasurementTM7List(measurementTM7Lists);
        return frameNumber;
    }

    private MeasurementTM7 createNewMeasurementTM7(MeasurementTM7RequestBody requestBody) {
        DetailMeasurement upper = createNewDetailMeasurement(requestBody.getUpperPart());
        DetailMeasurement mid = createNewDetailMeasurement(requestBody.getMidPart());
        DetailMeasurement lower = createNewDetailMeasurement(requestBody.getLowerPart());
        return new MeasurementTM7(null, requestBody.getItem(), null, upper, mid, lower);
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
        if (reportIndex.isEmpty()) {
            return null;
        }
        FormTM7 formTM7 = new FormTM7(requestBody.getName(), requestBody.getCode());
        reportIndex.ifPresent(formTM7::setReportIndex);
        formTM7.setFormIndex(reportIndex.get().currentFormIndex());
        List<FrameNumber> frameNumberList =
                requestBody.getFrameNumberList().stream()
                        .map(frameNumberRequestBody -> {
                            FrameNumber frameNumberTM7 = createNewFrameNumber(frameNumberRequestBody);
                            frameNumberTM7.setFormTM7(formTM7);
                            frameNumberRepository.save(frameNumberTM7);
                            return frameNumberTM7;
                        }).toList();
        formTM7.setFrameNumber(frameNumberList);
        FormTM7 formTM7Saved = formTM7Repository.save(formTM7);
        if (formTM7Saved.getId() > 0) {
            return mapperUtils.formTM7Mapper(formTM7);
        } else {
            return null;
        }
    }


    /**
     *
     * @param requestBody
     * @return
     */
    private MeasurementTM6 createNewMeasurementTM6 (MeasurementTM6RequestBody requestBody) {
        DetailMeasurement detailMeasurement = createNewDetailMeasurement(requestBody.getDetailMeasurement());
        return new MeasurementTM6(null, requestBody.getDescription(),
                requestBody.getItem(),detailMeasurement,null);
    }

    /**
     *
     * @param requestBody
     * @return
     */
    private StructuralDescriptionTM6 createNewStructuralDesTM6(StructuralDescriptionTM6RequestBody requestBody) {
        StructuralDescriptionTM6 structuralDescriptionTM6 =
                new StructuralDescriptionTM6(
                        null, requestBody.getStructuralDescriptionTitle(),
                        null, null);
        List<MeasurementTM6> measurementTM6List = requestBody.getMeasurementTM6List().stream()
                .map(measurementTM6RequestBody -> {
                    MeasurementTM6 measurementTM6 = createNewMeasurementTM6(measurementTM6RequestBody);
                    measurementTM6.setStructuralDescriptionTM6(structuralDescriptionTM6);
                    measurementTM6Repository.save(measurementTM6);
                    return measurementTM6;
                }).toList();
        structuralDescriptionTM6.setMeasurementTM6List(measurementTM6List);
        return structuralDescriptionTM6;
    }

    /**
     * @param requestBody
     * @param reportIndexID
     * @return
     */
    @Override
    public FormDTO saveNewFormTM6(FormTM6RequestBody requestBody, Long reportIndexID) {
        Optional<ReportIndex> reportIndex = reportIndexRepository.findById(reportIndexID);
        if (reportIndex.isEmpty()) {
            return null;
        }
        FormTM6 formTM6 = new FormTM6(requestBody.getStructuralMembers(),
                requestBody.getLocationOfStructure(), requestBody.getCode());
        reportIndex.ifPresent(formTM6::setReportIndex);
        formTM6.setFormIndex(reportIndex.get().currentFormIndex());
        List<StructuralDescriptionTM6> structuralDescriptionTM6List =
                requestBody.getStructuralDescriptionTM6List().stream()
                        .map(structuralDescriptionTM6RequestBody -> {
                            StructuralDescriptionTM6 structuralDescriptionTM6 =
                                    createNewStructuralDesTM6(structuralDescriptionTM6RequestBody);
                            structuralDescriptionTM6.setFormTM6(formTM6);
                            structuralDescriptionTM6Repository.save(structuralDescriptionTM6);
                            return structuralDescriptionTM6;
                        }).toList();
        formTM6.setStructuralDescriptionTM6List(structuralDescriptionTM6List);
        FormTM6 formTM6Saved = formTM6Repository.save(formTM6);
        if (formTM6Saved.getId() > 0) {
            return mapperUtils.formTM6Mapper(formTM6);
        } else {
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
                        measurementTM1Repository.save(measurementTM1);
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
        FormTM1 formTM1 = formTM1Repository.findById(id).orElse(null);
        if (formTM1 != null) {
            ReportIndexDTO reportIndexDTO = mapperUtils.reportIndexMapper(formTM1.getReportIndex());
            updateAfterDeleteForm(reportIndexDTO, formTM1.getFormIndex());
            formTM1.getReportIndex().getFormTM1List().remove(formTM1);
        }
        formTM1Repository.deleteById(id);
        boolean exist = formTM1Repository.existsById(id);
        return !exist;
    }

    /**
     *
     * @param id
     * @return
     */
    @Override
    public Boolean deleteFormTM4(Long id) {
        FormTM4 formTM4 = formTM4Repository.findById(id).orElse(null);
        if (formTM4 != null) {
            ReportIndexDTO reportIndexDTO = mapperUtils.reportIndexMapper(formTM4.getReportIndex());
            updateAfterDeleteForm(reportIndexDTO, formTM4.getFormIndex());
            formTM4.getReportIndex().getFormTM4List().remove(formTM4);
        }
        formTM4Repository.deleteById(id);
        boolean exist = formTM4Repository.existsById(id);
        return !exist;
    }

    @Override
    public Boolean deleteFormTM6(Long id) {
        FormTM6 formTM6 = formTM6Repository.findById(id).orElse(null);
        if (formTM6 != null) {
            ReportIndexDTO reportIndexDTO = mapperUtils.reportIndexMapper(formTM6.getReportIndex());
            updateAfterDeleteForm(reportIndexDTO, formTM6.getFormIndex());
            formTM6.getReportIndex().getFormTM6List().remove(formTM6);
        }
        formTM6Repository.deleteById(id);
        boolean exist = formTM6Repository.existsById(id);
        return !exist;
    }

    @Override
    public Boolean deleteFormTM7(Long id) {
        FormTM7 formTM7 = formTM7Repository.findById(id).orElse(null);
        if (formTM7 != null) {
            ReportIndexDTO reportIndexDTO = mapperUtils.reportIndexMapper(formTM7.getReportIndex());
            updateAfterDeleteForm(reportIndexDTO, formTM7.getFormIndex());
            formTM7.getReportIndex().getFormTM7List().remove(formTM7);
        }
        formTM7Repository.deleteById(id);
        boolean exist = formTM7Repository.existsById(id);
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

    @Override
    public Boolean isFormTM4Exist(Long id) {
        return formTM4Repository.existsById(id);
    }

    @Override
    public Boolean isFormTM6Exist(Long id) {
        return formTM6Repository.existsById(id);
    }

    @Override
    public Boolean isFormTM7Exist(Long id) {
        return formTM7Repository.existsById(id);
    }
    private MeasurementTM1 createMeasurementTM1(MeasurementTM1RequestBody requestBody) {
        DetailMeasurement after = createNewDetailMeasurement(requestBody.getAfterReadingMeasurementDetail());
        DetailMeasurement forward = createNewDetailMeasurement(requestBody.getForwardReadingMeasurementDetail());
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
                        measurementTM2Repository.save(measurementTM2);
                        return measurementTM2;
                    }).toList();
            formTM2.setMeasurementTM2List(measurementTM2List);

            return mapperUtils.formTM2Mapper(formTM2);
        }
        return null;
    }

    private MeasurementTM2 createMeasurementTM2(MeasurementTM2RequestBody requestBody) {
        DetailMeasurement first = createNewDetailMeasurement(requestBody.getFirstTransverseSectionMeasurementDetailTM2());
        DetailMeasurement second = createNewDetailMeasurement(requestBody.getSecondTransverseSectionMeasurementDetailTM2());
        DetailMeasurement third = createNewDetailMeasurement(requestBody.getThirdTransverseSectionMeasurementDetailTM2());
        return new MeasurementTM2(null, requestBody.getStrakePosition(), requestBody.getNoOrLetter(), null,
                first, second, third);
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Boolean deletedFormTM2(Long id) {
        FormTM2 formTM2 = formTM2Repository.findById(id).orElse(null);
        if (formTM2 != null) {
            ReportIndexDTO reportIndexDTO = mapperUtils.reportIndexMapper(formTM2.getReportIndex());
            updateAfterDeleteForm(reportIndexDTO, formTM2.getFormIndex());
            formTM2.getReportIndex().getFormTM2List().remove(formTM2);
        }
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
                        measurementTM3Repository.save(measurementTM3);
                        return measurementTM3;
                    }).toList();
            formTM3.setMeasurementTM3List(measurementTM3List);

            return mapperUtils.formTM3Mapper(formTM3);
        }
        return null;
    }

    private MeasurementTM3 createMeasurementTM3(MeasurementTM3RequestBody requestBody) {
        DetailMeasurement first =
                createNewDetailMeasurement(requestBody.getFirstTransverseSectionMeasurementDetail());
        DetailMeasurement second =
                createNewDetailMeasurement(requestBody.getSecondTransverseSectionMeasurementDetail());
        DetailMeasurement third =
                createNewDetailMeasurement(requestBody.getThirdTransverseSectionMeasurementDetail());
        return new MeasurementTM3(null, requestBody.getStructuralMember(), requestBody.getNoOrLetter(), null,
                first, second, third);
    }

    @Override
    public Boolean deletedFormTM3(Long id) {
        FormTM3 formTM3 = formTM3Repository.findById(id).orElse(null);
        if (formTM3 != null) {
            ReportIndexDTO reportIndexDTO = mapperUtils.reportIndexMapper(formTM3.getReportIndex());
            updateAfterDeleteForm(reportIndexDTO, formTM3.getFormIndex());
            formTM3.getReportIndex().getFormTM3List().remove(formTM3);
        }
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
            structuralTM5Repository.deleteAll(formTM5.getStructuralTM5List());
            List<StructuralTM5> structuralTM5List = requestBody.getStructuralTM5List().stream()
                    .map(structuralTM5RequestBody -> {
                        StructuralTM5 structuralTM5 = createStructuralTM5(structuralTM5RequestBody);
                        structuralTM5.setFormTM5(formTM5);
                        structuralTM5Repository.save(structuralTM5);
                        return structuralTM5;
                    }).toList();
            formTM5.setStructuralTM5List(structuralTM5List);
            return mapperUtils.formTM5Mapper(formTM5);
        }
        return null;
    }

    private MeasurementTM5 createMeasurementTM5(MeasurementTM5RequestBody requestBody) {
        DetailMeasurement detailMeasurement =
                createNewDetailMeasurement(requestBody.getMeasurementDetail());
        return new MeasurementTM5(null, requestBody.getStructuralComponentType(),
                requestBody.getItem(), null, detailMeasurement);
    }

    private StructuralTM5 createStructuralTM5(StructuralTM5RequestBody structuralTM5RequestBody) {
        StructuralTM5 structuralTM5 = new StructuralTM5(null,
                structuralTM5RequestBody.getName(), null, null);
        List<MeasurementTM5> measurementTM5List = structuralTM5RequestBody.getMeasurementTM5List()
                .stream().map(
                        measurementTM5RequestBody -> {
                            MeasurementTM5 measurementTM5 = createMeasurementTM5(measurementTM5RequestBody);
                            measurementTM5.setStructuralTM5(structuralTM5);
                            measurementTM5Repository.save(measurementTM5);
                            return measurementTM5;
                        }
                )
                .toList();
        structuralTM5.setMeasurementTM5List(measurementTM5List);
        return structuralTM5;
    }

    @Override
    public Boolean deletedFormTM5(Long id) {
        FormTM5 formTM5 = formTM5Repository.findById(id).orElse(null);
        if (formTM5 != null) {
            ReportIndexDTO reportIndexDTO = mapperUtils.reportIndexMapper(formTM5.getReportIndex());
            updateAfterDeleteForm(reportIndexDTO, formTM5.getFormIndex());
            formTM5.getReportIndex().getFormTM5List().remove(formTM5);
        }
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
                                FrameNumber frameNumberTM7 = createNewFrameNumber(frameNumberRequestBody);
                                frameNumberTM7.setFormTM7(formTM7);
                                frameNumberRepository.save(frameNumberTM7);
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
                                StructuralDescriptionTM6 structuralDescriptionTM6 =
                                        createNewStructuralDesTM6(structuralDescriptionTM6RequestBody);
                                structuralDescriptionTM6.setFormTM6(formTM6);
                                structuralDescriptionTM6Repository.save(structuralDescriptionTM6);
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
            structuralMemberTM4Repository.deleteAll(formTM4.getStructuralMemberTM4List());
            formTM4.setStructuralMemberTM4List(new ArrayList<>());
            List<StructuralMemberTM4> structuralMemberTM4List =
                    requestBody.getStructuralMemberTM4List().stream()
                            .map(structuralMemberTM4RequestBody -> {
                                StructuralMemberTM4 structuralMemberTM4 =
                                        createNewStructuralMemberTM4(structuralMemberTM4RequestBody);
                                structuralMemberTM4.setFormTM4(formTM4);
                                structuralMemberTM4Repository.save(structuralMemberTM4);
                                return structuralMemberTM4;
                            }).toList();
            formTM4.setStructuralMemberTM4List(structuralMemberTM4List);
            return mapperUtils.formTM4Mapper(formTM4);
        }
        return null;
    }

    /**
     * @param partId
     * @param formIndex
     * @return
     */
    @Override
    public Boolean deletedFormUsingPart(Long partId, Integer formIndex) {
        ReportIndex reportIndex = reportIndexRepository.findById(partId).orElse(null);
        if (reportIndex == null) {
            return false;
        }
        ReportIndexDTO reportIndexDTO = mapperUtils.reportIndexMapper(reportIndex);
        FormDTO formDTO = reportIndexDTO.getFormList().stream()
                .filter(form -> form.getFormIndex().equals(formIndex))
                .findFirst().orElse(null);
        Boolean isDeleted = false;
        if (formDTO != null) {
            switch (formDTO.getClass().getSimpleName()) {
                case "FormTM1DTO":
                    FormTM1 formTM1 = formTM1Repository.findById(formDTO.getId()).orElse(null);
                    if (formTM1 != null) {
                        formTM1Repository.delete(formTM1);
                        reportIndex.getFormTM1List().remove(formTM1);
                        isDeleted = !formTM1Repository.existsById((formDTO.getId()));
                    }
                    break;
                case "FormTM2DTO":
                    FormTM2 formTM2 = formTM2Repository.findById(formDTO.getId()).orElse(null);
                    if (formTM2 != null) {
                        formTM2Repository.delete(formTM2);
                        reportIndex.getFormTM2List().remove(formTM2);
                        isDeleted = !formTM2Repository.existsById((formDTO.getId()));
                    }
                    break;
                case "FormTM3DTO":
                    FormTM3 formTM3 = formTM3Repository.findById(formDTO.getId()).orElse(null);
                    if (formTM3 != null) {
                        formTM3Repository.delete(formTM3);
                        reportIndex.getFormTM3List().remove(formTM3);
                        isDeleted = !formTM3Repository.existsById((formDTO.getId()));
                    }
                    break;
                case "FormTM4DTO":
                    FormTM4 formTM4 = formTM4Repository.findById(formDTO.getId()).orElse(null);
                    if (formTM4 != null) {
                        formTM4Repository.delete(formTM4);
                        reportIndex.getFormTM4List().remove(formTM4);
                        isDeleted = !formTM4Repository.existsById((formDTO.getId()));
                    }
                    break;
                case "FormTM5DTO":
                    FormTM5 formTM5 = formTM5Repository.findById(formDTO.getId()).orElse(null);
                    if (formTM5 != null) {
                        formTM5Repository.delete(formTM5);
                        reportIndex.getFormTM5List().remove(formTM5);
                        isDeleted = !formTM5Repository.existsById((formDTO.getId()));
                    }
                    break;
                case "FormTM6DTO":
                    FormTM6 formTM6 = formTM6Repository.findById(formDTO.getId()).orElse(null);
                    if (formTM6 != null) {
                        formTM6Repository.delete(formTM6);
                        reportIndex.getFormTM6List().remove(formTM6);
                        isDeleted = !formTM6Repository.existsById((formDTO.getId()));
                    }
                    break;
                case "FormTM7DTO":
                    FormTM7 formTM7 = formTM7Repository.findById(formDTO.getId()).orElse(null);
                    if (formTM7 != null) {
                        formTM7Repository.delete(formTM7);
                        reportIndex.getFormTM7List().remove(formTM7);
                        isDeleted = !formTM7Repository.existsById((formDTO.getId()));
                    }
                    break;
                default:
                    isDeleted = false;
            }
        }

        if (isDeleted) {
            reportIndexDTO.getFormList().removeIf(form -> form.getFormIndex().equals(formIndex));
            updateAfterDeleteForm(reportIndexDTO, formIndex);
        }
        return isDeleted;
    }

    private void updateAfterDeleteForm(ReportIndexDTO reportIndexDTO, Integer formIndexDeleted) {
        reportIndexDTO.getFormList().forEach(formDTO -> {
            if (formDTO.getFormIndex() > formIndexDeleted) {
                String formType = "form_tm";
                switch (formDTO.getClass().getSimpleName()) {
                    case "FormTM1DTO" -> {
                        formType += "1";
                    }
                    case "FormTM2DTO" -> {
                        formType += "2";
                    }
                    case "FormTM3DTO" -> {
                        formType += "3";
                    }
                    case "FormTM4DTO" -> {
                        formType += "4";
                    }
                    case "FormTM5DTO" -> {
                        formType += "5";
                    }
                    case "FormTM6DTO" -> {
                        formType += "6";
                    }
                    case "FormTM7DTO" -> {
                        formType += "7";
                    }
                    default -> {
                        return;
                    }
                }
                formDAO.updateFormIndex(formType, formDTO.getId(), formDTO.getFormIndex() - 1);
            }
        });
    }

    /**
     * @param formId
     * @param formType
     * @param newIndex
     */
    @Override
    public void updateFormIndex(Long formId, String formType, Integer newIndex) {
        formDAO.updateFormIndex(formType, formId, newIndex);
    }
}