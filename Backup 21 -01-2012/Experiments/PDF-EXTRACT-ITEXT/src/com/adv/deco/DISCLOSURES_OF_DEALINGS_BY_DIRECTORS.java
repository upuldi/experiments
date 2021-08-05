package com.adv.deco;

import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: udoluweera
 * Date: 8/23/11
 * Time: 11:20 AM
 * To change this template use File | Settings | File Templates.
 */
public class DISCLOSURES_OF_DEALINGS_BY_DIRECTORS {

    private static final String HEADING = "DISCLOSURES OF DEALINGS BY DIRECTORS OF LISTED COMPANIES";

    static String pdftoText(String fileName,int startPage, int endPage) {

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

			pdfStripper.setStartPage(startPage);
			pdfStripper.setEndPage(endPage);
			parsedText = pdfStripper.getText(pdDoc);


		} catch (Exception e) {
			System.err.println("An exception occured in parsing the PDF Document." + e.getMessage());
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


        System.out.println("Starting the application");
        String contentString = pdftoText("/home/udoluweera/Experiments/PDF-EXTRACT-ITEXT/CSE1.pdf",68,69);

        int count = 0;
        int pageBeginNo = 0;

        List<DataBlock> dataBlocks = new ArrayList<DataBlock>();


        for (String line : contentString.split("\\n")) {

            System.out.println(" Line " + count + " is : "  + line);

            String trimLine = line.trim();

            /* Set the Starting line of the basic. */
            if (HEADING.equalsIgnoreCase(trimLine)) {
                System.out.println("Required Page Found : " + count);
                pageBeginNo = count;
            }

            if ("PURCHASES".equalsIgnoreCase(trimLine) || "SALES".equalsIgnoreCase(trimLine) ) {
                DataBlock dataBlockFound = new DataBlock();
                dataBlockFound.setType(trimLine);
                dataBlocks.add(dataBlockFound);
            }


            count = count + 1;

        }

	}
}


class DataBlock {

    private String type;
    private ArrayList<String> content;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ArrayList<String> getContent() {
        return content;
    }

    public void setContent(ArrayList<String> content) {
        this.content = content;
    }
}