package com.facenet.shipsregistry.controller;

import com.facenet.shipsregistry.entity.Sketch;
import com.facenet.shipsregistry.modal.SketchDTO;
import com.facenet.shipsregistry.service.SketchService;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

/**
 * @author: hungdinh
 * Date created: 11/04/2023
 */

@RestController
@RequestMapping("/sketches")
@Slf4j
public class SketchController {

    @Autowired
    private SketchService sketchService;

    /**
     *
     * @param formType
     * @param formId
     * @param multipartFiles
     * @return
     */
    @PostMapping(value = "/{formType}/{formId}", consumes = {
            "multipart/form-data"
    })
    public ResponseEntity<?> uploadSketch(@PathVariable String formType,
                                          @PathVariable Long formId,
                                          @RequestBody List<MultipartFile> multipartFiles) {
        try {
            List<SketchDTO> sketchDTOList = sketchService.uploadSketchIntoForm(formId, formType, multipartFiles);
            return ResponseEntity.ok(sketchDTOList);
        } catch (Exception exception) {
            log.error(exception.getMessage());
            exception.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    /**
     *
     * @param formType
     * @param formId
     * @return
     */
    @GetMapping("/{formType}/{formId}")
    public ResponseEntity<?> getAllSketchInForm(@PathVariable String formType, @PathVariable Long formId) {
        try {
            List<SketchDTO> sketchDTOList = sketchService.getSketchInForm(formId, formType);
            return ResponseEntity.ok(sketchDTOList);
        } catch (Exception exception) {
            log.error(exception.getMessage());
            exception.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<?> downloadSketch(@PathVariable Long id) {
        try {
            SketchDTO sketchDTO = sketchService.getSketchById(id);
            Resource resource = new UrlResource("file://" + sketchDTO.getPath());
            if (resource.exists()) {
                String contentType = sketchDTO.getType();
                return ResponseEntity.ok()
                        .contentType(MediaType.parseMediaType(contentType))
                        .header(HttpHeaders.CONTENT_DISPOSITION,
                                "attachment; filename=\"" + resource.getFilename() +"\"")
                        .body(resource);
            } else {
                return ResponseEntity.badRequest().build();
            }
        } catch (Exception exception) {
            log.error(exception.getMessage());
            exception.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }
}