package com.facenet.shipsregistry.controller;

import com.facenet.shipsregistry.service.ParamValueService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: hungdinh
 * Date created: 11/04/2023
 */
@RestController
@RequestMapping("/param_value")
@Slf4j
public class ParamValueController {

    @Autowired
    ParamValueService paramValueService;

    @GetMapping("")
    public ResponseEntity<?> getParamValue(
            @RequestParam(name = "type", defaultValue = "0") Integer type
    ) {
        return ResponseEntity.ok(type);
    }
}