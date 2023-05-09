package com.facenet.shipsregistry.controller;

import com.facenet.shipsregistry.modal.FormDTO;
import com.facenet.shipsregistry.modal.FormTM1DTO;
import com.facenet.shipsregistry.request.FormTM1RequestBody;
import com.facenet.shipsregistry.service.FormService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author: hungdinh
 * Date created: 11/04/2023
 */

@RestController
@RequestMapping("/forms")
@Slf4j
public class FormController {

    @Autowired
    private FormService formService;

    /**
     *
     * @param id
     * @param requestBody
     * @return
     */
    @PutMapping("/tm1s/{id}")
    public ResponseEntity<?> updateFormTM1(@PathVariable Long id,
                                           @RequestBody FormTM1RequestBody requestBody) {
        try {
            FormDTO formTM1DTO = formService.updateFormTM1(id,requestBody);
            return ResponseEntity.ok(formTM1DTO);
        } catch (Exception exception) {
            log.error(exception.getMessage());
            exception.printStackTrace();
            return ResponseEntity.internalServerError().body(exception.getMessage());
        }
    }

    @DeleteMapping("/tm1s/{id}")
    public ResponseEntity<?> deleteFormTM1(@PathVariable Long id) {
        try {
            formService.deleteFormTM1(id);
            return ResponseEntity.ok("Xóa thành công form TM1");
        } catch (Exception exception) {
            log.error(exception.getMessage());
            return ResponseEntity.internalServerError().body(exception.getMessage());
        }
    }
}