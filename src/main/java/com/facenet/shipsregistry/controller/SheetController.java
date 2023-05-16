package com.facenet.shipsregistry.controller;

import com.facenet.shipsregistry.modal.FormDTO;
import com.facenet.shipsregistry.service.SheetService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author: hungdinh
 * Date created: 11/04/2023
 */

@RestController
@RequestMapping("/sheet")
@Slf4j
public class SheetController {

    @Autowired
    private SheetService sheetService;

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
}