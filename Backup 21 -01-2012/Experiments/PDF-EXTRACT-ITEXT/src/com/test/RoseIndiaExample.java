package com.test;

import com.itextpdf.text.pdf.PdfDictionary;
import com.itextpdf.text.pdf.PdfName;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by IntelliJ IDEA.
 * User: udoluweera
 * Date: 8/21/11
 * Time: 6:17 PM
 * To change this template use File | Settings | File Templates.
 */
public class RoseIndiaExample {

    public static void main(String[] args) throws IOException {

/*          RoseIndiaExample roseIndiaExample = new RoseIndiaExample();
        roseIndiaExample.extractText("/home/udoluweera/Experiments/PDF-EXTRACT-ITEXT/cse.pdf","/home/udoluweera/Experiments/PDF-EXTRACT-ITEXT/testpage.xml");*/


        PdfReader reader = new PdfReader("/home/udoluweera/Experiments/PDF-EXTRACT-ITEXT/cse.pdf");
        PdfReaderContentParser parser = new PdfReaderContentParser(reader);
        PrintWriter out = new PrintWriter(new FileOutputStream("/home/udoluweera/Experiments/PDF-EXTRACT-ITEXT/txt.txt"));
        TextExtractionStrategy strategy;
        for (int i = 1; i <= reader.getNumberOfPages(); i++) {
            strategy = parser.processContent(i, new SimpleTextExtractionStrategy());
            out.println(strategy.getResultantText());
        }
        out.flush();
        out.close();


    }

    public void extractText(String src, String dest) throws IOException {

        PrintWriter out = new PrintWriter(new FileOutputStream(dest));
        PdfReader reader = new PdfReader(src);
        RenderListener listener = new MyTextRenderListener(out);
        PdfContentStreamProcessor processor = new PdfContentStreamProcessor(listener);
        PdfDictionary pageDic = reader.getPageN(1);
        PdfDictionary resourcesDic = pageDic.getAsDict(PdfName.RESOURCES);
        processor.processContent(ContentByteUtils.getContentBytesForPage(reader, 1), resourcesDic);
        out.flush();
        out.close();
    }

}
