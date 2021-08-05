package com.domain;

/**
 * Created by IntelliJ IDEA.
 * User: udoluweera
 * Date: 9/6/11
 * Time: 11:37 PM
 * To change this template use File | Settings | File Templates.
 */
public class Span  implements Comparable<Span>  {

    Integer left;
    Integer top;
    Integer fontSize;
    Integer widthSize;
    Integer height;
    String textContent;

    public Integer getLeft() {
        return left;
    }

    public void setLeft(Integer left) {
        this.left = left;
    }

    public Integer getTop() {
        return top;
    }

    public void setTop(Integer top) {
        this.top = top;
    }

    public Integer getFontSize() {
        return fontSize;
    }

    public void setFontSize(Integer fontSize) {
        this.fontSize = fontSize;
    }

    public Integer getWidthSize() {
        return widthSize;
    }

    public void setWidthSize(Integer widthSize) {
        this.widthSize = widthSize;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public String getTextContent() {
        return textContent;
    }

    public void setTextContent(String textContent) {
        this.textContent = textContent;
    }

    public int compareTo(Span o) {
        return this.getLeft() - o.getLeft();
    }
}
