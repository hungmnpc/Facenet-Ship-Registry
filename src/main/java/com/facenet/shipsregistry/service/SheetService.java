package com.facenet.shipsregistry.service;

import com.facenet.shipsregistry.modal.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author: hungdinh
 * Date created: 11/04/2023
 */
public interface SheetService {

    /**
     *
     * @param excelFile
     * @return
     * @throws Exception
     */
    public FormTM1DTO uploadFormTm1FromExcel(MultipartFile excelFile) throws Exception;

    /**
     *
     * @param excelFile
     * @return
     * @throws Exception
     */
    public FormTM2DTO uploadFormTm2FromExcel(MultipartFile excelFile) throws Exception;

    /**
     *
     * @param excelFile
     * @return
     * @throws Exception
     */
    public FormTM3DTO uploadFormTm3FromExcel(MultipartFile excelFile) throws Exception;

    /**
     *
     * @param excelFile
     * @return
     * @throws Exception
     */
    public FormTM5DTO uploadFormTm5FromExcel(MultipartFile excelFile) throws Exception;

    /**
     *
     * @param excelFile
     * @return
     * @throws Exception
     */
    public FormTM4DTO uploadFormTm4FromExcel(MultipartFile excelFile) throws Exception;

    /**
     *
     * @param excelFile
     * @return
     * @throws Exception
     */
    public FormTM6DTO uploadFormTm6FromExcel(MultipartFile excelFile) throws Exception;

    /**
     *
     * @param excelFile
     * @return
     * @throws Exception
     */
    public FormTM7DTO uploadFormTm7FromExcel(MultipartFile excelFile) throws Exception;
}
