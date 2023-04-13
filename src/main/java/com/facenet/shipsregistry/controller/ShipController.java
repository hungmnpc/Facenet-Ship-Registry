package com.facenet.shipsregistry.controller;

import com.facenet.shipsregistry.modal.ShipDTO;
import com.facenet.shipsregistry.request.ShipInfoRequestBody;
import com.facenet.shipsregistry.service.GeneralParticularsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: hungdinh
 * Date created: 11/04/2023
 */

@RestController
@RequestMapping("/ships")
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
        ShipDTO shipDTO = generalParticularsService.saveNewShip(requestBody);
        return shipDTO != null ? ResponseEntity.ok(shipDTO) : ResponseEntity.badRequest().build();
    }
}