package com.facenet.shipsregistry.utils;

import com.facenet.shipsregistry.entity.*;
import com.facenet.shipsregistry.modal.*;
import com.facenet.shipsregistry.repository.FormTM1Repository;
import com.facenet.shipsregistry.request.DetailMeasurementRequestBody;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author: hungdinh
 * Date created: 11/04/2023
 */
@Component
@Slf4j
public class MapperUtils {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private FormTM1Repository formTM1Repository;

    /**
     *
     * @param ship
     * @return
     */
    public ShipDTO shipMapper(Ship ship) {
        return modelMapper.map(ship, ShipDTO.class);
    }

    /**
     *
     * @param certificate
     * @return
     */
    public CertificateDTO certificateMapper(Certificate certificate) {
        return modelMapper.map(certificate, CertificateDTO.class);
    }

    /**
     *
     * @param generalParticulars
     * @return
     */
    public GeneralParticularsDTO generalParticularsMapper(GeneralParticulars generalParticulars) {

        GeneralParticularsDTO generalParticularsDTO =
                modelMapper.map(generalParticulars, GeneralParticularsDTO.class);
        generalParticularsDTO.setShipInfo(shipMapper(generalParticulars.getShip()));
        generalParticularsDTO.setCertificateDTO(certificateMapper(generalParticulars.getCertificate()));
        return generalParticularsDTO;
    }

    /**
     *
     * @param paramValue
     * @return
     */
    public ParamValueDTO paramValueMapper(ParamValue paramValue) {
        ParamValueDTO paramValueDTO = modelMapper.map(paramValue, ParamValueDTO.class);
        paramValueDTO.setType(paramValue.getType().toString());
        return paramValueDTO;
    }

    /**
     *
     * @param formTm1
     * @return
     */
    public FormTM1DTO formTM1Mapper(FormTM1 formTm1) {
        FormTM1DTO formTM1DTO = modelMapper.map(formTm1, FormTM1DTO.class);
        List<MeasurementTM1DTO> measurementTM1DTOList =
                formTm1.getMeasurementTM1List().stream()
                        .map(this::measurementTM1Mapper)
                        .toList();
        formTM1DTO.setMeasurementTM1DTOList(measurementTM1DTOList);
        return formTM1DTO;
    }
    public FormTM5DTO formTM5Mapper(FormTM5 formTm5) {
        FormTM5DTO formTM5DTO = modelMapper.map(formTm5, FormTM5DTO.class);
        List<MeasurementTM5DTO> measurementTM5DTOList =
                formTm5.getMeasurementTM5List().stream()
                        .map(this::measurementTM5Mapper)
                        .toList();
        formTM5DTO.setMeasurementTM5List(measurementTM5DTOList);
        return formTM5DTO;
    }

    /**
     *
     * @param formTM2
     * @return
     */
    public FormTM2DTO formTM2Mapper(FormTM2 formTM2) {
        FormTM2DTO formTM2DTO = modelMapper.map(formTM2, FormTM2DTO.class);
        List<MeasurementTM2DTO> measurementTM2DTOList =
                formTM2.getMeasurementTM2List().stream()
                        .map(this::measurementTM2DTO)
                        .toList();
        formTM2DTO.setMeasurementTM2DTOList(measurementTM2DTOList);
        return formTM2DTO;
    }

    /**
     *
     * @param measurementTM2
     * @return
     */
    public MeasurementTM2DTO measurementTM2DTO(MeasurementTM2 measurementTM2) {
        MeasurementTM2DTO measurementTM2DTO = modelMapper.map(measurementTM2, MeasurementTM2DTO.class);
        if (measurementTM2DTO.getFirstTransverseSectionMeasurementDetailTM2() != null) {
            measurementTM2DTO.setFirstTransverseSectionMeasurementDetailTM2(
                    detailMeasurementMapper(measurementTM2.getFirstTransverseSectionMeasurementDetailTM2())
            );
        }
        if (measurementTM2DTO.getSecondTransverseSectionMeasurementDetailTM2() != null) {
            measurementTM2DTO.setSecondTransverseSectionMeasurementDetailTM2(
                    detailMeasurementMapper(measurementTM2.getSecondTransverseSectionMeasurementDetailTM2())
            );
        }
        if (measurementTM2DTO.getThirdTransverseSectionMeasurementDetailTM2() != null) {
            measurementTM2DTO.setThirdTransverseSectionMeasurementDetailTM2(
                    detailMeasurementMapper(measurementTM2.getThirdTransverseSectionMeasurementDetailTM2())
            );
        }
        return measurementTM2DTO;
    }

