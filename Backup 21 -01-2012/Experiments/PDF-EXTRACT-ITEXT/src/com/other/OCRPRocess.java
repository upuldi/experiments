package com.other;

import com.asprise.util.ocr.OCR;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: udoluweera
 * Date: 8/22/11
 * Time: 7:20 PM
 * To change this template use File | Settings | File Templates.
 */
public class OCRPRocess {

    public static void main(String []args) throws IOException {

        BufferedImage image = ImageIO.read(new File("/home/udoluweera/Experiments/PDF-EXTRACT-ITEXT/Screenshot.png"));
        String s = new OCR().recognizeEverything(image);
        System.out.println("RESULTS: \n"+ s);








    }

}
