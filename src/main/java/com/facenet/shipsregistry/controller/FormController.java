package com.facenet.shipsregistry.controller;

import com.facenet.shipsregistry.modal.FormDTO;
import com.facenet.shipsregistry.modal.FormTM1DTO;
import com.facenet.shipsregistry.modal.ReportMenu;
import com.facenet.shipsregistry.modal.SketchDTO;
import com.facenet.shipsregistry.request.*;
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


    @DeleteMapping("/tm1s/{id}")
    public ResponseEntity<?> deleteFormTM1(@PathVariable Long id) {
        if (!formService.isFormTM1Exist(id)) {
            return ResponseEntity.badRequest().body(String.format("Form TM1 với id = %d không tồn tại", id));
        }
        try {
            Boolean isDeleted = formService.deleteFormTM1(id);
            if (isDeleted) {
                return ResponseEntity.accepted().build();
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
                return ResponseEntity.accepted().build();
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
                return ResponseEntity.accepted().build();
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
                return ResponseEntity.accepted().build();
            } else {
                return ResponseEntity.badRequest().body("Không thể xóa Form này");

            }
        } catch (Exception exception) {
            log.error(exception.getMessage());
            return ResponseEntity.internalServerError().body(exception.getMessage());
        }

    }

    @DeleteMapping("/tm4s/{id}")
    public ResponseEntity<?> deleteFormTM4(@PathVariable Long id) {
        if (!formService.isFormTM4Exist(id)) {
            return ResponseEntity.badRequest().body("Form không tồn tại");
        }
        try {
            Boolean isDeleted = formService.deleteFormTM4(id);
            if (isDeleted) {
                return ResponseEntity.accepted().build();
            } else {
                return ResponseEntity.badRequest().body("Không thể xóa Form này");

            }
        } catch (Exception exception) {
            log.error(exception.getMessage());
            return ResponseEntity.internalServerError().body(exception.getMessage());
        }

    }

    @DeleteMapping("/tm6s/{id}")
    public ResponseEntity<?> deleteFormTM6(@PathVariable Long id) {
        if (!formService.isFormTM6Exist(id)) {
            return ResponseEntity.badRequest().body("Form không tồn tại");
        }
        try {
            Boolean isDeleted = formService.deleteFormTM6(id);
            if (isDeleted) {
                return ResponseEntity.accepted().build();
            } else {
                return ResponseEntity.badRequest().body("Không thể xóa Form này");

            }
        } catch (Exception exception) {
            log.error(exception.getMessage());
            return ResponseEntity.internalServerError().body(exception.getMessage());
        }

    }

    @DeleteMapping("/tm7s/{id}")
    public ResponseEntity<?> deleteFormTM7(@PathVariable Long id) {
        if (!formService.isFormTM7Exist(id)) {
            return ResponseEntity.badRequest().body("Form không tồn tại");
        }
        try {
            Boolean isDeleted = formService.deleteFormTM7(id);
            if (isDeleted) {
                return ResponseEntity.accepted().build();
            } else {
                return ResponseEntity.badRequest().body("Không thể xóa Form này");

            }
        } catch (Exception exception) {
            log.error(exception.getMessage());
            return ResponseEntity.internalServerError().body(exception.getMessage());
        }

    }
    @PutMapping("/tm7s/{id}")
    public ResponseEntity<?> updateFormTM7(@PathVariable Long id,
                                           @RequestBody FormTM7RequestBody requestBody) {
        try {
            FormDTO formTM7DTO = formService.updateFormTM7(id,requestBody);
            if (formTM7DTO != null) {
                return ResponseEntity.ok(formTM7DTO);
            } else {
                return ResponseEntity.badRequest().body("Form không tồn tại");
            }
        } catch (Exception exception) {
            log.error(exception.getMessage());
            exception.printStackTrace();
            return ResponseEntity.internalServerError().body(exception.getMessage());
        }
    }
    @PutMapping("/tm6s/{id}")
    public ResponseEntity<?> updateFormTM6(@PathVariable Long id,
                                           @RequestBody FormTM6RequestBody requestBody) {
        try {
            FormDTO formTM6DTO = formService.updateFormTM6(id, requestBody);
            if (formTM6DTO != null) {
                return ResponseEntity.ok(formTM6DTO);
            } else {
                return ResponseEntity.badRequest().body("Form không tồn tại");
            }
        } catch (Exception exception) {
            log.error(exception.getMessage());
            exception.printStackTrace();
            return ResponseEntity.internalServerError().body(exception.getMessage());
        }
    }
    @PutMapping("/tm4s/{id}")
    public ResponseEntity<?> updateFormTM4(@PathVariable Long id,
                                           @RequestBody FormTM4RequestBody requestBody) {
        try {
            FormDTO formTM4DTO = formService.updateFormTM4(id, requestBody);
            if (formTM4DTO != null) {
                return ResponseEntity.ok(formTM4DTO);
            } else {
                return ResponseEntity.badRequest().body("Form không tồn tại");
            }
        } catch (Exception exception) {
            log.error(exception.getMessage());
            exception.printStackTrace();
            return ResponseEntity.internalServerError().body(exception.getMessage());
        }
    }

    /**
     *
     * @param id
     * @return
     */
    @GetMapping("/tm1s/{id}")
    public ResponseEntity<?> getFormTM1(@PathVariable Long id){
        try{
            FormDTO formTM1DTO = formService.getFormTM1ByID(id);
            if (formTM1DTO != null) {
                return ResponseEntity.ok(formTM1DTO);
            } else {
                return ResponseEntity.badRequest().body("Form không tồn tại");
            }
        }catch (Exception exception) {
            log.error(exception.getMessage());
            exception.printStackTrace();
            return ResponseEntity.internalServerError().body(exception.getMessage());
        }
    }
    @GetMapping("/tm2s/{id}")
    public ResponseEntity<?> getFormTM2(@PathVariable Long id) {
        try {
            FormDTO formTM2DTO = formService.getFormTM2ByID(id);
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

    @GetMapping("/tm3s/{id}")
    public ResponseEntity<?> getFormTM3(@PathVariable Long id) {
        try {
            FormDTO formTM3DTO = formService.getFormTM3ByID(id);
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

    @GetMapping("/tm4s/{id}")
    public ResponseEntity<?> getFormTM4(@PathVariable Long id){
        try{
            FormDTO formTM4DTO = formService.getFormTM4ByID(id);
            if (formTM4DTO != null) {
                return ResponseEntity.ok(formTM4DTO);
            } else {
                return ResponseEntity.badRequest().body("Form không tồn tại");
            }
        } catch (Exception exception) {
            log.error(exception.getMessage());
            exception.printStackTrace();
            return ResponseEntity.internalServerError().body(exception.getMessage());
        }
    }

    @GetMapping("/tm5s/{id}")
    public ResponseEntity<?> getFormTM5(@PathVariable Long id) {
        try {
            FormDTO formTM5DTO = formService.getFormTM5ByID(id);
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

    @GetMapping("/tm6s/{id}")
    public ResponseEntity<?> getFormTM6(@PathVariable Long id){
        try{
            FormDTO formTM6DTO = formService.getFormTM6ByID(id);
            if (formTM6DTO != null) {
                return ResponseEntity.ok(formTM6DTO);
            } else {
                return ResponseEntity.badRequest().body("Form không tồn tại");
            }
        }catch (Exception exception) {
            log.error(exception.getMessage());
            exception.printStackTrace();
            return ResponseEntity.internalServerError().body(exception.getMessage());
        }
    }

    @GetMapping("/tm7s/{id}")
    public ResponseEntity<?> getFormTM7(@PathVariable Long id){
        try{
            FormDTO formTM7DTO = formService.getFormTM7ByID(id);
            if (formTM7DTO != null) {
                return ResponseEntity.ok(formTM7DTO);
            } else {
                return ResponseEntity.badRequest().body("Form không tồn tại");
            }
        }catch (Exception exception) {
            log.error(exception.getMessage());
            exception.printStackTrace();
            return ResponseEntity.internalServerError().body(exception.getMessage());
        }
    }

    @PutMapping("/index/{formType}/{id}")
    public ResponseEntity<?> updateFormIndex(@PathVariable String formType,
                                             @PathVariable Long id,
                                             @RequestBody Integer newIndex) {
        try {
            formService.updateFormIndex(id, formType, newIndex);
            return ResponseEntity.ok().build();
        } catch (Exception exception) {
            log.error(exception.getMessage());
            exception.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }
}