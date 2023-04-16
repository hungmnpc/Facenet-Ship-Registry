package com.facenet.shipsregistry.controller;

import com.facenet.shipsregistry.entity.GeneralParticulars;
import com.facenet.shipsregistry.modal.GeneralParticularsDTO;
import com.facenet.shipsregistry.request.GeneralParticularRequestBody;
import com.facenet.shipsregistry.service.GeneralParticularsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author: hungdinh
 * Date created: 11/04/2023
 */
@RestController
@RequestMapping("/generals_particulars")
@Slf4j
public class GeneralParticularsController {

    @Autowired
    private GeneralParticularsService generalParticularsService;

    /**
     *
     * @return
     */
    @GetMapping("")
    public ResponseEntity<List<GeneralParticularsDTO>> getAllGeneralParticulars() {
        List<GeneralParticularsDTO> generalParticularsDTOList
                = generalParticularsService.getAllGeneralParticulars();
        return ResponseEntity.ok(generalParticularsDTOList);
    }

    /**
     *
     * @param requestBody
     * @return
     */
    @PostMapping("")
    public ResponseEntity<?> saveNewGeneralParticulars(
            @RequestBody GeneralParticularRequestBody requestBody
            ) {
        try {
            GeneralParticularsDTO generalParticularsDTO =
                    generalParticularsService.saveNewGeneralParticulars(requestBody);
            if (generalParticularsDTO != null) {
                return ResponseEntity.ok(generalParticularsDTO);
            } else {
                return ResponseEntity.badRequest().build();
            }
        } catch (Exception exception) {
            log.debug(exception.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}