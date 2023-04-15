package com.facenet.shipsregistry.service;

import com.facenet.shipsregistry.entity.Certificate;
import com.facenet.shipsregistry.entity.GeneralParticulars;
import com.facenet.shipsregistry.entity.Ship;
import com.facenet.shipsregistry.modal.CertificateDTO;
import com.facenet.shipsregistry.modal.GeneralParticularsDTO;
import com.facenet.shipsregistry.modal.ShipDTO;
import com.facenet.shipsregistry.repository.CertificateRepository;
import com.facenet.shipsregistry.repository.GeneralParticularsRepository;
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
                requestBody.getDateOfBuild(), null);

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
                        requestBody.getSurveyorInfo(), certificate, null, false);
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
            return mapperUtils.generalParticularsMapper(generalParticulars);
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
     * @param imoNumber
     * @return
     */
    @Override
    public ShipDTO findShipByImoNumber(String imoNumber) {
        try {
            Optional<Ship> shipOptional = shipRepository.findShipByImoNumber(imoNumber);
            return shipOptional.map(ship -> mapperUtils.shipMapper(ship)).orElse(null);
        } catch (Exception exception) {
            log.debug(exception.getMessage());
            return null;
        }
    }

    /**
     *
     * @return
     */
    @Override
    public List<ShipDTO> findAllShip() {
        try {
            List<Ship> shipList = shipRepository.findAll();
            return shipList.stream()
                    .map(ship -> mapperUtils.shipMapper(ship)).toList();
        } catch (Exception exception) {
            log.debug(exception.getMessage());
            return new ArrayList<ShipDTO>();
        }
    }

    /**
     * \
     *
     * @param imoNumber
     * @param name
     * @return
     */
    @Override
    public List<ShipDTO> search(String imoNumber, String name, String absIdentification) {
        try {
            List<Ship> shipList = shipRepository.search(imoNumber, name, absIdentification);
            return shipList.stream().map(ship -> mapperUtils.shipMapper(ship)).collect(Collectors.toList());
        } catch (Exception exception) {
            log.debug(exception.getMessage());
            return new ArrayList<ShipDTO>();
        }
    }
}