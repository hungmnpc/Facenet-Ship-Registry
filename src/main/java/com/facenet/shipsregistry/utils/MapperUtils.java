package com.facenet.shipsregistry.utils;

import com.facenet.shipsregistry.entity.*;
import com.facenet.shipsregistry.modal.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: hungdinh
 * Date created: 11/04/2023
 */
@Component
public class MapperUtils {

    @Autowired
    private ModelMapper modelMapper;

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

    public FormTM1DTO formTM1Mapper(FormTM1 formTm1) {
        FormTM1DTO formTM1DTO = modelMapper.map(formTm1, FormTM1DTO.class);
        List<MeasurementTM1DTO> measurementTM1DTOList =
                formTm1.getMeasurementTM1List().stream()
                        .map(this::measurementTM1Mapper)
                        .toList();
        formTM1DTO.setMeasurementTM1DTOList(measurementTM1DTOList);
        return formTM1DTO;
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

    public DetailMeasurementDTO detailMeasurementMapper(DetailMeasurement detailMeasurement) {
        DetailMeasurementDTO detailMeasurementDTO =
                modelMapper.map(detailMeasurement, DetailMeasurementDTO.class);

        return detailMeasurementDTO;
    }

    public ReportIndexDTO reportIndexMapper(ReportIndex reportIndex) {
        ReportIndexDTO reportIndexDTO = modelMapper.map(reportIndex, ReportIndexDTO.class);
        reportIndexDTO.setReportNo(reportIndex.getGeneralParticulars().getReportNo());
        List<FormTM1DTO> formTM1DTOList = new ArrayList<>();
        if (reportIndex.getFormTM1List() != null) {
             formTM1DTOList = reportIndex.getFormTM1List().stream()
                    .map(this::formTM1Mapper)
                    .toList();
        }
        List<FormDTO> formDTOList = new ArrayList<>(formTM1DTOList);
        reportIndexDTO.setFormList(formDTOList);
        return reportIndexDTO;
    }
}