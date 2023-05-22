package com.facenet.shipsregistry.controller;

import com.facenet.shipsregistry.modal.DetailMeasurementDTO;
import com.facenet.shipsregistry.request.DetailMeasurementRequestBody;
import com.facenet.shipsregistry.service.FormService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author: hungdinh
 * Date created: 11/04/2023
 */

@RestController
@RequestMapping("/detail_measurements")
@Slf4j
@SecurityRequirement(name = "bearerAuth")
public class DetailMeasurementController {

    @Autowired
    private FormService formService;

    @PutMapping("/{id}")
    public ResponseEntity<?> updateDetailMeasurement(@PathVariable Long id,
                                                     @RequestBody DetailMeasurementRequestBody requestBody) {
        try {
            DetailMeasurementDTO detailMeasurementDTO = formService.updateDetailMeasurement(id, requestBody);
            if (detailMeasurementDTO != null) {
                return ResponseEntity.ok(detailMeasurementDTO);
            } else {
                return ResponseEntity.badRequest().build();
            }
        } catch (Exception exception) {
            log.error(exception.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }
}