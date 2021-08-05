package org.framework.engine;

import org.apache.log4j.Logger;
import org.framework.basic.Document;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: udoluweera
 * Date: 8/23/11
 * Time: 2:40 PM
 * To change this template use File | Settings | File Templates.
 */
public class PROCESS_DOCUMENTS {

    Logger log = Logger.getLogger("PROCESS_DOCUMENTS");

    List<String> headings = new ArrayList<String>();

    public List<Document> getDocumentsList(String content) {

        List<Document> returnDocumentList = new ArrayList<Document>();
        Document lastProcessedDocument = null;

        log.debug("Start Processing For Obtain Documents....");

        int count = 0;

        for (String line : content.split("\\n")) {

            log.debug("Processing Line : " + line);

            String trimLine = line.trim();

            if(headings.contains(trimLine)) {

                log.debug("Document Found");
                Document document = new Document();
                document.setTittle(trimLine);

                if (null != lastProcessedDocument ) {
                    document.setPreviousTitle(lastProcessedDocument.getTittle());
                }



            }











            count = count + 1;

        }





        return null;

    }

}
