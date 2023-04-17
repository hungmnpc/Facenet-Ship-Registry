package com.facenet.shipsregistry.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

/**
 * @author: hungdinh
 * Date created: 11/04/2023
 */

@RestController
@RequestMapping("/forms")
@Slf4j
public class FormController {

    @GetMapping("")
    public ResponseEntity<?> getAllForm() {
        return ResponseEntity.ok().build();
    }

    @PostMapping("/tm1")
    public ResponseEntity<?> saveNewFormTm1(HttpServletRequest request) {
        return ResponseEntity.created(URI.create(request.getRequestURI())).body("TM1");
    }

    @PostMapping("/tm2")
    public ResponseEntity<?> saveNewFormTm2(HttpServletRequest request) {
        return ResponseEntity.created(URI.create(request.getRequestURI())).body("TM2");
    }


}