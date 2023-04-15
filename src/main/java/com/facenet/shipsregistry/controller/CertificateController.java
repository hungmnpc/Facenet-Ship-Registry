package com.facenet.shipsregistry.controller;

import com.facenet.shipsregistry.modal.CertificateDTO;
import com.facenet.shipsregistry.service.GeneralParticularsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author: hungdinh
 * Date created: 11/04/2023
 */

@RestController
@RequestMapping("/certificates")
public class CertificateController {

    @Autowired
    private GeneralParticularsService generalParticularsService;

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
}