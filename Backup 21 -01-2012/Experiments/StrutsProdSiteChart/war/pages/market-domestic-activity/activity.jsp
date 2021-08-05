<%@ taglib prefix="s" uri="/struts-tags" %>

<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div id="content">
    <div class="box">
        <h2>Domestic Buying and Selling</h2>
        <p><p>
        <p>Following charts will provides the domestic activity of the market. Domestic buying and selling are an indicator of the market participation of local parities.</p>

        <h1>     </h1>
        <h1>     </h1>
        <h1>     </h1>
        <h1>     </h1>

        <h1>     </h1>

        <h1>   </h1>
        <h1>   </h1>
        <h1>   </h1>
        <h1>   </h1>
        <p> </p>
        <p> </p>
        <p> </p>
        <p> </p>
        <p> </p>
        <p> </p>
        <p>&nbsp; </p>
        <p> &nbsp; </p>
        <h1>   </h1>
        <h1>   </h1>

        <h3>Domestic Buying Vs Selling</h3>
        <p></p>
        <p>The following chart will provide you a comparison of domestic buying and selling.</p>


        <script type="text/javascript" src="pages/amstock/swfobject.js"></script>
        <div id="flashcontent-buysell" align="CENTER">
            <strong>You need to upgrade your Flash Player</strong>
        </div>

        <script type="text/javascript">
            var so = new SWFObject("pages/amstock/amstock.swf", "amstock", "900", "500", "8", "#FFFFFF");
            so.addVariable("path", "");
            so.addVariable("settings_file", encodeURIComponent("pages/market-domestic-activity/amstock_settings-single-dom-purchease-vs-sale.xml"));
            so.write("flashcontent-buysell");
        </script>

        <h1>     </h1>
        <h1>     </h1>
        <h1>     </h1>
        <h1>     </h1>
        <h1>     </h1>
        <h1>     </h1>

        <h3>Domestic Buying</h3>
        <p></p>
        <p>Following chart will provide the domestic value purchases of  the CSE during each market day.</p>

        <script type="text/javascript" src="pages/amstock/swfobject.js"></script>
        <div id="flashcontent-buy" align="CENTER">
            <strong>You need to upgrade your Flash Player</strong>
        </div>

        <script type="text/javascript">
            var so = new SWFObject("pages/amstock/amstock.swf", "amstock", "900", "500", "8", "#FFFFFF");
            so.addVariable("path", "");
            so.addVariable("settings_file", encodeURIComponent("pages/market-domestic-activity/amstock_settings-single-dom-purchease.xml"));
            so.write("flashcontent-buy");
        </script>

        <h1>     </h1>
        <h1>     </h1>
        <h1>     </h1>
        <h1>     </h1>
        <h1>     </h1>
        <h1>     </h1>

        <h3>Domestic Selling</h3>
        <p></p>
        <p>Following chart will provide the domestic value sales of the CSE during each market day.</p>


        <script type="text/javascript" src="pages/amstock/swfobject.js"></script>
        <div id="flashcontent-sell" align="CENTER">
            <strong>You need to upgrade your Flash Player</strong>
        </div>

        <script type="text/javascript">
            var so = new SWFObject("pages/amstock/amstock.swf", "amstock", "900", "500", "8", "#FFFFFF");
            so.addVariable("path", "");
            so.addVariable("settings_file", encodeURIComponent("pages/market-domestic-activity/amstock_settings-single-dom-sales.xml"));
            so.write("flashcontent-sell");
        </script>

        <h1>     </h1>
        <h1>     </h1>
        <h1>     </h1>
        <h1>     </h1>
        <h1>     </h1>
        <h1>     </h1>


    </div>

</div>