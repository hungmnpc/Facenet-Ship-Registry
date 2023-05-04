package com.facenet.shipsregistry.service;

import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfCopy;
import com.lowagie.text.pdf.PdfDocument;
import com.lowagie.text.pdf.PdfLayerMembership;
import com.lowagie.text.pdf.PdfWriter;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.pdfbox.multipdf.PDFMergerUtility;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.print.Doc;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author: hungdinh
 * Date created: 11/04/2023
 */

@Service
@Transactional
public class PDFGeneratorServiceImpl implements PDFGeneratorService{

    /**
     * @param response
     */
    @Override
    public void export(HttpServletResponse response, String reportNo) throws IOException {
        Files.createDirectories(Paths.get(reportNo));
        Document document = new Document(PageSize.A4.rotate());
        FontFactory.register("fonts/Arial/arialn/Arialn.ttf", "Arial");

        PdfWriter.getInstance(document, new FileOutputStream(reportNo + "/2.pdf"));
        document.open();
        Font fontTitle = FontFactory.getFont("Arial", 9f);
        Paragraph paragraph = new Paragraph("This is title. ", fontTitle);
        paragraph.setAlignment(Paragraph.ALIGN_CENTER);

        Font fontParagraph = FontFactory.getFont(FontFactory.HELVETICA);
        fontParagraph.setSize(12);

        Paragraph paragraph1 = new Paragraph("This is paragraph.", fontParagraph);
        paragraph1.setAlignment(Paragraph.ALIGN_LEFT);
        document.add(paragraph);
        document.add(paragraph1);

        Document document2 = new Document(PageSize.A4);
        document2.setMargins(1f, 1f, 1f, 1f);
        FontFactory.register("fonts/Arial/arialn/Arialn.ttf", "Arial");

        PdfWriter.getInstance(document2, new FileOutputStream(reportNo + "/1.pdf"));
        document2.open();
        Font fontTitle2 = FontFactory.getFont("Arial", 9f);
        Paragraph paragraph2 = new Paragraph("This is title. ", fontTitle2);
        paragraph2.setAlignment(Paragraph.ALIGN_CENTER);

        Font fontParagraph2 = FontFactory.getFont(FontFactory.HELVETICA);
        fontParagraph2.setSize(12);

        Paragraph paragraph3 = new Paragraph("This is paragraph.", fontParagraph2);
        paragraph3.setAlignment(Paragraph.ALIGN_LEFT);
        document2.add(paragraph2);
        document2.add(paragraph3);
        int i = 0;
        while (i++ < 100) {
            document2.add(paragraph3);
        }
        document2.close();
        document.close();
        createMenu(reportNo);
        File file1 = new File(reportNo + "/1.pdf");
        File file2 = new File(reportNo + "/2.pdf");
        File file3 = new File(reportNo + "/menu.pdf");
        PDFMergerUtility pdfMergerUtility = new PDFMergerUtility();

        pdfMergerUtility.setDestinationFileName(reportNo + "/main.pdf");

        pdfMergerUtility.addSource(file1);
        pdfMergerUtility.addSource(file2);
        pdfMergerUtility.addSource(file3);

        pdfMergerUtility.mergeDocuments();

    }

    /**
     * @param reportNo
     */
    @Override
    public void createMenu(String reportNo) throws IOException {
        Document document = new Document(PageSize.A4);
        FontFactory.register("fonts/Arial/arialn/Arialn.ttf", "Arial");
        PdfWriter.getInstance(document, new FileOutputStream(reportNo + "/menu.pdf"));
        document.open();
        Font fontTitle = FontFactory.getFont("Arial", 9f);
        Paragraph paragraph = new Paragraph("This is title. ", fontTitle);
        paragraph.setAlignment(Paragraph.ALIGN_CENTER);

        Font fontParagraph = FontFactory.getFont(FontFactory.HELVETICA);
        fontParagraph.setSize(12);

        Paragraph paragraph1 = new Paragraph("This is paragraph.", fontParagraph);
        paragraph1.setAlignment(Paragraph.ALIGN_LEFT);
        document.add(paragraph);
        document.add(paragraph1);
        document.close();
    }

    /**
     *
     * @param reportNo
     * @throws IOException
     */
    private void creatGP(String reportNo) throws IOException {
        Document document = new Document(PageSize.A4);
        FontFactory.register("fonts/Arial/arialn/Arialn.ttf", "Arial");
        PdfWriter.getInstance(document, new FileOutputStream(reportNo + "/gp.pdf"));
        document.open();
        Font fontTitle = FontFactory.getFont("Arial", 9f);
        Paragraph paragraph = new Paragraph("This is title. ", fontTitle);
        paragraph.setAlignment(Paragraph.ALIGN_CENTER);

        Font fontParagraph = FontFactory.getFont(FontFactory.HELVETICA);
        fontParagraph.setSize(12);

        Paragraph paragraph1 = new Paragraph("This is paragraph.", fontParagraph);
        paragraph1.setAlignment(Paragraph.ALIGN_LEFT);
        document.add(paragraph);
        document.add(paragraph1);
        document.close();
    }
}