package com.facenet.shipsregistry.controller;

import com.facenet.shipsregistry.modal.CertificateDTO;
import com.facenet.shipsregistry.request.CertificateRequestBody;
import com.facenet.shipsregistry.service.GeneralParticularsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author: hungdinh
 * Date created: 11/04/2023
 */

@RestController
@RequestMapping("/certificates")
@Slf4j
public class CertificateController {

    @Autowired
    private GeneralParticularsService generalParticularsService;

    /**
     *
     * @param certificateNo
     * @param certificateOrganization
     * @return
     */
    @GetMapping("")
    public ResponseEntity<?> searchCertificates(
            @RequestParam(name = "certificateNo", defaultValue = "") String certificateNo,
            @RequestParam(name = "certificateOrganization", defaultValue = "") String certificateOrganization
    ) {
        if (certificateNo.equals("") && certificateOrganization.equals("")) {
            return ResponseEntity.ok(generalParticularsService.getAllCertificate());
        } else {
            List<CertificateDTO> certificateDTOList =
                    generalParticularsService.searchCertificate(certificateNo, certificateOrganization);
            return ResponseEntity.ok(certificateDTOList);
        }
    }

    /**
     *
     * @param requestBody
     * @return
     */
    @PostMapping("")
    public ResponseEntity<CertificateDTO> saveNewCertificate(
            @RequestBody CertificateRequestBody requestBody
            ) {
        try {
            CertificateDTO certificateDTO = generalParticularsService.saveNewCertificate(requestBody);
            if (certificateDTO.getId() > 0) {
                return ResponseEntity.ok(certificateDTO);
            } else {
                return ResponseEntity.badRequest().build();
            }
        } catch (Exception exception) {
            log.debug(exception.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }
}