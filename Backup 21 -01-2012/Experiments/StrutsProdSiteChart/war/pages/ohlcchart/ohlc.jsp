<%@ taglib prefix="s" uri="/struts-tags" %>

<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div id="content">
    <div class="box">
        <h2>OHLC Charts</h2>
        <p><p><h1></h1><br></br>

        <fieldset>

            <legend>Stock Data</legend>
            <s:form action="submitDataOHLCChartAction.action">
                <sx:autocompleter cssClass="formitem" label="Stock Code" list="stockCodes" name="stockCode"></sx:autocompleter>
                <s:submit cssClass="formbutton"/>
            </s:form>
        </fieldset>

        <s:if test="showChart == true ">
            <h1>   </h1>
            <h1>   </h1>
            <h1>   </h1>
            <h1>   </h1>
            <h1>   </h1>
            <p> </p>
            <p> </p>
            <p> </p>
            <p> </p>
            <p> </p>

            <fieldset>

                <legend>EOD  <s:property value="stockCode" /> Price Movements </legend>
                <h1>   </h1>
                <h1>   </h1>
                <h1>   </h1>
                <h1>   </h1>
                <h1>   </h1>
                <p> </p>
                <p> </p>
                <p> </p>
                <p> </p>


                <script type="text/javascript" src="pages/amstock/swfobject.js"></script>
                <div id="flashcontent" align="CENTER">
                    <strong>You need to upgrade your Flash Player</strong>
                </div>

                <script type="text/javascript">
                    var so = new SWFObject("pages/amstock/amstock.swf", "amstock", "900", "600", "8", "#FFFFFF");
                    so.addVariable("path", "");
                    so.addVariable("chart_settings", "<settings> <margins>0</margins> <number_format> <letters> <letter number='1000'>K</letter> <letter number='1000000'>M</letter> <letter number='1000000000'>B</letter> </letters> </number_format> <data_sets> <data_set did='0'> <title> <s:property value="stockCode"/> </title> <short> <s:property value="stockCode"/> </short> <color>317dc9</color> <file_name>http://localhost:9090/struts-site/getEODCandleDataEODDataAction.action?type=<s:property value="stockCode"/></file_name> <csv> <reverse>true</reverse> <separator>,</separator> <date_format>YYYY-MM-DD</date_format> <decimal_separator>.</decimal_separator> <columns> <column>date</column> <column>open</column> <column>high</column> <column>low</column> <column>close</column> <column>volume</column> <column>trades</column> </columns> </csv> </data_set> </data_sets> <error_messages> <enabled>0</enabled> </error_messages> <charts> <chart cid='0'> <height>50</height> <title>Value</title> <bg_color>ffffff</bg_color> <border_color>#CCCCCC</border_color> <border_alpha>100</border_alpha> <values> <x> <enabled>false</enabled> </x> </values> <legend> <show_date>true</show_date> </legend> <column_width>70</column_width> <graphs> <graph gid='0'> <type>ohlc</type> <data_sources> <open>open</open> <low>low</low> <high>high</high> <close>close</close> </data_sources> <positive_color>1c6dbf</positive_color> <negative_color>c70003</negative_color> <fill_alpha>100</fill_alpha> <legend> <date key='false' title='false'> <![CDATA[open:<b>{open}</b> low:<b>{low}</b> high:<b>{high}</b> close:<b>{close}</b>]]></date> <period key='false' title='false'> <![CDATA[open:<b>{open}</b> low:<b>{low}</b> high:<b>{high}</b> close:<b>{close}</b>]]></period> <date_comparing key='false' title='false'><![CDATA[]]></date_comparing> <period_comparing key='false' title='false'><![CDATA[]]></period_comparing> </legend> </graph> </graphs> </chart> <chart> <height>30</height> <title>Volume</title> <border_color>#CCCCCC</border_color> <border_alpha>0</border_alpha> <grid> <x> <alpha>10</alpha> <dashed>true</dashed> </x> <y_left> <alpha>10</alpha> <dashed>true</dashed> <approx_count>3</approx_count> </y_left> </grid> <values> <x> <enabled>true</enabled> </x> </values> <legend> <positive_color>7f8da9</positive_color> <negative_color>db4c3c</negative_color> <show_date>false</show_date> </legend> <column_width>70</column_width> <graphs> <graph> <type>column</type> <data_sources> <close>volume</close> </data_sources> <period_value>average</period_value> <cursor_color>002b6d</cursor_color> <fill_alpha>100</fill_alpha> <legend> <date key='false' title='false'>  <![CDATA[ {average} ]]>  </date> <period key='false' title='false'>  <![CDATA[ <b>{average.percents}</b> ]]>  </period> </legend> </graph> </graphs> </chart> <chart> <height>30</height> <title>Trades</title> <border_color>#CCCCCC</border_color> <border_alpha>0</border_alpha> <grid> <x> <alpha>10</alpha> <dashed>true</dashed> </x> <y_left> <alpha>10</alpha> <dashed>true</dashed> <approx_count>3</approx_count> </y_left> </grid> <values> <x> <enabled>true</enabled> </x> </values> <legend> <positive_color>7f8da9</positive_color> <negative_color>db4c3c</negative_color> <show_date>false</show_date> </legend> <column_width>70</column_width> <graphs> <graph> <type>column</type> <data_sources> <close>trades</close> </data_sources> <period_value>average</period_value> <cursor_color>002b6d</cursor_color> <fill_alpha>100</fill_alpha> <legend> <date key='false' title='false'>  <![CDATA[ {average} ]]>  </date> <period key='false' title='false'>  <![CDATA[ <b>{average.percents}</b> ]]>  </period> </legend> </graph> </graphs> </chart> </charts> <data_set_selector> <enabled>false</enabled> </data_set_selector> <period_selector> <button> <bg_color_hover>c70003</bg_color_hover> <bg_color_selected>317dc9</bg_color_selected> <text_color_hover>ffffff</text_color_hover> <text_color_selected>ffffff</text_color_selected> </button> <periods> <period type='DD' count='10'>10D</period> <period type='MM' count='1'>1M</period> <period selected='true' type='MM' count='3'>3M</period> <period type='YYYY' count='1'>1Y</period> <period type='YYYY' count='3'>3Y</period> <period type='YTD' count='0'>YTD</period> <period type='MAX'>MAX</period> </periods> <periods_title>Zoom:</periods_title> <tom_period_title>Custom period:</tom_period_title> </period_selector> <header> <enabled>false</enabled> </header> <background> <color>ffffff</color> <alpha>100</alpha> </background> <plot_area> <margins>15</margins> <bg_color>ffffff,daf0fd</bg_color> <bg_alpha>100</bg_alpha> <border_alpha>70</border_alpha> <border_color>ffffff</border_color> <border_width></border_width> <corner_radius>8</corner_radius> </plot_area> <scroller> <enabled>true</enabled> <height>40</height> <graph_data_source>close</graph_data_source> <bg_color>ffffff</bg_color> <selected_color>daf0fd</selected_color> <resize_button_color>317dc9</resize_button_color> <playback> <enabled>true</enabled> <color>002b6d</color> <color_hover>db4c3c</color_hover> <speed>3</speed> <max_speed>10</max_speed> <speed_indicator> <color>002b6d</color> </speed_indicator> </playback> </scroller> </settings>");
                    so.write("flashcontent");
                </script>



            </fieldset>

        </s:if>

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

    </div>

</div>