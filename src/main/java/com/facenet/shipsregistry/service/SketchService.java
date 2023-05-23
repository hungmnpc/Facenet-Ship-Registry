package com.facenet.shipsregistry.service;

import com.facenet.shipsregistry.entity.Sketch;
import com.facenet.shipsregistry.modal.SketchDTO;
import com.facenet.shipsregistry.request.SketchedRequest;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * @author: hungdinh
 * Date created: 11/04/2023
 */
public interface SketchService {

    /**
     *
     * @param formId
     * @param formType
     * @param file
     * @return
     */
    public List<SketchDTO> uploadSketchIntoForm(Long formId, String formType, List<MultipartFile> file);

    /**
     *
     * @param formId
     * @param formType
     * @return
     */
    public List<SketchDTO> getSketchInForm(Long formId, String formType);

    /**
     *
     * @param sketchId
     * @return
     */
    public SketchDTO getSketchById(Long sketchId);

    /**
     *
     * @param sketchId
     * @return
     */
    public Boolean deletedSketch(Long sketchId);
}