    /**
     *
     * @param measurementTM1
     * @return
     */
    public MeasurementTM1DTO measurementTM1Mapper(MeasurementTM1 measurementTM1) {
        MeasurementTM1DTO measurementTM1DTO = modelMapper.map(measurementTM1, MeasurementTM1DTO.class);
        if (measurementTM1.getAfterReadingMeasurementDetail() != null) {
            measurementTM1DTO.setAfterReadingMeasurementDetail(
                    detailMeasurementMapper(measurementTM1.getAfterReadingMeasurementDetail())
            );
        }
        if (measurementTM1.getForwardReadingMeasurementDetail() != null) {
            measurementTM1DTO.setForwardReadingMeasurementDetail(
                    detailMeasurementMapper(measurementTM1.getForwardReadingMeasurementDetail())
            );
        }
        return measurementTM1DTO;
    }
    public MeasurementTM5DTO measurementTM5Mapper(MeasurementTM5 measurementTM5) {
        MeasurementTM5DTO measurementTM5DTO = modelMapper.map(measurementTM5, MeasurementTM5DTO.class);
        if (measurementTM5.getMeasurementDetail() != null) {
            measurementTM5DTO.setMeasurementDetail(
                detailMeasurementMapper(measurementTM5.getMeasurementDetail())
        );
        }
        return measurementTM5DTO;
    }


    /**
     *
     * @param formTm3
     * @return
     */
    public FormTM3DTO formTM3Mapper(FormTM3 formTm3) {
        FormTM3DTO formTM3DTO = modelMapper.map(formTm3, FormTM3DTO.class);
        List<MeasurementTM3DTO> measurementTM3DTOList =
                formTm3.getMeasurementTM3List().stream()
                        .map(this::measurementTM3Mapper)
                        .toList();
        formTM3DTO.setMeasurementTM3DTOList(measurementTM3DTOList);
        return formTM3DTO;
    }

    /**
     *
     * @param formTM4
     * @return
     */
    public FormTM4DTO formTM4Mapper(FormTM4 formTM4) {
        FormTM4DTO formTM4DTO = modelMapper.map(formTM4, FormTM4DTO.class);
        List<StructuralMemberTM4DTO> structuralMemberTM4DTOList =
                formTM4.getStructuralMemberTM4List().stream()
                        .map(this::structuralMemberTM4Mapper)
                        .toList();
        structuralMemberTM4DTOList.forEach(structuralMemberTM4DTO -> System.out.println(structuralMemberTM4DTO.toString()));
        formTM4DTO.setStructuralMemberTM4List(structuralMemberTM4DTOList);
        log.info("{}", formTM4DTO.toString());
        return formTM4DTO;
    }

    /**
     *
     * @param measurementTM4
     * @return
     */
    public MeasurementTM4DTO measurementTM4Mapper(MeasurementTM4 measurementTM4) {
        MeasurementTM4DTO measurementTM4DTO = modelMapper.map(measurementTM4, MeasurementTM4DTO.class);
        measurementTM4DTO.setDetailMeasurement(detailMeasurementMapper(measurementTM4.getDetailMeasurement()));
        return measurementTM4DTO;
    }

    /**
     *
     * @param structuralMemberTM4
     * @return
     */
    public StructuralMemberTM4DTO structuralMemberTM4Mapper(StructuralMemberTM4 structuralMemberTM4) {
        StructuralMemberTM4DTO structuralMemberTM4DTO =
                modelMapper.map(structuralMemberTM4, StructuralMemberTM4DTO.class);
        List<MeasurementTM4DTO> measurementTM4DTOList = structuralMemberTM4.getMeasurementTM4List()
                .stream().map(this::measurementTM4Mapper)
                .toList();
        structuralMemberTM4DTO.setMeasurementTM4DTOList(measurementTM4DTOList);
        return structuralMemberTM4DTO;
    }

