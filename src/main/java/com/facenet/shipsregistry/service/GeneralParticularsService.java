package com.facenet.shipsregistry.service;

import com.facenet.shipsregistry.modal.CertificateDTO;
import com.facenet.shipsregistry.modal.GeneralParticularsDTO;
import com.facenet.shipsregistry.modal.ShipDTO;
import com.facenet.shipsregistry.request.CertificateRequestBody;
import com.facenet.shipsregistry.request.GeneralParticularRequestBody;
import com.facenet.shipsregistry.request.ShipInfoRequestBody;

import java.util.List;

/**
 * @author: hungdinh
 * Date created: 11/04/2023
 */
public interface GeneralParticularsService {

    /**
     *
     * @param requestBody
     * @return
     */
    public ShipDTO saveNewShip(ShipInfoRequestBody requestBody);

    /**
     *
     * @param requestBody
     * @return
     */
    public GeneralParticularsDTO saveNewGeneralParticulars(
            GeneralParticularRequestBody requestBody
    );

    /**
     *
     * @param requestBody
     * @return
     */
    public CertificateDTO saveNewCertificate(CertificateRequestBody requestBody);

    /**
     *
     * @param imoNumber
     * @return
     */
    public ShipDTO findShipByImoNumber(String imoNumber);

    /**
     *
     * @return
     */
    public List<ShipDTO> findAllShip();

    /**\
     *
     * @return
     */
    public List<ShipDTO> search(String imoNumber, String name);
}
