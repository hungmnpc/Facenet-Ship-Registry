package com.facenet.shipsregistry.service;

import com.facenet.shipsregistry.entity.*;
import com.facenet.shipsregistry.modal.*;
import com.facenet.shipsregistry.utils.MapperUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFFormulaEvaluator;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * @author: hungdinh
 * Date created: 11/04/2023
 */

@Service
@Slf4j
public class SheetServiceImpl implements SheetService{

    @Autowired
    private MapperUtils mapperUtils;

    /**
     *
     * @param file
     * @return
     * @throws IOException
     */
    private Workbook getWorkBookFromFile(MultipartFile file) throws IOException {
        Path tempDir = Files.createTempDirectory("");
        File tempFile =tempDir.resolve(Objects.requireNonNull(file.getOriginalFilename())).toFile();
        file.transferTo(tempFile);
        Workbook workbook = WorkbookFactory.create(tempFile);
        return workbook;
    }


    /**
     * @param excelFile
     * @return
     */
    @Override
    public FormTM1DTO uploadFormTm1FromExcel(MultipartFile excelFile) throws Exception {
        Workbook workbook = getWorkBookFromFile(excelFile);
        Sheet sheet = workbook.getSheetAt(0);
        FormTM1 formTM1 = new FormTM1(null, ""
                , sheet.getRow(0).getCell(1).getStringCellValue(), null
                , new ArrayList<>(), null);
        Stream<Row> rowStream = StreamSupport.stream(sheet.spliterator(), false);
        DataFormatter formatter = new DataFormatter();
        AtomicInteger index = new AtomicInteger();
        List<MeasurementTM1> measurementTM1List = new ArrayList<>();
        rowStream.forEach(row -> {
            Stream<Cell> cellStream = StreamSupport.stream(row.spliterator(), false);
            List<String> cellVals = cellStream.map(cell -> {
                return formatter.formatCellValue(cell,new HSSFFormulaEvaluator((HSSFWorkbook) workbook));
            }).toList();
            if (index.get() >= 4 && cellVals.size() > 0) {
                MeasurementTM1 measurementTM1 = createMeasurementTM1ByRow(cellVals);
                if (measurementTM1 != null) {
                    measurementTM1List.add(measurementTM1);
                }
                formTM1.setMeasurementTM1List(measurementTM1List);
            }
            index.addAndGet(1);
        });
        return mapperUtils.formTM1Mapper(formTM1);
    }

    /**
     *
     * @param row
     * @return
     */
    private MeasurementTM1 createMeasurementTM1ByRow(List<String> row) {
        if (!row.get(1).equals("")) {
            MeasurementTM1 measurementTM1 =
                    new MeasurementTM1(null, row.get(0).trim(), row.get(1).trim(), null, null, null);
            DetailMeasurement forward = createDetailMeasurement(row, 2, 3, 4);
            DetailMeasurement after = createDetailMeasurement(row, 2, 11, 12);
            measurementTM1.setForwardReadingMeasurementDetail(forward);
            measurementTM1.setAfterReadingMeasurementDetail(after);
            return measurementTM1;
        } else {
            return null;
        }
    }

    /**
     *
     * @param excelFile
     * @return
     * @throws Exception
     */
    @Override
    public FormTM2DTO uploadFormTm2FromExcel(MultipartFile excelFile) throws Exception {
        Workbook workbook = getWorkBookFromFile(excelFile);
        Sheet sheet = workbook.getSheetAt(0);
        Stream<Row> rowStream = StreamSupport.stream(sheet.spliterator(), false);
        DataFormatter formatter = new DataFormatter();
        AtomicInteger i = new AtomicInteger();
        FormTM2 formTM2 = new FormTM2();
        List<MeasurementTM2> measurementTM2List = new ArrayList<>();
        rowStream.forEach(row -> {
            Stream<Cell> cellStream = StreamSupport.stream(row.spliterator(), false);
            List<String> cellVals = cellStream
                    .map(cell -> formatter
                            .formatCellValue(cell,new HSSFFormulaEvaluator((HSSFWorkbook) workbook)))
                    .toList();
            if (i.get() == 2 ) {
                formTM2.setFirstFrameNoTM2(cellVals.get(6).trim());
                formTM2.setSecondFrameNoTM2(cellVals.get(17).trim());
                formTM2.setThirdFrameNoTM2(cellVals.get(28).trim());
            }
            if (!cellVals.get(0).equals("") && i.get() >=5) {
                log.info("{}", cellVals);
                MeasurementTM2 measurementTM2 = createMeasurementTM2(cellVals);
                measurementTM2List.add(measurementTM2);
            }
            i.set(i.get() + 1);
        });
        formTM2.setMeasurementTM2List(measurementTM2List);
        return mapperUtils.formTM2Mapper(formTM2);
    }