    /**
     *
     * @param measurementTM7
     * @return
     */
    public MeasurementTM7DTO measurementTM7Mapper(MeasurementTM7 measurementTM7) {
        MeasurementTM7DTO measurementTM7DTO = modelMapper.map(measurementTM7, MeasurementTM7DTO.class);
        measurementTM7DTO.setLowerPart(detailMeasurementMapper(measurementTM7.getLowerPart()));
        measurementTM7DTO.setMidPart(detailMeasurementMapper(measurementTM7.getMidPart()));
        measurementTM7DTO.setUpperPart(detailMeasurementMapper(measurementTM7.getUpperPart()));
        return measurementTM7DTO;
    }

    /**
     *
     * @param frameNumber
     * @return
     */
    public FrameNumberDTO frameNumberMapper(FrameNumber frameNumber) {
        FrameNumberDTO frameNumberDTO =
                modelMapper.map(frameNumber, FrameNumberDTO.class);
        List<MeasurementTM7DTO> measurementTM7DTOList = frameNumber.getMeasurementTM7List()
                .stream().map(this::measurementTM7Mapper)
                .toList();
        frameNumberDTO.setMeasurementTM7DTOList(measurementTM7DTOList);
        return frameNumberDTO;
    }

    /**
     *
     * @param formTM7
     * @return
     */
    public FormTM7DTO formTM7Mapper(FormTM7 formTM7) {
        FormTM7DTO formTM7DTO = modelMapper.map(formTM7, FormTM7DTO.class);
        List<FrameNumberDTO> frameNumberDTOList =
                formTM7.getFrameNumber().stream()
                        .map(this::frameNumberMapper)
                        .toList();
        frameNumberDTOList.forEach(frameNumberDTO -> System.out.println(frameNumberDTO.toString()));
        formTM7DTO.setFrameNumberList(frameNumberDTOList);
        log.info("{}", formTM7DTO.toString());
        return formTM7DTO;
    }

    /**
     *
     * @param formTM6
     * @return
     */
    public FormTM6DTO formTM6Mapper(FormTM6 formTM6) {
        FormTM6DTO formTM6DTO = modelMapper.map(formTM6, FormTM6DTO.class);
        List<StructuralDescriptionTM6DTO> structuralDescriptionTM6DTOList =
                formTM6.getStructuralDescriptionTM6List().stream()
                        .map(this::structuralMemberTM6Mapper)
                        .toList();
        structuralDescriptionTM6DTOList.forEach(structuralDescriptionTM6DTO -> System.out.println(structuralDescriptionTM6DTO.toString()));
        formTM6DTO.setStructuralDescriptionTM6List(structuralDescriptionTM6DTOList);
        log.info("{}", formTM6DTO.toString());
        return formTM6DTO;
    }

    /**
     *
     * @param measurementTM6
     * @return
     */
    public MeasurementTM6DTO measurementTM6Mapper(MeasurementTM6 measurementTM6) {
        MeasurementTM6DTO measurementTM6DTO = modelMapper.map(measurementTM6, MeasurementTM6DTO.class);
        measurementTM6DTO.setDetailMeasurement(detailMeasurementMapper(measurementTM6.getDetailMeasurement()));
        return measurementTM6DTO;
    }

    public StructuralDescriptionTM6DTO structuralMemberTM6Mapper(StructuralDescriptionTM6 structuralDescriptionTM6) {
        StructuralDescriptionTM6DTO structuralDescriptionTM6DTO =
                modelMapper.map(structuralDescriptionTM6, StructuralDescriptionTM6DTO.class);
        List<MeasurementTM6DTO> measurementTM6DTOList = structuralDescriptionTM6.getMeasurementTM6List()
                .stream().map(this::measurementTM6Mapper)
                .toList();
        structuralDescriptionTM6DTO.setMeasurementTM6DTOList(measurementTM6DTOList);
        return structuralDescriptionTM6DTO;
    }

