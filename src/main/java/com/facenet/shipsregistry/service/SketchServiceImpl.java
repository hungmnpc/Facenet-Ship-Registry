package com.facenet.shipsregistry.service;

import com.facenet.shipsregistry.entity.FormTM1;
import com.facenet.shipsregistry.entity.Sketch;
import com.facenet.shipsregistry.modal.SketchDTO;
import com.facenet.shipsregistry.repository.SketchRepository;
import com.facenet.shipsregistry.request.SketchedRequest;
import com.facenet.shipsregistry.utils.MapperUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * @author: hungdinh
 * Date created: 11/04/2023
 */

@Service
@Transactional
@Slf4j
public class SketchServiceImpl implements SketchService{

    @Autowired
    private SketchRepository sketchRepository;

    @Autowired
    MapperUtils mapperUtils;

    @Autowired
    private FormService formService;

    private Sketch upload(MultipartFile file, String path) {
        try {
            file.transferTo(new File(path));
            Sketch sketch = new Sketch(null, path, file.getContentType(), null, null);
            Sketch sketchSaved = sketchRepository.save(sketch);
            if (sketchSaved.getId() > 0) {
                return sketchSaved;
            } else {
                return null;
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

    private Sketch upload(MultipartFile file, String path, Long formId, String formType) {
        try {
            file.transferTo(new File(path));
            Sketch sketch = new Sketch(null, path, file.getContentType(), formId, formType);
            Sketch sketchSaved = sketchRepository.save(sketch);
            if (sketchSaved.getId() > 0) {
                return sketchSaved;
            } else {
                return null;
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

    @Override
    public SketchDTO uploadSketch(MultipartFile file) {
        String fileName = file.getOriginalFilename();
        String path = "/home/hungdinh/Desktop/sketches/" + fileName;
        Sketch sketch = upload(file, path);
        if (sketch != null) {
            return mapperUtils.mapperSketch(sketch);
        } else {
            return null;
        }

    }

    /**
     * @param request
     * @return
     */
    @Override
    public List<SketchDTO> uploadSketchesIntoFormTM1(List<MultipartFile> request, Long formId) throws Exception {
        if (formService.isFormTM1Exist(formId)) {
            List<SketchDTO> sketchDTOList = request.stream()
                    .map(file -> {
                        String fileName = file.getOriginalFilename();
                        String path = "/home/hungdinh/Desktop/sketches/" + fileName;
                        Sketch sketch = upload(file, path, formId, "FormTM1");
                        return mapperUtils.mapperSketch(sketch);
                    }).toList();

            return sketchDTOList;
        } else {
            throw new Exception("Không tồn tại Form TM1 với id = " + formId);
        }
    }

    /**
     * @return
     */
    @Override
    public List<SketchDTO> getAllSketch() {
        List<Sketch> sketchList = sketchRepository.findAll();
        List<SketchDTO> sketchDTOList = sketchList.stream()
                .map(sketch -> mapperUtils.mapperSketch(sketch))
                .toList();
        return sketchDTOList;
    }
}