package com.facenet.shipsregistry.controller;

import com.facenet.shipsregistry.entity.Sketch;
import com.facenet.shipsregistry.modal.SketchDTO;
import com.facenet.shipsregistry.service.SketchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
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
     * @param file
     * @return
     */
    @PostMapping(value = "/upload", consumes = {
            "multipart/form-data"
    })
    public ResponseEntity<?> uploadSketch(@RequestParam MultipartFile file) {
        try {
            SketchDTO sketchDTO = sketchService.uploadSketch(file);
            return ResponseEntity.ok(sketchDTO);
        } catch (Exception exception) {
            log.error(exception.getMessage());
            exception.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }

    }

    @GetMapping("")
    public ResponseEntity<?> getAllSketch() {
        try {
            MediaType mediaType = MediaType.APPLICATION_PDF;
            List<SketchDTO> sketchDTOList = sketchService.getAllSketch();
            log.info("{}", sketchDTOList);
            File file = new File(sketchDTOList.get(7).getPath());
            UrlResource resource = new UrlResource(file.toURI());
            String headerValue = "attachment; filename=\"" + resource.getFilename() + "\"";
            return ResponseEntity.ok()
                    .contentType(mediaType)
                    .header(HttpHeaders.CONTENT_DISPOSITION, headerValue)
                    .body(resource);
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }
}