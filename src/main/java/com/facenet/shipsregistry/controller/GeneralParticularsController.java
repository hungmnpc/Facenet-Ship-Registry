package com.facenet.shipsregistry.controller;

import com.facenet.shipsregistry.request.GeneralParticularRequestBody;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author: hungdinh
 * Date created: 11/04/2023
 */
@RestController
@RequestMapping("/generals_particulars")
public class GeneralParticularsController {

    /**
     *
     * @return
     */
    @GetMapping("")
    public ResponseEntity<?> getAllGeneralParticulars() {
        return ResponseEntity.ok("Hello");
    }

    /**
     *
     * @param requestBody
     * @return
     */
    @PostMapping("")
    public ResponseEntity<?> createNewGeneralParticulars(
            @RequestBody GeneralParticularRequestBody requestBody
            ) {


        return ResponseEntity.ok().build();
    }
}