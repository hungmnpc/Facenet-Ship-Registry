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
    public FormTM5DTO formTM5Mapper(FormTM5 formTm5) {
        FormTM5DTO formTM5DTO = modelMapper.map(formTm5, FormTM5DTO.class);
        List<MeasurementTM5DTO> measurementTM5DTOList =
                formTm5.getMeasurementTM5List().stream()
                        .map(this::measurementTM5Mapper)
                        .toList();
        formTM5DTO.setMeasurementTM5DTOList(measurementTM5DTOList);
        return formTM5DTO;
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