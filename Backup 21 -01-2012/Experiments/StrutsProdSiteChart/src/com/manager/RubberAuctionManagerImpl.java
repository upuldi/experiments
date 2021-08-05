package com.manager;

import com.dao.RubberAuctionDao;
import com.dto.RubberPriceFormatted;
import com.domain.RubberPrice;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: udoluweera
 * Date: 6/30/11
 * Time: 6:24 PM
 * To change this template use File | Settings | File Templates.
 */
public class RubberAuctionManagerImpl implements RubberAuctionManager{

    private static Logger log = Logger.getLogger(RubberAuctionManagerImpl.class);

    RubberAuctionDao rubberAuctionDao;

    public void setRubberAuctionDao(RubberAuctionDao rubberAuctionDao) {
        this.rubberAuctionDao = rubberAuctionDao;
    }

    public List<RubberPriceFormatted> getRubberAuctionData() {

        log.debug("Inside getRubberAuctionData");

        List<RubberPriceFormatted> returnList = new ArrayList<RubberPriceFormatted>();
        Map<String,List<RubberPrice>> pricesGroupedDate = new HashMap<String, List<RubberPrice>>();

        List<RubberPrice> rubberPricesList = rubberAuctionDao.getRubberAuctionPrices();

        log.debug("Dao data returned with : " + rubberPricesList.size());

        for (RubberPrice rubberPrice : rubberPricesList ) {

            if (null != pricesGroupedDate.get(rubberPrice.getDate())) {
                List<RubberPrice> list = pricesGroupedDate.get(rubberPrice.getDate());
                list.add(rubberPrice);

            } else {
                List<RubberPrice> list = new ArrayList<RubberPrice>();
                list.add(rubberPrice);
                pricesGroupedDate.put(rubberPrice.getDate(),list);
            }
        }

        for (Map.Entry<String,List<RubberPrice>> entry : pricesGroupedDate.entrySet() ) {


            RubberPriceFormatted rubberPriceFormatted = new RubberPriceFormatted();
            rubberPriceFormatted.setDate(entry.getKey());

            for (RubberPrice rubberPrice : entry.getValue()) {

                if ("TPC 1X".equalsIgnoreCase(rubberPrice.getItem().trim()) && null != rubberPrice.getPriceLKR()) {
                    rubberPriceFormatted.setTpc1x(Integer.parseInt(rubberPrice.getPriceLKR()));
                }
                if ("TPC 2".equalsIgnoreCase(rubberPrice.getItem().trim()) && null != rubberPrice.getPriceLKR() ) {
                    rubberPriceFormatted.setTpc2(Integer.parseInt(rubberPrice.getPriceLKR()));
                }
                if ("TPC 3".equalsIgnoreCase(rubberPrice.getItem().trim()) && null != rubberPrice.getPriceLKR() ) {
                    rubberPriceFormatted.setTpc3(Integer.parseInt(rubberPrice.getPriceLKR()));
                }
                if ("TPC 4".equalsIgnoreCase(rubberPrice.getItem().trim()) && null != rubberPrice.getPriceLKR() ) {
                    rubberPriceFormatted.setTpc4(Integer.parseInt(rubberPrice.getPriceLKR()));
                }
                if ("Brown Crepe 1X".equalsIgnoreCase(rubberPrice.getItem().trim()) && null != rubberPrice.getPriceLKR()  ) {
                    rubberPriceFormatted.setBrownCrepe1x(Integer.parseInt(rubberPrice.getPriceLKR()));
                }
                if ("Brown Crepe 2X".equalsIgnoreCase(rubberPrice.getItem().trim()) && null != rubberPrice.getPriceLKR()  ) {
                    rubberPriceFormatted.setBrownCrepe2x(Integer.parseInt(rubberPrice.getPriceLKR()));
                }
                if ("Brown Crepe 3X".equalsIgnoreCase(rubberPrice.getItem().trim()) && null != rubberPrice.getPriceLKR() ) {
                    rubberPriceFormatted.setBrownCrepe3x(Integer.parseInt(rubberPrice.getPriceLKR()));
                }
                if ("Brown Crepe 4X".equalsIgnoreCase(rubberPrice.getItem().trim()) && null != rubberPrice.getPriceLKR() ) {
                    rubberPriceFormatted.setBrownCrepe4x(Integer.parseInt(rubberPrice.getPriceLKR()));
                }
                if ("Flat Bark".equalsIgnoreCase(rubberPrice.getItem().trim()) && null != rubberPrice.getPriceLKR() ) {
                    rubberPriceFormatted.setFlatBark(Integer.parseInt(rubberPrice.getPriceLKR()));
                }
                if ("RSS 1".equalsIgnoreCase(rubberPrice.getItem().trim()) && null != rubberPrice.getPriceLKR() ) {
                    rubberPriceFormatted.setRss1(Integer.parseInt(rubberPrice.getPriceLKR()));
                }
                if ("RSS 2".equalsIgnoreCase(rubberPrice.getItem().trim()) && null != rubberPrice.getPriceLKR()  ) {
                    rubberPriceFormatted.setRss2(Integer.parseInt(rubberPrice.getPriceLKR()));
                }
                if ("RSS 3".equalsIgnoreCase(rubberPrice.getItem().trim()) && null != rubberPrice.getPriceLKR() ) {
                    rubberPriceFormatted.setRss3(Integer.parseInt(rubberPrice.getPriceLKR()));
                }
                if ("RSS 4".equalsIgnoreCase(rubberPrice.getItem().trim()) && null != rubberPrice.getPriceLKR() ) {
                    rubberPriceFormatted.setRss4(Integer.parseInt(rubberPrice.getPriceLKR()));
                }
                if ("RSS 5".equalsIgnoreCase(rubberPrice.getItem().trim()) && null != rubberPrice.getPriceLKR() ) {
                    rubberPriceFormatted.setRss5(Integer.parseInt(rubberPrice.getPriceLKR()));
                }
                if ("Skim Crepe".equalsIgnoreCase(rubberPrice.getItem().trim()) && null != rubberPrice.getPriceLKR() ) {
                    rubberPriceFormatted.setSkimCrepe(Integer.parseInt(rubberPrice.getPriceLKR()));
                }
                if ("Sole Crepe".equalsIgnoreCase(rubberPrice.getItem().trim()) && null != rubberPrice.getPriceLKR() ) {
                    rubberPriceFormatted.setSoleCrepe(Integer.parseInt(rubberPrice.getPriceLKR()));
                }
            }
            returnList.add(rubberPriceFormatted);
        }

        return returnList;
    }
}
