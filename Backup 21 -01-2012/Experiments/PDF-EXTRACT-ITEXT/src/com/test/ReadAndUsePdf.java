package com.test;

/**
 * Created by IntelliJ IDEA.
 * User: udoluweera
 * Date: 8/21/11
 * Time: 7:18 PM
 * To change this template use File | Settings | File Templates.
 */

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PRTokeniser;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.parser.ContentByteUtils;

import java.io.FileOutputStream;
import java.io.IOException;

public class ReadAndUsePdf {
    private static String INPUTFILE = "/home/udoluweera/Experiments/PDF-EXTRACT-ITEXT/cse.pdf";
    private static String OUTPUTFILE = "/home/udoluweera/Experiments/PDF-EXTRACT-ITEXT/cse-yo.pdf";

    public static void main(String[] args) throws DocumentException,
            IOException {
        Document document = new Document();

        PdfWriter writer = PdfWriter.getInstance(document,
                new FileOutputStream(OUTPUTFILE));
        document.open();
        PdfReader reader = new PdfReader(INPUTFILE);
        int n = reader.getNumberOfPages();
        PdfImportedPage page;
        // Go through all pages
        for (int i = 1; i <= n; i++) {
            // Only basic number 2 will be included
            if (i == 2) {


                //basic = writer.getImportedPage(reader, i);
            //    System.out.println(reader.getPdfObject(1).);

/*				basic = writer.getImportedPage(reader, i);
				Image instance = Image.getInstance(basic);
				document.add(instance);*/
            }
        }
        //document.close();
 // PdfReader reader = new PdfReader(src);
  byte[] streamBytes = reader.getPageContent(1);
  PRTokeniser tokenizer = new PRTokeniser(streamBytes);
        System.out.println(new String (ContentByteUtils.getContentBytesForPage(reader,1)));
  while (tokenizer.nextToken()) {
    if (tokenizer.getTokenType() == PRTokeniser.TokenType.STRING) {
       System.out.println(tokenizer.getStringValue());
    }
  }


    }

}
