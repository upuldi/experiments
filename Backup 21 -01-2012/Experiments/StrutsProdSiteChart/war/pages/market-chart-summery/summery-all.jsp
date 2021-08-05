<%@ taglib prefix="s" uri="/struts-tags" %>

<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>

<%@page contentType="text/html" pageEncoding="UTF-8" %>
<div id="content">
    <div class="box">
        <h2>Market Summery</h2>

        <p>

        <p>

        <p>Following chart will provides a comparison of various market indices of CSE.</p>

        <p>You can compare how market turnover, total trades, domestic contribution to total trades, foreign
            contribution to total trades, total domestic sales and purchases, total foreign sales and purchases, total
            volume of turnover with domestic and foreign participation of it changed over time.</p>

        <h1></h1>

        <h1></h1>

        <h1></h1>

        <h1></h1>


        <p></p>

        <p></p>

        <p></p>

        <p></p>

        <p></p>

        <p>&nbsp; </p>

        <p> &nbsp; </p>

        <h1></h1>

        <h1></h1>

        <h3>Market Summery</h3>

        <p></p>


        <script type="text/javascript" src="pages/amstock/swfobject.js"></script>
        <div id="flashcontent" align="CENTER">
            <strong>You need to upgrade your Flash Player</strong>
        </div>

        <script type="text/javascript">
            var so = new SWFObject("pages/amstock/amstock.swf", "amstock", "900", "600", "8", "#FFFFFF");
            so.addVariable("path", "");
            so.addVariable("settings_file", encodeURIComponent("pages/market-chart-summery/amstock_settings_all-summery.xml"));
            so.write("flashcontent");
        </script>

        <h1></h1>

        <h1></h1>

        <h1></h1>

        <h1></h1>

        <h1></h1>

        <h1></h1>


    </div>

</div>