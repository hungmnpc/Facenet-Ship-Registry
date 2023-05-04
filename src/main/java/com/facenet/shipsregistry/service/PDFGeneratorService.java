package com.facenet.shipsregistry.service;

import com.lowagie.text.Document;
import jakarta.servlet.http.HttpServletResponse;

import javax.print.Doc;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @author: hungdinh
 * Date created: 11/04/2023
 */
public interface PDFGeneratorService {

    /**
     *
     * @param response
     */
    public void export(HttpServletResponse response, String reportNo) throws IOException;

    /**
     *
     * @param reportNo
     */
    public void createMenu(String reportNo) throws IOException;

}
