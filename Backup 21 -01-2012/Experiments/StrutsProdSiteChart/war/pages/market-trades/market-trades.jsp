<%@ taglib prefix="s" uri="/struts-tags" %>

<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div id="content">
    <div class="box">
        <h2>Market Trades</h2>
        <p><p>
        <p> Following charts will give you the comprehensive details about total market treads , and how local and foreign participation took place for those trades.  Long term market trades ,foreign and local participation might give you some idea about where the market is heading when it considered with other factors. These information were obtained from the CSE in daily basis. </p>
        <p> </p>
        <p> </p>
        <p> </p>
        <p> </p>
        <p> </p>
        <p>&nbsp; </p>
        <p> &nbsp; </p>
        <h1>   </h1>
        <h1>   </h1>


        <h1>     </h1>
        <h1>     </h1>
        <h1>     </h1>
        <h1>     </h1>
        <h1>     </h1>
        <h1>     </h1>

        <h3>All Market Trades</h3>
        <p></p>
        <p>Market participation comparison. All market trades to domestic participation and foreign participation.</p>


        <script type="text/javascript" src="pages/amstock/swfobject.js"></script>
        <div id="flashcontent" align="CENTER">
            <strong>You need to upgrade your Flash Player</strong>
        </div>

        <script type="text/javascript">
            var so = new SWFObject("pages/amstock/amstock.swf", "amstock", "900", "500", "8", "#FFFFFF");
            so.addVariable("path", "");
            so.addVariable("settings_file", encodeURIComponent("pages/market-trades/amstock_settings_trades.xml"));
            so.write("flashcontent");
        </script>

        <h1>     </h1>
        <h1>     </h1>
        <h1>     </h1>
        <h1>     </h1>
        <h1>     </h1>
        <h1>     </h1>

        <h3>Domestic Trades</h3>
        <p></p><p>Domestic participation of the market.</p>


        <script type="text/javascript" src="pages/amstock/swfobject.js"></script>
        <div id="flashcontent-domestic" align="CENTER">
            <strong>You need to upgrade your Flash Player</strong>
        </div>

        <script type="text/javascript">
            var so = new SWFObject("pages/amstock/amstock.swf", "amstock", "900", "500", "8", "#FFFFFF");
            so.addVariable("path", "");
            so.addVariable("settings_file", encodeURIComponent("pages/market-trades/amstock_settings-single-domtrades.xml"));
            so.write("flashcontent-domestic");
        </script>


        <h1>     </h1>
        <h1>     </h1>
        <h1>     </h1>
        <h1>     </h1>
        <h1>     </h1>
        <h1>     </h1>

        <h3>Foreign Trades</h3>
        <p></p><p>Foreign participation of market.</p>


        <script type="text/javascript" src="pages/amstock/swfobject.js"></script>
        <div id="flashcontent-foreign" align="CENTER">
            <strong>You need to upgrade your Flash Player</strong>
        </div>

        <script type="text/javascript">
            var so = new SWFObject("pages/amstock/amstock.swf", "amstock", "900", "500", "8", "#FFFFFF");
            so.addVariable("path", "");
            so.addVariable("settings_file", encodeURIComponent("pages/market-trades/amstock_settings-single-fortrades.xml"));
            so.write("flashcontent-foreign");
        </script>



        <h1>   </h1>
        <h1>   </h1>
        <h1>   </h1>
        <p> </p>
        <p> </p>
        <p> </p>
        <p> </p>
        <p> </p>

    </div>

</div>