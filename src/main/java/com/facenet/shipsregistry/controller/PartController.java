package com.facenet.shipsregistry.controller;

import com.facenet.shipsregistry.modal.FormDTO;
import com.facenet.shipsregistry.modal.ReportIndexDTO;
import com.facenet.shipsregistry.modal.ReportMenu;
import com.facenet.shipsregistry.request.*;
import com.facenet.shipsregistry.service.FormService;
import com.facenet.shipsregistry.service.GeneralParticularsService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.URI;

/**
 * @author: hungdinh
 * Date created: 11/04/2023
 */

@RestController
@RequestMapping("/report-indexes")
@Slf4j
public class PartController {

    @Autowired
    FormService formService;

    @Autowired
    GeneralParticularsService generalParticularsService;


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePart(@PathVariable Long id) {
        if (!generalParticularsService.isPartExist(id)) {
            return ResponseEntity.badRequest().body("Không tồn tại part");
        }
        try {
            Boolean isDeleted = generalParticularsService.deletePart(id);
            if (isDeleted) {
                return ResponseEntity.status(HttpStatus.ACCEPTED).build();
            } else {
                return ResponseEntity.badRequest().body("Không thể xóa part này");
            }
        } catch (Exception exception) {
            log.error(exception.getMessage());
            return ResponseEntity.internalServerError().body(exception.getMessage());
        }
    }

    /**
     *
     * @param request
     * @param id
     * @param requestBody
     * @return
     */
    @PostMapping(value = "/{id}/tm1s")
    public ResponseEntity<?> saveNewFormTm1(HttpServletRequest request,
                                            @PathVariable Long id,
                                            @RequestBody FormTM1RequestBody requestBody) {
        try {
            FormDTO formTM1DTOSave = formService.saveNewFormTM1(requestBody, id);
            if (formTM1DTOSave != null) {
                return ResponseEntity.created(URI.create(request.getRequestURI())).body(formTM1DTOSave);
            } else {
                return ResponseEntity.badRequest().build();
            }
        } catch (Exception exception) {
            log.error(exception.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }


    @PostMapping(value = "/{id}/tm1s/sheet", consumes = {
            "multipart/form-data"
    })
    public ResponseEntity<?> saveNewFormTm1BySheet(@PathVariable Long id, @RequestParam MultipartFile excelFile) {
        try {
            FormDTO formDTO = formService.uploadFormTm1FromExcel(id, excelFile);
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
     * @param request
     * @param id
     * @param requestBody
     * @return
     */
    @PostMapping("/{id}/tm5s")
    public ResponseEntity<?> saveNewFormTm5(HttpServletRequest request,
                                            @PathVariable Long id,
                                            @RequestBody FormTM5RequestBody requestBody) {
        try {
            FormDTO formTM5DTOSave = formService.saveNewFormTM5(requestBody, id);
            if (formTM5DTOSave != null) {
                return ResponseEntity.created(URI.create(request.getRequestURI())).body(formTM5DTOSave);
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
     * @param request
     * @param id
     * @param requestBody
     * @return
     */
    @PostMapping("/{id}/tm2s")
    public ResponseEntity<?> saveNewFormTm2(HttpServletRequest request,
                                            @PathVariable Long id,
                                            @RequestBody FormTM2RequestBody requestBody) {
        try {
            FormDTO formTM2Saved = formService.saveNewFormTM2(requestBody, id);
            if (formTM2Saved != null) {
                return ResponseEntity.created(URI.create(request.getRequestURI())).body(formTM2Saved);
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
     * @param request
     * @param id
     * @param requestBody
     * @return
     */
    @PostMapping("/{id}/tm4s")
    public ResponseEntity<?> saveNewFormTm4(HttpServletRequest request,
                                            @PathVariable Long id,
                                            @RequestBody FormTM4RequestBody requestBody) {

        try {
            FormDTO formTM4DTO = formService.saveNewFormTM4(requestBody, id);
            if (formTM4DTO != null) {
                return ResponseEntity.created(URI.create(request.getRequestURI())).body(formTM4DTO);
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
     * @param request
     * @param id
     * @param requestBody
     * @return
     */
    @PostMapping("/{id}/tm6s")
    public ResponseEntity<?> saveNewFormTm6(HttpServletRequest request,
                                            @PathVariable Long id,
                                            @RequestBody FormTM6RequestBody requestBody) {

        try {
            FormDTO formTM6DTO = formService.saveNewFormTM6(requestBody, id);
            if (formTM6DTO != null) {
                return ResponseEntity.created(URI.create(request.getRequestURI())).body(formTM6DTO);
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
     * @param request
     * @param id
     * @param requestBody
     * @return
     */
    @PostMapping("/{id}/tm7s")
    public ResponseEntity<?> saveNewFormTm7(HttpServletRequest request,
                                            @PathVariable Long id,
                                            @RequestBody FormTM7RequestBody requestBody) {

        try {
            FormDTO formTM7DTO = formService.saveNewFormTM7(requestBody, id);
            if (formTM7DTO != null) {
                return ResponseEntity.created(URI.create(request.getRequestURI())).body(formTM7DTO);
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
    @GetMapping("/{id}")
    public ResponseEntity<?> getAllFormFromReportIndex(@PathVariable Long id) {
        try {
            ReportIndexDTO reportIndexDTO = formService.getReportIndexByID(id);
            if (reportIndexDTO != null) {
                return ResponseEntity.ok(reportIndexDTO);
            } else {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }
        } catch (Exception exception) {
            log.error(exception.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }

    /**
     *
     * @param request
     * @param id
     * @param requestBody
     * @return
     */
    @PostMapping("/{id}/tm3s")
    public ResponseEntity<?> saveNewFormTm3(HttpServletRequest request,
                                            @PathVariable Long id,
                                            @RequestBody FormTM3RequestBody requestBody) {
        try {
            FormDTO formTM3DTOSave = formService.saveNewFormTM3(requestBody, id);
            if (formTM3DTOSave != null) {
                return ResponseEntity.created(URI.create(request.getRequestURI())).body(formTM3DTOSave);
            } else {
                return ResponseEntity.badRequest().build();
            }
        } catch (Exception exception) {
            log.debug(exception.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping("/{id}")
    private ResponseEntity<?> updatePart(@PathVariable Long id, @RequestBody PartUpdateRequest request) {
        try {
            ReportMenu.Part part =
                    generalParticularsService.updateReportIndex(id, request.getItem(), request.getPartIndex());
            if (part != null) {
                return ResponseEntity.ok(part);
            } else {
                return ResponseEntity.badRequest().build();
            }
        } catch (Exception exception) {
            log.error(exception.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/{id}/form/{formIndex}")
    public ResponseEntity<?> deleteForm(@PathVariable Long id, @PathVariable Integer formIndex) {
        try {
            Boolean isDeleted = formService.deletedFormUsingPart(id, formIndex);
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
}