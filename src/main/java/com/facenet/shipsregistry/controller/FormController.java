package com.facenet.shipsregistry.controller;

import com.facenet.shipsregistry.modal.FormDTO;
import com.facenet.shipsregistry.modal.FormTM1DTO;
import com.facenet.shipsregistry.modal.SketchDTO;
import com.facenet.shipsregistry.request.FormTM1RequestBody;
import com.facenet.shipsregistry.request.FormTM2RequestBody;
import com.facenet.shipsregistry.request.FormTM3RequestBody;
import com.facenet.shipsregistry.request.FormTM5RequestBody;
import com.facenet.shipsregistry.request.SketchedRequest;
import com.facenet.shipsregistry.service.FormService;
import com.facenet.shipsregistry.service.SketchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author: hungdinh
 * Date created: 11/04/2023
 */

@RestController
@RequestMapping("/forms")
@Slf4j
public class FormController {

    @Autowired
    private FormService formService;


    @Autowired
    private SketchService sketchService;

    /**
     *
     * @param id
     * @param requestBody
     * @return
     */
    @PutMapping("/tm1s/{id}")
    public ResponseEntity<?> updateFormTM1(@PathVariable Long id,
                                           @RequestBody FormTM1RequestBody requestBody) {
        try {
            FormDTO formTM1DTO = formService.updateFormTM1(id,requestBody);
            return ResponseEntity.ok(formTM1DTO);
        } catch (Exception exception) {
            log.error(exception.getMessage());
            exception.printStackTrace();
            return ResponseEntity.internalServerError().body(exception.getMessage());
        }
    }



    @PostMapping("/tm1s/{id}/sketches")
    public ResponseEntity<?> uploadSketchesToFormTM1(@PathVariable Long id,@RequestBody List<MultipartFile> request) {
        try {
            List<SketchDTO> sketchDTOList = sketchService.uploadSketchesIntoFormTM1(request, id);
            return ResponseEntity.ok(sketchDTOList);
        } catch (Exception exception) {
            log.error(exception.getMessage());
            exception.printStackTrace();
            return ResponseEntity.badRequest().body(exception.getMessage());
        }
    }

    @DeleteMapping("/tm1s/{id}")
    public ResponseEntity<?> deleteFormTM1(@PathVariable Long id) {
        if (!formService.isFormTM1Exist(id)) {
            return ResponseEntity.badRequest().body(String.format("Form TM1 với id = %d không tồn tại", id));
        }
        try {
            Boolean isDeleted = formService.deleteFormTM1(id);
            if (isDeleted) {
                return ResponseEntity.ok("Xóa thành công form TM1");
            } else {
                return ResponseEntity.badRequest().build();
            }
        } catch (Exception exception) {
            log.error(exception.getMessage());
            return ResponseEntity.internalServerError().body(exception.getMessage());
        }
    }


    /**
     *
     * @param id
     * @param requestBody
     * @return
     */
    @PutMapping("/tm2s/{id}")
    public ResponseEntity<?> updateFormTM2(@PathVariable Long id,
                                           @RequestBody FormTM2RequestBody requestBody) {
        try {
            FormDTO formTM2DTO = formService.updateFormTM2(id,requestBody);
            if (formTM2DTO != null) {
                return ResponseEntity.ok(formTM2DTO);
            } else {
                return ResponseEntity.badRequest().body("Form không tồn tại");
            }
        } catch (Exception exception) {
            log.error(exception.getMessage());
            exception.printStackTrace();
            return ResponseEntity.internalServerError().body(exception.getMessage());
        }
    }
    @PutMapping("/tm1s/{id}")
    public ResponseEntity<?> updateFormTM1(@PathVariable Long id,
                                           @RequestBody FormTM1RequestBody requestBody) {
        try {
            FormDTO formTM1DTO = formService.updateFormTM1(id,requestBody);
            return ResponseEntity.ok(formTM1DTO);
        } catch (Exception exception) {
            log.error(exception.getMessage());
            exception.printStackTrace();
            return ResponseEntity.internalServerError().body(exception.getMessage());
        }
    }


    /**\
     *
     * @param id
     * @return
     */
    @DeleteMapping("/tm2s/{id}")
    public ResponseEntity<?> deleteFormTM2(@PathVariable Long id) {
        if (!formService.isFormTM2Exist(id)) {
            return ResponseEntity.badRequest().body("Form không tồn tại");
        }
        try {
            Boolean isDeleted = formService.deletedFormTM2(id);
            if (isDeleted) {
                return ResponseEntity.ok("Xóa thành công.");
            } else {
                return ResponseEntity.badRequest().body("Không thể xóa Form này");

            }
        } catch (Exception exception) {
            log.error(exception.getMessage());
            return ResponseEntity.internalServerError().body(exception.getMessage());
        }

    }
    @PutMapping("/tm3s/{id}")
    public ResponseEntity<?> updateFormTM3(@PathVariable Long id,
                                           @RequestBody FormTM3RequestBody requestBody) {
        try {
            FormDTO formTM3DTO = formService.updateFormTM3(id,requestBody);
            if (formTM3DTO != null) {
                return ResponseEntity.ok(formTM3DTO);
            } else {
                return ResponseEntity.badRequest().body("Form không tồn tại");
            }
        } catch (Exception exception) {
            log.error(exception.getMessage());
            exception.printStackTrace();
            return ResponseEntity.internalServerError().body(exception.getMessage());
        }
    }
    @PutMapping("/tm5s/{id}")
    public ResponseEntity<?> updateFormTM5(@PathVariable Long id,
                                           @RequestBody FormTM5RequestBody requestBody) {
        try {
            FormDTO formTM5DTO = formService.updateFormTM5(id,requestBody);
            if (formTM5DTO != null) {
                return ResponseEntity.ok(formTM5DTO);
            } else {
                return ResponseEntity.badRequest().body("Form không tồn tại");
            }
        } catch (Exception exception) {
            log.error(exception.getMessage());
            exception.printStackTrace();
            return ResponseEntity.internalServerError().body(exception.getMessage());
        }
    }
    @DeleteMapping("/tm3s/{id}")
    public ResponseEntity<?> deleteFormTM3(@PathVariable Long id) {
        if (!formService.isFormTM3Exist(id)) {
            return ResponseEntity.badRequest().body("Form không tồn tại");
        }
        try {
            Boolean isDeleted = formService.deletedFormTM3(id);
            if (isDeleted) {
                return ResponseEntity.ok("Xóa thành công.");
            } else {
                return ResponseEntity.badRequest().body("Không thể xóa Form này");

            }
        } catch (Exception exception) {
            log.error(exception.getMessage());
            return ResponseEntity.internalServerError().body(exception.getMessage());
        }

    }
    @DeleteMapping("/tm5s/{id}")
    public ResponseEntity<?> deleteFormTM5(@PathVariable Long id) {
        if (!formService.isFormTM5Exist(id)) {
            return ResponseEntity.badRequest().body("Form không tồn tại");
        }
        try {
            Boolean isDeleted = formService.deletedFormTM5(id);
            if (isDeleted) {
                return ResponseEntity.ok("Xóa thành công.");
            } else {
                return ResponseEntity.badRequest().body("Không thể xóa Form này");

            }
        } catch (Exception exception) {
            log.error(exception.getMessage());
            return ResponseEntity.internalServerError().body(exception.getMessage());
        }

    }
}