    /**
     *
     * @param cellVals
     * @return
     */
    private MeasurementTM2 createMeasurementTM2(List<String> cellVals) {
        MeasurementTM2 measurementTM2 = new MeasurementTM2();
        measurementTM2.setStrakePosition(cellVals.get(0));
        measurementTM2.setNoOrLetter(cellVals.get(1));
        DetailMeasurement first = createDetailMeasurement(cellVals, 2, 4, 5);
        DetailMeasurement second = createDetailMeasurement(cellVals, 13, 15, 16);
        DetailMeasurement third = createDetailMeasurement(cellVals, 24, 26, 27);
        measurementTM2.setFirstTransverseSectionMeasurementDetailTM2(first);
        measurementTM2.setSecondTransverseSectionMeasurementDetailTM2(second);
        measurementTM2.setThirdTransverseSectionMeasurementDetailTM2(third);
        return measurementTM2;
    }

    /**
     *
     * @param cellVals
     * @param original
     * @param gaugedP
     * @param gaugedS
     * @return
     */
    private DetailMeasurement createDetailMeasurement(List<String> cellVals, Integer original,
                                                      Integer gaugedP, Integer gaugedS) {
        Double originalValue =
                !cellVals.get(original).equals("") ? Double.parseDouble(cellVals.get(original)) : null;
        Double gaugedPValue =
                !cellVals.get(gaugedP).equals("") ? Double.parseDouble(cellVals.get(gaugedP)) : null;
        Double gaugedSValue =
                !cellVals.get(gaugedS).equals("") ? Double.parseDouble(cellVals.get(gaugedS)) : null;
        return new DetailMeasurement(originalValue, null, gaugedPValue, gaugedSValue, "");
    }

    /**
     * @param excelFile
     * @return
     * @throws Exception
     */
    @Override
    public FormTM3DTO uploadFormTm3FromExcel(MultipartFile excelFile) throws Exception {
        Workbook workbook = getWorkBookFromFile(excelFile);
        Sheet sheet = workbook.getSheetAt(0);
        Stream<Row> rowStream = StreamSupport.stream(sheet.spliterator(), false);
        DataFormatter formatter = new DataFormatter();
        AtomicInteger i = new AtomicInteger();
        FormTM3 formTM3 = new FormTM3();
        List<MeasurementTM3> measurementTM3List = new ArrayList<>();
        rowStream.forEach(row -> {
            Stream<Cell> cellStream = StreamSupport.stream(row.spliterator(), false);
            List<String> cellVals = cellStream.
                    map(cell -> formatter
                            .formatCellValue(cell,new HSSFFormulaEvaluator((HSSFWorkbook) workbook))).toList();
            log.info("{}", cellVals);
            if (i.get() == 1 ) {
                formTM3.setFirstFrameNo(cellVals.get(6).trim());
                formTM3.setSecondFrameNo(cellVals.get(17).trim());
                formTM3.setThirdFrameNo(cellVals.get(28).trim());
            }

            if (i.get() >= 4 && !cellVals.get(0).equals("")) {
                MeasurementTM3 measurementTM3 = createMeasurementTM3(cellVals);
                measurementTM3List.add(measurementTM3);
            }
            i.set(i.get() + 1);
        });
        formTM3.setMeasurementTM3List(measurementTM3List);
        return mapperUtils.formTM3Mapper(formTM3);
    }

    /**
     *
     * @param cellVals
     * @return
     */
    private MeasurementTM3 createMeasurementTM3 (List<String> cellVals) {
        MeasurementTM3 measurementTM3 = new MeasurementTM3();
        measurementTM3.setStructuralMember(cellVals.get(0));
        measurementTM3.setNoOrLetter(cellVals.get(1));
        DetailMeasurement first = createDetailMeasurement(cellVals, 2, 4, 5);
        DetailMeasurement second = createDetailMeasurement(cellVals, 13, 15, 16);
        DetailMeasurement third= createDetailMeasurement(cellVals, 24, 26, 27);
        measurementTM3.setFirstTransverseSectionMeasurementDetailTM3(first);
        measurementTM3.setSecondTransverseSectionMeasurementDetailTM3(second);
        measurementTM3.setThirdTransverseSectionMeasurementDetailTM3(third);
        return measurementTM3;
    }

    /**
     * @param excelFile
     * @return
     * @throws Exception
     */
    @Override
    public FormTM5DTO uploadFormTm5FromExcel(MultipartFile excelFile) throws Exception {
        Workbook workbook = getWorkBookFromFile(excelFile);
        Sheet sheet = workbook.getSheetAt(0);
        Stream<Row> rowStream = StreamSupport.stream(sheet.spliterator(), false);
        DataFormatter formatter = new DataFormatter();
        AtomicInteger i = new AtomicInteger();
        FormTM5 formTM5 = new FormTM5();
        formTM5.setFrameNo(String.valueOf((int)sheet.getRow(1).getCell(11).getNumericCellValue()));
        formTM5.setTankHolDescription(sheet.getRow(0).getCell(3).getStringCellValue());
        List<MeasurementTM5> measurementTM5List = new ArrayList<>();
        rowStream.forEach(row -> {
            Stream<Cell> cellStream = StreamSupport.stream(row.spliterator(), false);
            List<String> cellVals = cellStream.
                    map(cell -> formatter
                            .formatCellValue(cell,new HSSFFormulaEvaluator((HSSFWorkbook) workbook))).toList();
            log.info("{}", cellVals);
            i.set(i.get() + 1);
        });
        formTM5.setMeasurementTM5List(measurementTM5List);
        return mapperUtils.formTM5Mapper(formTM5);
    }
}