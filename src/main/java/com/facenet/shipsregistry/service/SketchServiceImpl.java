package com.facenet.shipsregistry.service;

import com.facenet.shipsregistry.entity.FormTM1;
import com.facenet.shipsregistry.entity.Sketch;
import com.facenet.shipsregistry.modal.FormTM1DTO;
import com.facenet.shipsregistry.modal.ReportNameAndPartName;
import com.facenet.shipsregistry.modal.SketchDTO;
import com.facenet.shipsregistry.repository.FormTM1Repository;
import com.facenet.shipsregistry.repository.GeneralParticularsRepository;
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
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

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

    @Autowired
    private FormTM1Repository formTM1Repository;

    @Autowired
    private GeneralParticularsRepository generalParticularsRepository;

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
//            Files.createDirectories(Paths.get("sketches"));
            String localDir = System.getProperty("user.dir").concat("/sketches");
            log.info(localDir + "/" + path);
            String finalDir = localDir + "/" + path;
            Files.createDirectories(Paths.get(finalDir));
            File newFile = new File(finalDir);
            file.transferTo(newFile);
            Sketch sketch = new Sketch(null, finalDir, file.getContentType(), formId, formType);
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
     * @param formId
     * @param formType
     * @param files
     * @return
     */
    @Override
    public List<SketchDTO> uploadSketchIntoForm(Long formId, String formType, List<MultipartFile> files) {
        String reportNo = null;
        ReportNameAndPartName reportNameAndPartName = new ReportNameAndPartName();
        Object[][] rpAndPn = generalParticularsRepository.getReportAndPartNameByForm(formType, formId);
        if (rpAndPn.length > 0) {
            reportNameAndPartName
                    .init( generalParticularsRepository.getReportAndPartNameByForm(formType, formId)[0]);
            log.info("{}", reportNameAndPartName.toString());
            String finalPrePath = reportNameAndPartName.createDir();
            List<Sketch> sketchList =  files.stream()
                    .map(multipartFile ->
                            upload(multipartFile,
                                    finalPrePath + multipartFile.getOriginalFilename(),
                                    formId, formType))
                    .toList();
            List<SketchDTO> sketchDTOList = sketchList.stream()
                    .map(sketch -> mapperUtils.mapperSketch(sketch))
                    .toList();
            return sketchDTOList;
        }

        return null;
    }

    /**
     * @param formId
     * @param formType
     * @return
     */
    @Override
    public List<SketchDTO> getSketchInForm(Long formId, String formType) {
        List<Sketch> sketchList = sketchRepository.getAllSketchInForm(formId, formType);
        List<SketchDTO> sketchDTOList = sketchList.stream()
                .map(sketch -> mapperUtils.mapperSketch(sketch))
                .toList();
        return sketchDTOList;
    }

    /**
     * @param sketchId
     * @return
     */
    @Override
    public SketchDTO getSketchById(Long sketchId) {
        Sketch sketch = sketchRepository.findById(sketchId).orElse(null);
        if (sketch != null) {
            return mapperUtils.mapperSketch(sketch);
        }
        return null;
    }


}