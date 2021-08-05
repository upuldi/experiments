package com.test;

import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: udoluweera
 * Date: 8/21/11
 * Time: 11:36 PM
 * To change this template use File | Settings | File Templates.
 */
public class Pdf2Html {

	// Extract text from PDF Document
	static String pdftoText(String fileName) {

		PDFParser parser;
		String parsedText = null;;
		PDFTextStripper pdfStripper = null;
		PDDocument pdDoc = null;
		COSDocument cosDoc = null;
		File file = new File(fileName);
		if (!file.isFile()) {
			System.err.println("File " + fileName + " does not exist.");
			return null;
		}
		try {
			parser = new PDFParser(new FileInputStream(file));
		} catch (IOException e) {
			System.err.println("Unable to open PDF Parser. " + e.getMessage());
			return null;
		}
		try {
			parser.parse();
			cosDoc = parser.getDocument();

            pdfStripper = new PDFTextStripper();
			pdDoc = new PDDocument(cosDoc);

/*            PDFText2HTML pdfText2HTML = new PDFText2HTML("UTF-8");
            pdfText2HTML.setStartPage(1);
            pdfText2HTML.setEndPage(5);
           parsedText =  pdfText2HTML.getText(pdDoc);*/

			pdfStripper.setStartPage(7);
			pdfStripper.setEndPage(7);
			parsedText = pdfStripper.getText(pdDoc);


		} catch (Exception e) {
			System.err
					.println("An exception occured in parsing the PDF Document."
							+ e.getMessage());
		} finally {
			try {
				if (cosDoc != null)
					cosDoc.close();
				if (pdDoc != null)
					pdDoc.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return parsedText;
	}
	public static void main(String args[]){
		System.out.println(pdftoText("/home/udoluweera/Experiments/PDF-EXTRACT-ITEXT/CSE1.pdf"));
	}

}
