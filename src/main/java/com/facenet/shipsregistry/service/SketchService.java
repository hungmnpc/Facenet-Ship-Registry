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

    public SketchDTO uploadSketch(MultipartFile file) throws IOException;

    /**
     *
     * @param request
     * @return
     */
    public List<SketchDTO> uploadSketchesIntoFormTM1(List<MultipartFile> request, Long formId) throws Exception;

    /**
     *
     * @return
     */
    public List<SketchDTO> getAllSketch();
}
