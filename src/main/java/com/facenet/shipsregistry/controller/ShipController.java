package com.facenet.shipsregistry.controller;

import com.facenet.shipsregistry.modal.ShipDTO;
import com.facenet.shipsregistry.request.ShipInfoRequestBody;
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
@RequestMapping("/ships")
@Slf4j
public class ShipController {

    @Autowired
    private GeneralParticularsService generalParticularsService;

    /**
     *
     * @param requestBody
     * @return
     */
    @PostMapping("")
    public ResponseEntity<ShipDTO> createNewShip(@RequestBody ShipInfoRequestBody requestBody) {
        try {
            ShipDTO shipDTO = generalParticularsService.saveNewShip(requestBody);
            return shipDTO != null ? ResponseEntity.ok(shipDTO) : ResponseEntity.badRequest().build();
        } catch (Exception exception) {
            log.debug(exception.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("")
    public ResponseEntity<?> getAllShips(
            @RequestParam(name = "imo-number", defaultValue = "") String imoNumber,
            @RequestParam(name = "name", defaultValue = "") String name,
            @RequestParam(name = "abs_identification", defaultValue = "") String absIdentification
    ) {
        try {
            List<ShipDTO> shipDTOList = generalParticularsService.searchShip(imoNumber, name, absIdentification);
            return ResponseEntity.ok(shipDTOList);
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
        }

    }
}