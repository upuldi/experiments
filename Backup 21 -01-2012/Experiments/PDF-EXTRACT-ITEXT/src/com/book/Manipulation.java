package com.book;

import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfReader;

import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: udoluweera
 * Date: 8/21/11
 * Time: 8:14 PM
 * To change this template use File | Settings | File Templates.
 */
public class Manipulation {
    
    public static void main(String []args) throws IOException {
        
        
        System.out.println("Starting");
        inspect("/home/udoluweera/Experiments/PDF-EXTRACT-ITEXT/cse.pdf");
        
    }

    public static void inspect(String filename)
            throws IOException {

        
        PdfReader reader = new PdfReader(filename);
        System.out.println(filename);
        System.out.print("Number of pages: ");
        System.out.println(reader.getNumberOfPages());
        Rectangle mediabox = reader.getPageSize(1);
        System.out.print("Size of basic 1: [");
        System.out.print(mediabox.getLeft());
        System.out.print(',');
        System.out.print(mediabox.getBottom());
        System.out.print(',');
        System.out.print(mediabox.getRight());
        System.out.print(',');
        System.out.print(mediabox.getTop());
        System.out.println("]");
        System.out.print("Rotation of basic 1: ");
        System.out.println(reader.getPageRotation(1));
        System.out.print("Page size with rotation of basic 1: ");
        System.out.println(reader.getPageSizeWithRotation(1));
        System.out.print("File length: ");
        System.out.println(reader.getFileLength());
        System.out.print("Is rebuilt? ");
        System.out.println(reader.isRebuilt());
        System.out.print("Is encrypted? ");
        System.out.println(reader.isEncrypted());
        System.out.println();
        System.out.flush();
    }
}
