<%@ taglib prefix="s" uri="/struts-tags" %>

<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div id="content">
    <div class="box">
        <h2>Market Turnover</h2>
        <p><p>
        <p>Following chart will give you the value of turnover of the market. This is the market participant's total buying and selling.</p>

        <p>Market turnover gives various different indicators about the market. Increase of market turnover may due to increase of fund coming into the market , increased trading activity of  high-frequency trades and the emergence of retail investors as an important market segment. It also may due to the growth of electronic execution and lowered transaction costs, or due to increased market liquidity. </p>
        <h1>   </h1>
        <p> </p>
        <p> </p>
        <p> </p>
        <p> &nbsp; </p>
        <h1>   </h1>
        <h1>   </h1>

        <h3>Equity Value of Turnover</h3>
        <p></p>
        <p>The following chart will give you the equity values of market turnover of CSE in SLR.</p>

        <script type="text/javascript" src="pages/amstock/swfobject.js"></script>
        <div id="flashcontent" align="CENTER">
            <strong>You need to upgrade your Flash Player</strong>
        </div>

        <script type="text/javascript">
            var so = new SWFObject("pages/amstock/amstock.swf", "amstock", "900", "500", "8", "#FFFFFF");
            so.addVariable("path", "");
            so.addVariable("settings_file", encodeURIComponent("pages/market-turnover/amstock_settings-single-turnover.xml"));
            so.write("flashcontent");
        </script>

        <h1>     </h1>
        <h1>     </h1>
        <h1>     </h1>
        <h1>     </h1>
        <h1>     </h1>
        <h1>     </h1>


    </div>

</div>