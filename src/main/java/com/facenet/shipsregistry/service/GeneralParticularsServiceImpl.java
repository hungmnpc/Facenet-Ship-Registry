package com.facenet.shipsregistry.service;

import com.facenet.shipsregistry.entity.Certificate;
import com.facenet.shipsregistry.entity.GeneralParticulars;
import com.facenet.shipsregistry.entity.ReportIndex;
import com.facenet.shipsregistry.entity.Ship;
import com.facenet.shipsregistry.modal.*;
import com.facenet.shipsregistry.repository.CertificateRepository;
import com.facenet.shipsregistry.repository.GeneralParticularsRepository;
import com.facenet.shipsregistry.repository.ReportIndexRepository;
import com.facenet.shipsregistry.repository.ShipRepository;
import com.facenet.shipsregistry.request.CertificateRequestBody;
import com.facenet.shipsregistry.request.GeneralParticularRequestBody;
import com.facenet.shipsregistry.request.ShipInfoRequestBody;
import com.facenet.shipsregistry.utils.MapperUtils;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
public class GeneralParticularsServiceImpl implements GeneralParticularsService{

    @Autowired
    private ShipRepository shipRepository;

    @Autowired
    private CertificateRepository certificateRepository;

    @Autowired
    private GeneralParticularsRepository generalParticularsRepository;

    @Autowired
    private ReportIndexRepository reportIndexRepository;

    @Autowired
    private MapperUtils mapperUtils;

    /**
     * @param requestBody
     * @return
     */
    @Override
    public ShipDTO saveNewShip(ShipInfoRequestBody requestBody) {
        Ship ship = new Ship(null, requestBody.getName(), requestBody.getImoNumber(),
                requestBody.getAbsIdentification(), requestBody.getPostOfRegistry(),
                requestBody.getGrossTons(), requestBody.getDeadweight(),
                requestBody.getDateOfBuild(), requestBody.getClassificationSociety(), null);

        Ship shipSave = shipRepository.save(ship);
        if (shipSave.getShip_id() > 0) {
            return  mapperUtils.shipMapper(ship);
        }
        return null;
    }

    /**
     * @param requestBody
     * @return
     */
    @Override
    public GeneralParticularsDTO saveNewGeneralParticulars(GeneralParticularRequestBody requestBody) {
        Optional<Ship> ship = shipRepository.findShipByImoNumber(requestBody.getShip().getImoNumber());
        Certificate certificate =
                certificateRepository.findCertificateByCertificateNo(
                        requestBody.getCertificateNo()).orElse(null);
        GeneralParticulars generalParticulars =
                new GeneralParticulars(null, null, requestBody.getReportNo(),
                        requestBody.getSurveyorInfo(), certificate,
                        requestBody.getPlaceOfMeasurement(),
                        requestBody.getFirstDateOfMeasurement(),
                        requestBody.getLastDateOfMeasurement(),
                        requestBody.getNameOfOperator(), null, false,
                        requestBody.getMeasurementEquipmentInfo(),
                        requestBody.getSurveyType()
                        );
        if (ship.isPresent()) {
            generalParticulars.setShip(ship.get());
        } else {
            ShipDTO newShip = saveNewShip(requestBody.getShip());
            generalParticulars.setShip(
                    shipRepository.findShipByImoNumber(newShip.getImoNumber())
                            .orElse(null));
        }
        GeneralParticulars generalParticularsSaved =
                generalParticularsRepository.save(generalParticulars);

        if (generalParticularsSaved.getId() > 0) {
            GeneralParticularsDTO generalParticularsDTO = mapperUtils.generalParticularsMapper(generalParticulars);
            return generalParticularsDTO;
        }
        return null;
    }

    /**
     * @param requestBody
     * @return
     */
    @Override
    public CertificateDTO saveNewCertificate(CertificateRequestBody requestBody) {
        Certificate certificate = new Certificate(null, requestBody.getCertificateOrganization(),
                requestBody.getCertificateNo(), requestBody.getValidStartDate(),
                requestBody.getValidEndDate(), null);
        Certificate certificateSaved = certificateRepository.save(certificate);
        if (certificateSaved.getId() > 0) {
            return mapperUtils.certificateMapper(certificateSaved);
        }
        return null;
    }

