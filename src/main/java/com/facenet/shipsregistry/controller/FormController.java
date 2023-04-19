package com.facenet.shipsregistry.controller;

import com.facenet.shipsregistry.modal.FormDTO;
import com.facenet.shipsregistry.modal.ReportIndexDTO;
import com.facenet.shipsregistry.request.FormTM1RequestBody;
import com.facenet.shipsregistry.request.FormTM3RequestBody;
import com.facenet.shipsregistry.request.FormTM2RequestBody;
import com.facenet.shipsregistry.request.FormTM4RequestBody;
import com.facenet.shipsregistry.service.FormService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;

/**
 * @author: hungdinh
 * Date created: 11/04/2023
 */

@RestController
@RequestMapping("/report-indexes")
@Slf4j
public class FormController {

    @Autowired
    FormService formService;

    @GetMapping("")
    public ResponseEntity<?> getAllForm() {
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/tm1s")
    public ResponseEntity<?> saveNewFormTm1(HttpServletRequest request,
                                            @PathVariable Long id,
                                            @RequestBody FormTM1RequestBody requestBody) {
        try {
            FormDTO formTM1DTOSave = formService.saveNewFormTM1(requestBody, id);
            if (formTM1DTOSave != null) {
                return ResponseEntity.created(URI.create(request.getRequestURI())).body(formTM1DTOSave);
            } else {
                return ResponseEntity.badRequest().build();
            }
        } catch (Exception exception) {
            log.debug(exception.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping("/{id}/tm2s")
    public ResponseEntity<?> saveNewFormTm2(HttpServletRequest request,
                                            @PathVariable Long id,
                                            @RequestBody FormTM2RequestBody requestBody) {
        try {
            FormDTO formTM2Saved = formService.saveNewFormTM2(requestBody, id);
            if (formTM2Saved != null) {
                return ResponseEntity.created(URI.create(request.getRequestURI())).body(formTM2Saved);
            } else {
                return ResponseEntity.badRequest().build();
            }
        } catch (Exception exception) {
            log.debug(exception.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping("/{id}/tm4s")
    public ResponseEntity<?> saveNewFormTm2(HttpServletRequest request,
                                            @PathVariable Long id,
                                            @RequestBody FormTM4RequestBody requestBody) {
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAllFormFromReportIndex(@PathVariable Long id) {
        try {
            ReportIndexDTO reportIndexDTO = formService.getReportIndexByID(id);
            if (reportIndexDTO != null) {
                return ResponseEntity.ok(reportIndexDTO);
            } else {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }
        } catch (Exception exception) {
            log.debug(exception.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }


    @PostMapping("/tm2")
    public ResponseEntity<?> saveNewFormTm2(HttpServletRequest request) {
        return ResponseEntity.created(URI.create(request.getRequestURI())).body("TM2");
    }

    @PostMapping("/{id}/tm3s")
    public ResponseEntity<?> saveNewFormTm3(HttpServletRequest request,
                                            @PathVariable Long id,
                                            @RequestBody FormTM3RequestBody requestBody) {
        try {
            FormDTO formTM3DTOSave = formService.saveNewFormTM3(requestBody, id);
            if (formTM3DTOSave != null) {
                return ResponseEntity.created(URI.create(request.getRequestURI())).body(formTM3DTOSave);
            } else {
                return ResponseEntity.badRequest().build();
            }
        } catch (Exception exception) {
            log.debug(exception.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }
}