package com.facenet.shipsregistry.controller;

import com.facenet.shipsregistry.entity.ParamValue;
import com.facenet.shipsregistry.modal.ParamValueDTO;
import com.facenet.shipsregistry.request.ParamValueRequestBody;
import com.facenet.shipsregistry.service.ParamValueService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

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

    /**
     *
     * @param type
     * @return
     */
    @GetMapping("")
    public ResponseEntity<?> getParamValue(
            @RequestParam(name = "type", defaultValue = "0") Integer type
    ) {
        try {
            List<ParamValueDTO> paramValueDTOList = new ArrayList<>();
            if (type == 0) {
                paramValueDTOList = paramValueService.getAllParamValue();
            } else {
                paramValueDTOList = paramValueService.getParamValueByType(type);
            }
            return ResponseEntity.ok(paramValueDTOList);
        } catch (Exception exception) {
            log.debug(exception.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     *
     * @param requestBody
     * @param httpServletRequest
     * @return
     */
    @PostMapping("")
    public ResponseEntity<?> saveParamValue(
            @RequestBody ParamValueRequestBody requestBody,
            HttpServletRequest httpServletRequest
            ) {
        try {
            ParamValueDTO paramValueDTO = paramValueService.saveNewParamValue(requestBody);
            if (paramValueDTO != null) {
                return ResponseEntity.created(URI.create(httpServletRequest.getRequestURI())).body(paramValueDTO);
            } else {
                return ResponseEntity.badRequest().build();
            }
        } catch (Exception exception) {
            log.error(exception.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }

    /**
     *
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteParamValue(@PathVariable Long id) {
        try {
            paramValueService.deleteParamValue(id);
            return ResponseEntity.accepted().build();
        } catch (Exception exception) {
            log.error(exception.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateParamValue(@PathVariable Long id, @RequestBody ParamValueRequestBody requestBody) {
        try {
            ParamValueDTO paramValueDTO = paramValueService.updateParamValue(id, requestBody);
            if (paramValueDTO != null) {
                return ResponseEntity.ok(paramValueDTO);
            } else {
                return ResponseEntity.badRequest().build();
            }
        } catch (Exception exception) {
            log.error(exception.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }
}