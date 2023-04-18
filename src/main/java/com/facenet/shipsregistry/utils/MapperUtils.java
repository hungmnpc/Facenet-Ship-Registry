package com.facenet.shipsregistry.utils;

import com.facenet.shipsregistry.entity.*;
import com.facenet.shipsregistry.modal.*;
import com.facenet.shipsregistry.request.DetailMeasurementRequestBody;
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


    public DetailMeasurement mapperToDetailMeasurement(DetailMeasurementRequestBody requestBody) {
        return new DetailMeasurement(requestBody.getOriginalThickness(),
                requestBody.getMaxAlwbDim(), requestBody.getGaugedP(), requestBody.getGaugedS());
    }
}