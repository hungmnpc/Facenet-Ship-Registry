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
        formTM5.setLocationOfStructure(sheet.getRow(1).getCell(3).getStringCellValue());
        List<StructuralTM5> structuralTM5List = new ArrayList<>();
        StructuralTM5 structuralTM5 = new StructuralTM5();
        structuralTM5.setMeasurementTM5List(new ArrayList<>());
        rowStream.forEach(row -> {
            Stream<Cell> cellStream = StreamSupport.stream(row.spliterator(), false);
            List<String> cellVals = cellStream.
                    map(cell -> formatter
                            .formatCellValue(cell,new HSSFFormulaEvaluator((HSSFWorkbook) workbook))).toList();
            if (i.get() >=4 && !cellVals.get(0).equals("")) {
                if (structuralTM5.getName() == null) {
                    structuralTM5.setName("");
                }
                if (!cellVals.get(0).equals("") && cellVals.get(3).equals("")) {
                    if (structuralTM5.getName() != null) {
                        structuralTM5List.add(copy(structuralTM5));
                        structuralTM5.setName(cellVals.get(0));
                        structuralTM5.setMeasurementTM5List(new ArrayList<>());
                    }
                } else  {
                    MeasurementTM5 measurementTM5 = createMeasurementTM5(cellVals);
                    structuralTM5.getMeasurementTM5List().add(measurementTM5);
                }
            }
            i.set(i.get() + 1);
        });
        formTM5.setStructuralTM5List(structuralTM5List);
        return mapperUtils.formTM5Mapper(formTM5);
    }

    /**
     *
     * @param structuralTM5
     * @return
     */
    private StructuralTM5 copy(StructuralTM5 structuralTM5) {
        return new StructuralTM5(null, structuralTM5.getName(), structuralTM5.getFormTM5(),
                structuralTM5.getMeasurementTM5List());
    }

    /**
     *
     * @param cellVals
     * @return
     */
    private MeasurementTM5 createMeasurementTM5(List<String> cellVals) {
        MeasurementTM5 measurementTM5 = new MeasurementTM5();
        measurementTM5.setItem(cellVals.get(3));
        measurementTM5.setStructuralComponentType(cellVals.get(0));
        DetailMeasurement detailMeasurement = createDetailMeasurement(cellVals, 4, 6, 7);
        measurementTM5.setMeasurementDetail(detailMeasurement);
        return measurementTM5;
    }

    /**
     * @param excelFile
     * @return
     * @throws Exception
     */
    @Override
    public FormTM4DTO uploadFormTm4FromExcel(MultipartFile excelFile) throws Exception {
        Workbook workbook = getWorkBookFromFile(excelFile);
        Sheet sheet = workbook.getSheetAt(0);
        Stream<Row> rowStream = StreamSupport.stream(sheet.spliterator(), false);
        DataFormatter formatter = new DataFormatter();
        AtomicInteger i = new AtomicInteger();
        FormTM4 formTM4 = new FormTM4();
        formTM4.setLocationOfStructure(sheet.getRow(1).getCell(3).getStringCellValue());
        formTM4.setTankDescription(sheet.getRow(0).getCell(3).getStringCellValue());
        List<StructuralMemberTM4> structuralMemberTM4List = new ArrayList<>();
        StructuralMemberTM4 structuralMemberTM4 = new StructuralMemberTM4();
        structuralMemberTM4.setMeasurementTM4List(new ArrayList<>());
        rowStream.forEach(row -> {
            Stream<Cell> cellStream = StreamSupport.stream(row.spliterator(), false);
            List<String> cellVals = cellStream.
                    map(cell -> formatter
                            .formatCellValue(cell,new HSSFFormulaEvaluator((HSSFWorkbook) workbook))).toList();
            if (i.get() >= 4 && !cellVals.get(0).equals("")) {
                if (i.get() == 4) {
                    if (cellVals.get(3).equals("") && !cellVals.get(0).equals("")) {
                        structuralMemberTM4.setStructuralMemberTitle(cellVals.get(0));
                    } else {
                        structuralMemberTM4List.add(structuralMemberTM4);
                    }
                } else {
                    if (cellVals.get(3).equals("")) {
                        structuralMemberTM4List.add(copy(structuralMemberTM4));
                        structuralMemberTM4.setMeasurementTM4List(new ArrayList<>());
                        structuralMemberTM4.setStructuralMemberTitle(cellVals.get(0));
                    } else {
                        MeasurementTM4 measurementTM4 = createMeasurementTM4(cellVals);
                        structuralMemberTM4.getMeasurementTM4List().add(measurementTM4);
                    }
                }
            }
            i.set(i.get() + 1);
        });
        if (structuralMemberTM4.getStructuralMemberTitle() != null) {
            structuralMemberTM4List.add(structuralMemberTM4);
        }
        formTM4.setStructuralMemberTM4List(structuralMemberTM4List);
        return mapperUtils.formTM4Mapper(formTM4);
    }

    /**
     *
     * @param structuralMemberTM4
     * @return
     */
    private StructuralMemberTM4 copy (StructuralMemberTM4 structuralMemberTM4) {
        return new StructuralMemberTM4(structuralMemberTM4.getId(),
                structuralMemberTM4.getStructuralMemberTitle(),
                structuralMemberTM4.getFormTM4(),
                structuralMemberTM4.getMeasurementTM4List());
    }

    /**
     *
     * @param cellVal
     * @return
     */
    private MeasurementTM4 createMeasurementTM4(List<String> cellVal) {
        MeasurementTM4 measurementTM4 = new MeasurementTM4();
        measurementTM4.setItem(cellVal.get(3));
        measurementTM4.setStructuralMember(cellVal.get(0));

        DetailMeasurement detailMeasurement = createDetailMeasurement(cellVal, 4, 6 , 7);
        measurementTM4.setDetailMeasurement(detailMeasurement);
        return measurementTM4;
    }

    /**
     * @param excelFile
     * @return
     * @throws Exception
     */
    @Override
    public FormTM6DTO uploadFormTm6FromExcel(MultipartFile excelFile) throws Exception {
        Workbook workbook = getWorkBookFromFile(excelFile);
        Sheet sheet = workbook.getSheetAt(0);
        Stream<Row> rowStream = StreamSupport.stream(sheet.spliterator(), false);
        DataFormatter formatter = new DataFormatter();
        AtomicInteger i = new AtomicInteger();
        FormTM6 formTM6 = new FormTM6();
        formTM6.setLocationOfStructure(sheet.getRow(2).getCell(3).getStringCellValue());
        formTM6.setStructuralMembers(sheet.getRow(0).getCell(3).getStringCellValue());
        formTM6.setStructuralDescriptionTM6List(new ArrayList<>());
        StructuralDescriptionTM6 structuralDescriptionTM6 = new StructuralDescriptionTM6();
        structuralDescriptionTM6.setMeasurementTM6List(new ArrayList<>());
        List<StructuralDescriptionTM6> structuralDescriptionTM6List = new ArrayList<>();
        rowStream.forEach(row -> {
            Stream<Cell> cellStream = StreamSupport.stream(row.spliterator(), false);
            List<String> cellVals = cellStream.
                    map(cell -> formatter
                            .formatCellValue(cell,new HSSFFormulaEvaluator((HSSFWorkbook) workbook)))
                    .toList();
            if (i.get() >= 6 && !cellVals.get(0).equals("")) {
                if (i.get() == 6) {
                    if (cellVals.get(3).equals("") && !cellVals.get(0).equals("")) {
                        structuralDescriptionTM6.setStructuralDescriptionTitle(cellVals.get(0));
                    } else {
                        structuralDescriptionTM6List.add(structuralDescriptionTM6);
                    }
                } else {
                    if (cellVals.get(3).equals("")) {
                        structuralDescriptionTM6List.add( copy(structuralDescriptionTM6));
                        structuralDescriptionTM6.setMeasurementTM6List(new ArrayList<>());
                        structuralDescriptionTM6.setStructuralDescriptionTitle(cellVals.get(0));
                    } else {
                        MeasurementTM6 measurementTM6 = createMeasurementTM6(cellVals);
                        structuralDescriptionTM6.getMeasurementTM6List().add(measurementTM6);
                    }
                }
            }
            i.set(i.get() + 1);
        });
        if (structuralDescriptionTM6 .getStructuralDescriptionTitle() != null) {
            structuralDescriptionTM6List.add(structuralDescriptionTM6);
        }
        formTM6.setStructuralDescriptionTM6List(structuralDescriptionTM6List);
        return mapperUtils.formTM6Mapper(formTM6);
    }

    /**
     *
     * @param structuralDescriptionTM6
     * @return
     */
    private StructuralDescriptionTM6 copy(StructuralDescriptionTM6 structuralDescriptionTM6) {
        return new StructuralDescriptionTM6(structuralDescriptionTM6.getId(),
                structuralDescriptionTM6.getStructuralDescriptionTitle(),
                structuralDescriptionTM6.getFormTM6(),
                structuralDescriptionTM6.getMeasurementTM6List());
    }

    /**
     *
     * @param cellVals
     * @return
     */
    private MeasurementTM6 createMeasurementTM6(List<String> cellVals) {
        MeasurementTM6 measurementTM6 = new MeasurementTM6();
        measurementTM6.setDescription(cellVals.get(0));
        measurementTM6.setItem(cellVals.get(2));
        DetailMeasurement detailMeasurement = createDetailMeasurement(cellVals, 3,5, 6);
        measurementTM6.setDetailMeasurement(detailMeasurement);
        return measurementTM6;
    }

    /**
     * @param excelFile
     * @return
     * @throws Exception
     */
    @Override
    public FormTM7DTO uploadFormTm7FromExcel(MultipartFile excelFile) throws Exception {
        Workbook workbook = getWorkBookFromFile(excelFile);
        Sheet sheet = workbook.getSheetAt(0);
        Stream<Row> rowStream = StreamSupport.stream(sheet.spliterator(), false);
        DataFormatter formatter = new DataFormatter();
        AtomicInteger i = new AtomicInteger();
        FormTM7 formTM7 = new FormTM7();
        formTM7.setName(sheet.getRow(0).getCell(0).getStringCellValue());
        List<FrameNumber> frameNumberList = new ArrayList<>();
        FrameNumber frameNumber = new FrameNumber();
        frameNumber.setMeasurementTM7List(new ArrayList<>());
        rowStream.forEach(row -> {
            Stream<Cell> cellStream = StreamSupport.stream(row.spliterator(), false);
            List<String> cellVals = cellStream.
                    map(cell -> formatter
                            .formatCellValue(cell,new HSSFFormulaEvaluator((HSSFWorkbook) workbook)))
                    .toList();
            if (i.get() >= 4 && !cellVals.get(0).equals("")) {
                if (frameNumber.getName() == null) {
                    frameNumber.setName(cellVals.get(0));
                } else {
                    if (i.get() > 4 && cellVals.get(1).equals("") && !cellVals.get(0).equals("") ) {
                        frameNumberList.add( copy(frameNumber));
                        frameNumber.setName(cellVals.get(0));
                        frameNumber.setMeasurementTM7List(new ArrayList<>());
                    } else {
                        MeasurementTM7 measurementTM7 = createMeasurementTM7(cellVals);
                        frameNumber.getMeasurementTM7List().add(measurementTM7);
                    }
                }
            }
            i.set(i.get() + 1);
        });
        frameNumberList.add(frameNumber);
        formTM7.setFrameNumber(frameNumberList);
        return mapperUtils.formTM7Mapper(formTM7);
    }

    /**
     *
     * @param frameNumber
     * @return
     */
    private FrameNumber copy (FrameNumber frameNumber) {
        FrameNumber frameNumberCopy = new FrameNumber(frameNumber.getName());
        frameNumberCopy.setMeasurementTM7List(frameNumber.getMeasurementTM7List());
        return frameNumber;
    }

    /**
     *
     * @param cellVals
     * @return
     */
    private MeasurementTM7 createMeasurementTM7(List<String> cellVals) {
        MeasurementTM7 measurementTM7 = new MeasurementTM7();
        measurementTM7.setItem(cellVals.get(0));
        DetailMeasurement upper = createDetailMeasurement(cellVals, 1, 3, 4);
        DetailMeasurement mid = createDetailMeasurement(cellVals, 9, 11, 12);
        DetailMeasurement lower = createDetailMeasurement(cellVals, 17, 19, 20);
        measurementTM7.setUpperPart(upper);
        measurementTM7.setMidPart(mid);
        measurementTM7.setLowerPart(lower);
        return measurementTM7;
    }
}