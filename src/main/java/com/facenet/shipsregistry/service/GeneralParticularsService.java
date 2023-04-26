package com.facenet.shipsregistry.service;

import com.facenet.shipsregistry.modal.CertificateDTO;
import com.facenet.shipsregistry.modal.GeneralParticularsDTO;
import com.facenet.shipsregistry.modal.ReportMenu;
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
     * @param id
     */
    public void deleteCertificate(Long id);

    /**
     *
     * @param id
     * @param requestBody
     * @return
     */
    public CertificateDTO updateCertificate(Long id, CertificateRequestBody requestBody);

    /**
     *
     * @param imoNumber
     * @param name
     * @param absIdentification
     * @return
     */
    public List<ShipDTO> searchShip(String imoNumber, String name, String absIdentification);

    /**
     *
     * @param certificateNo
     * @return
     */
    public List<CertificateDTO> searchCertificate(String certificateNo, String certificateOrganization);

    /**
     *
     * @return
     */
    public List<CertificateDTO> getAllCertificate();

    /**
     *
     * @return
     */
    public List<GeneralParticularsDTO> getAllGeneralParticulars();

    /**
     *
     * @param id
     * @return
     */
    public ReportMenu getReportMenu(Long id);
}