    /**
     * @param id
     */
    @Override
    public void deleteCertificate(Long id) {
        certificateRepository.deleteById(id);
    }

    /**
     * @param id
     * @param requestBody
     * @return
     */
    @Override
    public CertificateDTO updateCertificate(Long id, CertificateRequestBody requestBody) {
        Optional<Certificate> certificateOptional = certificateRepository.findById(id);
        if (certificateOptional.isPresent()) {
            Certificate certificate = certificateOptional.get();
            certificate.setCertificateNo(requestBody.getCertificateNo());
            certificate.setCertificateOrganization(requestBody.getCertificateOrganization());
            certificate.setValidStartDate(requestBody.getValidStartDate());
            certificate.setValidEndDate(requestBody.getValidEndDate());
            return mapperUtils.certificateMapper(certificate);
        }
        return null;
    }

    /**
     *
     * @param imoNumber
     * @param name
     * @param absIdentification
     * @return
     */
    @Override
    public List<ShipDTO> searchShip(String imoNumber, String name, String absIdentification) {
        try {
            List<Ship> shipList = shipRepository.search(imoNumber, name, absIdentification);
            return shipList.stream().map(ship -> mapperUtils.shipMapper(ship)).collect(Collectors.toList());
        } catch (Exception exception) {
            log.debug(exception.getMessage());
            return new ArrayList<ShipDTO>();
        }
    }

    /**
     * @param certificateNo
     * @return
     */
    @Override
    public List<CertificateDTO> searchCertificate(String certificateNo, String certificateOrganization) {
        List<Certificate> certificateList = certificateRepository.search(certificateNo, certificateOrganization);
        try {
            return certificateList.stream()
                    .map(certificate -> mapperUtils.certificateMapper(certificate))
                    .collect(Collectors.toList());
        } catch (Exception exception) {
            log.debug(exception.getMessage());
            return new ArrayList<>();
        }
    }

    /**
     * @return
     */
    @Override
    public List<CertificateDTO> getAllCertificate() {
        List<Certificate> certificateList = certificateRepository.findAll();
        try {
            return certificateList.stream()
                    .map(certificate -> mapperUtils.certificateMapper(certificate))
                    .collect(Collectors.toList());
        } catch (Exception exception) {
            log.debug(exception.getMessage());
            return new ArrayList<>();
        }
    }

    /**
     * @return
     */
    @Override
    public List<GeneralParticularsDTO> getAllGeneralParticulars() {
        List<GeneralParticulars> generalParticularsList =
                generalParticularsRepository.findAll();
        return generalParticularsList.stream()
                .map(generalParticulars -> mapperUtils.generalParticularsMapper(generalParticulars))
                .collect(Collectors.toList());
    }

    /**
     * @param id
     * @return
     */
    @Override
    public ReportMenu getReportMenu(Long id) {
        String reportNo = generalParticularsRepository.getReportNo(id);
        if (reportNo != null) {
            List<ReportIndex> reportIndices = reportIndexRepository.findAllReportIndexInGP(id);
            List<ReportIndexDTO> reportIndexDTOS = reportIndices.stream()
                    .map(reportIndex -> mapperUtils.reportIndexMapper(reportIndex))
                    .toList();
            List<ReportMenu.Part> parts = reportIndexDTOS.stream()
                    .map(reportIndex -> {
                        return new ReportMenu.Part(
                                reportIndex.getPartIndex(), reportIndex.getItem(),
                                reportIndex.getFormList().stream()
                                        .map(formDTO -> new ReportMenu.Form(1L, formDTO.getType()))
                                        .toList());
                    }).toList();
            ReportMenu reportMenu = new ReportMenu(reportNo, parts);
            return reportMenu;
        }
        return null;
    }

    /**
     * @param id
     */
    @Override
    public void deleteGeneralParticulars(Long id) {
        generalParticularsRepository.deleteById(id);
    }
}