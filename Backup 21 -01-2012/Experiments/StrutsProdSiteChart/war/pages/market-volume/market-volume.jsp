<%@ taglib prefix="s" uri="/struts-tags" %>

<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div id="content">
    <div class="box">
        <h2>Market Volme</h2>
        <p><p>
        <p>The market volume participated to the turnover is displayed in following charts. The foreign and domestic participation of the volume is also provided below.
        </p>
        <p>Increased market turnover indicates the increase of market liquidity. It might be due to various factors like increase of fund coming into the market , increased trading activity of  high-frequency trades and the emergence of retail investors as an important market segment.   It also may due to the growth of electronic execution and lowered transaction costs and attracted greater participation from many customer types.</p>

        <h1>   </h1>


        <h1>     </h1>
        <h1>     </h1>
        <h1>     </h1>
        <h1>     </h1>
        <h1>     </h1>
        <h1>     </h1>

        <h3>Market Volume Comparision</h3>
        <p></p>
        <p>Domestic and foreign participation comparison of the overall market turnover volume.</p>


        <script type="text/javascript" src="pages/amstock/swfobject.js"></script>
        <div id="flashcontent" align="CENTER">
            <strong>You need to upgrade your Flash Player</strong>
        </div>

        <script type="text/javascript">
            var so = new SWFObject("pages/amstock/amstock.swf", "amstock", "900", "500", "8", "#FFFFFF");
            so.addVariable("path", "");
            so.addVariable("settings_file", encodeURIComponent("pages/market-volume/amstock_settings-single-total-volume.xml"));
            so.write("flashcontent");
        </script>

        <h1>     </h1>
        <h1>     </h1>
        <h1>     </h1>
        <h1>     </h1>
        <h1>     </h1>
        <h1>     </h1>

        <h3>Domestic Volume</h3>
        <p></p>
        <p>Domestic participation to the turnover volume.</p>


        <script type="text/javascript" src="pages/amstock/swfobject.js"></script>
        <div id="flashcontent-domestic" align="CENTER">
            <strong>You need to upgrade your Flash Player</strong>
        </div>

        <script type="text/javascript">
            var so = new SWFObject("pages/amstock/amstock.swf", "amstock", "900", "500", "8", "#FFFFFF");
            so.addVariable("path", "");
            so.addVariable("settings_file", encodeURIComponent("pages/market-volume/amstock_settings-single-volume-domestic.xml"));
            so.write("flashcontent-domestic");
        </script>


        <h1>     </h1>
        <h1>     </h1>
        <h1>     </h1>
        <h1>     </h1>
        <h1>     </h1>
        <h1>     </h1>

        <h3>Foreign Volume</h3>
        <p></p>
        <p>Foreign participation to the turnover volume.</p>


        <script type="text/javascript" src="pages/amstock/swfobject.js"></script>
        <div id="flashcontent-foreign" align="CENTER">
            <strong>You need to upgrade your Flash Player</strong>
        </div>

        <script type="text/javascript">
            var so = new SWFObject("pages/amstock/amstock.swf", "amstock", "900", "500", "8", "#FFFFFF");
            so.addVariable("path", "");
            so.addVariable("settings_file", encodeURIComponent("pages/market-volume/amstock_settings-singlevolume-forign.xml"));
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