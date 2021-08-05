package org.framework.basic;

/**
 * Created by IntelliJ IDEA.
 * User: udoluweera
 * Date: 8/23/11
 * Time: 2:25 PM
 * To change this template use File | Settings | File Templates.
 */
public class Document {

    private String tittle;
    private String previousTitle;
    private String afterTitle;
    private Long startLineNumber;
    private Long endLineNumber;
    private Long startPageNo;
    private Long endPageNo;

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public String getPreviousTitle() {
        return previousTitle;
    }

    public void setPreviousTitle(String previousTitle) {
        this.previousTitle = previousTitle;
    }

    public String getAfterTitle() {
        return afterTitle;
    }

    public void setAfterTitle(String afterTitle) {
        this.afterTitle = afterTitle;
    }

    public Long getStartLineNumber() {
        return startLineNumber;
    }

    public void setStartLineNumber(Long startLineNumber) {
        this.startLineNumber = startLineNumber;
    }

    public Long getEndLineNumber() {
        return endLineNumber;
    }

    public void setEndLineNumber(Long endLineNumber) {
        this.endLineNumber = endLineNumber;
    }

    public Long getStartPageNo() {
        return startPageNo;
    }

    public void setStartPageNo(Long startPageNo) {
        this.startPageNo = startPageNo;
    }

    public Long getEndPageNo() {
        return endPageNo;
    }

    public void setEndPageNo(Long endPageNo) {
        this.endPageNo = endPageNo;
    }
}
