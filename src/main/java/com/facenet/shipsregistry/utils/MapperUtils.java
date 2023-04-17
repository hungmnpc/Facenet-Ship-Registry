package com.facenet.shipsregistry.utils;

import com.facenet.shipsregistry.entity.Certificate;
import com.facenet.shipsregistry.entity.GeneralParticulars;
import com.facenet.shipsregistry.entity.ParamValue;
import com.facenet.shipsregistry.entity.Ship;
import com.facenet.shipsregistry.modal.CertificateDTO;
import com.facenet.shipsregistry.modal.GeneralParticularsDTO;
import com.facenet.shipsregistry.modal.ParamValueDTO;
import com.facenet.shipsregistry.modal.ShipDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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

    public ParamValueDTO paramValueMapper(ParamValue paramValue) {
        ParamValueDTO paramValueDTO = modelMapper.map(paramValue, ParamValueDTO.class);
        paramValueDTO.setType(paramValue.getType().toString());
        return paramValueDTO;
    }
}