    /**
     *
     * @param measurementTM3
     * @return
     */
    public MeasurementTM3DTO measurementTM3Mapper(MeasurementTM3 measurementTM3) {
        MeasurementTM3DTO measurementTM3DTO = modelMapper.map(measurementTM3, MeasurementTM3DTO.class);
        if (measurementTM3.getFirstTransverseSectionMeasurementDetailTM3() != null) {
            measurementTM3DTO.setFirstTransverseSectionMeasurementDetail(
                    detailMeasurementMapper(measurementTM3.getFirstTransverseSectionMeasurementDetailTM3())
            );
        }
        if (measurementTM3.getSecondTransverseSectionMeasurementDetailTM3() != null) {
            measurementTM3DTO.setSecondTransverseSectionMeasurementDetail(
                    detailMeasurementMapper(measurementTM3.getSecondTransverseSectionMeasurementDetailTM3())
            );
        }
        if (measurementTM3.getThirdTransverseSectionMeasurementDetailTM3() != null) {
            measurementTM3DTO.setThirdTransverseSectionMeasurementDetail(
                    detailMeasurementMapper(measurementTM3.getThirdTransverseSectionMeasurementDetailTM3())
            );
        }
        return measurementTM3DTO;
    }

    /**
     *
     * @param detailMeasurement
     * @return
     */
    public DetailMeasurementDTO detailMeasurementMapper(DetailMeasurement detailMeasurement) {
        DetailMeasurementDTO detailMeasurementDTO =
                modelMapper.map(detailMeasurement, DetailMeasurementDTO.class);

        return detailMeasurementDTO;
    }

    /**
     *
     *
     * @param reportIndex
     * @return
     */
    public ReportIndexDTO reportIndexMapper(ReportIndex reportIndex) {
        ReportIndexDTO reportIndexDTO = modelMapper.map(reportIndex, ReportIndexDTO.class);
        reportIndexDTO.setReportNo(reportIndex.getGeneralParticulars().getReportNo());
        List<FormDTO> formDTOList = new ArrayList<>();
        if (reportIndex.getFormTM1List() != null) {
            formDTOList.addAll(reportIndex.getFormTM1List().stream()
                    .map(this::formTM1Mapper)
                    .toList());
        }
        if (reportIndex.getFormTM2List() != null) {
            formDTOList.addAll(reportIndex.getFormTM2List().stream()
                    .map(this::formTM2Mapper)
                    .toList());
        }
        if (reportIndex.getFormTM3List() != null) {
            formDTOList.addAll(reportIndex.getFormTM3List().stream()
                    .map(this::formTM3Mapper)
                    .toList());
        }
        if (reportIndex.getFormTM4List() != null) {
            formDTOList.addAll(reportIndex.getFormTM4List().stream()
                    .map(this::formTM4Mapper)
                    .toList());
        }
        if (reportIndex.getFormTM5List() != null) {
            formDTOList.addAll(reportIndex.getFormTM5List().stream()
                    .map(this::formTM5Mapper)
                    .toList());
        }
        if (reportIndex.getFormTM6List() != null) {
            formDTOList.addAll(reportIndex.getFormTM6List().stream()
                    .map(this::formTM6Mapper)
                    .toList());
        }
        if (reportIndex.getFormTM7List() != null) {
            formDTOList.addAll(reportIndex.getFormTM7List().stream()
                    .map(this::formTM7Mapper)
                    .toList());
        }
        reportIndexDTO.setFormList(formDTOList);
        return reportIndexDTO;
    }

    /**
     *
     * @param requestBody
     * @return
     */
    public DetailMeasurement mapperToDetailMeasurement(DetailMeasurementRequestBody requestBody) {
        return new DetailMeasurement(requestBody.getOriginalThickness(),
                requestBody.getMaxAlwbDim(), requestBody.getGaugedP(), requestBody.getGaugedS(), requestBody.getPercent());
    }
}