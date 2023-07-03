package com.facenet.shipsregistry.controller;

import com.facenet.shipsregistry.entity.Sketch;
import com.facenet.shipsregistry.modal.FormDTO;
import com.facenet.shipsregistry.repository.SketchRepository;
import com.facenet.shipsregistry.service.SheetService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author: hungdinh
 * Date created: 11/04/2023
 */

@RestController
@RequestMapping("/sheet")
@Slf4j
@SecurityRequirement(name = "bearerAuth")
public class SheetController {

    @Autowired
    private SheetService sheetService;

    @Autowired
    private SketchRepository sketchRepository;

    /**
     *
     * @param excelFile
     * @return
     */
    @PostMapping(value = "/tm1s", consumes = {
            "multipart/form-data"
    })
    public ResponseEntity<?> uploadSheetTM1(@RequestParam MultipartFile excelFile) {
        try {
            FormDTO formDTO = sheetService.uploadFormTm1FromExcel(excelFile);
            if (formDTO != null) {
                return ResponseEntity.ok(formDTO);
            } else {
                return ResponseEntity.badRequest().build();
            }
        } catch (Exception exception) {
            exception.printStackTrace();
            log.error(exception.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }

    /**
     *
     * @param excelFile
     * @return
     */
    @PostMapping(value = "/tm2s", consumes = {
            "multipart/form-data"
    })
    public ResponseEntity<?> uploadSheetTM2(@RequestParam MultipartFile excelFile) {
        try {
            FormDTO formDTO = sheetService.uploadFormTm2FromExcel(excelFile);
            if (formDTO != null) {
                return ResponseEntity.ok(formDTO);
            } else {
                return ResponseEntity.badRequest().build();
            }
        } catch (Exception exception) {
            exception.printStackTrace();
            log.error(exception.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }

    /**
     *
     * @param excelFile
     * @return
     */
    @PostMapping(value = "/tm3s", consumes = {
            "multipart/form-data"
    })
    public ResponseEntity<?> uploadSheetTM3(@RequestParam MultipartFile excelFile) {
        try {
            FormDTO formDTO = sheetService.uploadFormTm3FromExcel(excelFile);
            if (formDTO != null) {
                return ResponseEntity.ok(formDTO);
            } else {
                return ResponseEntity.badRequest().build();
            }
        } catch (Exception exception) {
            exception.printStackTrace();
            log.error(exception.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }

    /**
     *
     * @param excelFile
     * @return
     */
    @PostMapping(value = "/tm5s", consumes = {
            "multipart/form-data"
    })
    public ResponseEntity<?> uploadSheetTM5(@RequestParam MultipartFile excelFile) {
        try {
            FormDTO formDTO = sheetService.uploadFormTm5FromExcel(excelFile);
            if (formDTO != null) {
                return ResponseEntity.ok(formDTO);
            } else {
                return ResponseEntity.badRequest().build();
            }
        } catch (Exception exception) {
            exception.printStackTrace();
            log.error(exception.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }

    /**
     *
     * @param excelFile
     * @return
     */
    @PostMapping(value = "/tm4s", consumes = {
            "multipart/form-data"
    })
    public ResponseEntity<?> uploadSheetTM4(@RequestParam MultipartFile excelFile) {
        try {
            FormDTO formDTO = sheetService.uploadFormTm4FromExcel(excelFile);
            if (formDTO != null) {
                return ResponseEntity.ok(formDTO);
            } else {
                return ResponseEntity.badRequest().build();
            }
        } catch (Exception exception) {
            exception.printStackTrace();
            log.error(exception.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }

    /**
     *
     * @param excelFile
     * @return
     */
    @PostMapping(value = "/tm6s", consumes = {
            "multipart/form-data"
    })
    public ResponseEntity<?> uploadSheetTM6(@RequestParam MultipartFile excelFile) {
        try {
            FormDTO formDTO = sheetService.uploadFormTm6FromExcel(excelFile);
            if (formDTO != null) {
                return ResponseEntity.ok(formDTO);
            } else {
                return ResponseEntity.badRequest().build();
            }
        } catch (Exception exception) {
            exception.printStackTrace();
            log.error(exception.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping(value = "/tm7s", consumes = {
            "multipart/form-data"
    })
    public ResponseEntity<?> uploadSheetTM7(@RequestParam MultipartFile excelFile) {
        try {
            FormDTO formDTO = sheetService.uploadFormTm7FromExcel(excelFile);
            if (formDTO != null) {
                return ResponseEntity.ok(formDTO);
            } else {
                return ResponseEntity.badRequest().build();
            }
        } catch (Exception exception) {
            exception.printStackTrace();
            log.error(exception.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }

    /**
     *
     * @param form
     * @return
     */
    @GetMapping("/{form}")
    public ResponseEntity<?> downloadTemplate(@PathVariable String form) {
        HttpHeaders header = new HttpHeaders();
        File file = null;
        try {
            file = ResourceUtils.getFile("classpath:template-form/" + form + ".xls");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        Path path = Paths.get(file.getAbsolutePath());
        header.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getName() + "\"");
        header.add("Cache-Control", "no-cache, no-store, must-revalidate");
        header.add("Pragma", "no-cache");
        header.add("Expires", "0");
        ByteArrayResource resource = null;
        try {
            resource = new ByteArrayResource(Files.readAllBytes(path));
            return ResponseEntity.ok()
                    .headers(header)
                    .contentLength(file.length())
                    .contentType(MediaType.parseMediaType("application/octet-stream"))
                    .body(resource);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}