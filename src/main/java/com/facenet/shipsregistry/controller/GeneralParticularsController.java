package com.facenet.shipsregistry.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    @GetMapping("/")
    public ResponseEntity<?> getAllGeneralParticulars() {
        return ResponseEntity.ok().build();
    }
}