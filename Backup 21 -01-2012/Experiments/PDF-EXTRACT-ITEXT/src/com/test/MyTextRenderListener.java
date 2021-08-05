package com.test;

import com.itextpdf.text.pdf.parser.ImageRenderInfo;
import com.itextpdf.text.pdf.parser.RenderListener;
import com.itextpdf.text.pdf.parser.TextRenderInfo;

import java.io.PrintWriter;

/**
 * Created by IntelliJ IDEA.
 * User: udoluweera
 * Date: 8/21/11
 * Time: 9:41 PM
 * To change this template use File | Settings | File Templates.
 */
public class MyTextRenderListener implements RenderListener {

    protected PrintWriter out;

    public MyTextRenderListener(PrintWriter out) {
        this.out = out;
    }

    public void beginTextBlock() {
        out.print("<");
    }

    public void endTextBlock() {
        out.println(">");
    }

    public void renderImage(ImageRenderInfo renderInfo) {
    }

    public void renderText(TextRenderInfo renderInfo) {
        out.print("<");
        out.print(renderInfo.getText());
        out.print(">");
    }
}