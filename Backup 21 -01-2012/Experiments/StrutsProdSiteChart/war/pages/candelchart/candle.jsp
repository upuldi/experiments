<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div id="content">
    <div class="box">
        <h2>Candle Charts</h2>
        <p><p><h1></h1><br></br>

        <fieldset>

            <legend>Stock Data</legend>
            <s:form action="submitDataCandleChartAction.action">
                <sx:autocompleter cssClass="formitem" label="Stock Code" list="stockCodes" name="stockCode"></sx:autocompleter>
                <s:submit cssClass="formbutton"/>
            </s:form>

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

        </fieldset>

        <s:if test="showChart == true ">

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
                <p> </p>

                <script type="text/javascript" src="pages/amstock/swfobject.js"></script>
                <div id="flashcontent" align="CENTER">
                    <strong>You need to upgrade your Flash Player</strong>
                </div>

                <script type="text/javascript">
                    var so = new SWFObject("pages/amstock/amstock.swf", "amstock", "900", "600", "8", "#FFFFFF");
                    so.addVariable("path", "");
                    so.addVariable("chart_settings", "<?xml version='1.0' encoding='UTF-8'?>  <!-- Only the settings with values not equal to defaults are in this file. If you want to see the  full list of available settings, check the amstock_settings.xml file in the amstock folder. --> <settings> <margins>0</margins> <max_series>100</max_series> <number_format> <letters> <letter number='1000'>K</letter> <letter number='1000000'>M</letter> <letter number='1000000000'>B</letter> </letters> </number_format> <data_sets> <data_set did='0'> <title> <s:property value="stockCode"/> </title> <short> <s:property value="stockCode"/> </short> <color>7f8da9</color> <file_name>http://localhost:9090/struts-site/getEODCandleDataEODDataAction.action?type=<s:property value="stockCode"/></file_name> <csv> <reverse>true</reverse> <separator>,</separator> <date_format>YYYY-MM-DD</date_format> <decimal_separator>.</decimal_separator> <columns> <column>date</column> <column>open</column> <column>high</column> <column>low</column> <column>close</column> <column>volume</column> <column>trades</column> </columns> </csv> </data_set> </data_sets> <error_messages> <enabled>0</enabled> </error_messages> <charts> <chart cid='0'> <height>70</height> <border_color>#CCCCCC</border_color> <border_alpha>0</border_alpha> <grid> <x> <alpha>10</alpha> <dashed>true</dashed> </x> <y_left> <alpha>10</alpha> <dashed>true</dashed> </y_left> </grid> <values> <y_left> <bg_alpha>70</bg_alpha> <bg_color>000000</bg_color> <text_color>FFFFFF</text_color> </y_left> </values> <legend> <fade_others_to>10</fade_others_to> <show_date>true</show_date> <positive_color>7f8da9</positive_color> <negative_color>db4c3c</negative_color> <graph_on_off>false</graph_on_off> <show_balloon>true</show_balloon> </legend> <column_width>70</column_width> <graphs> <graph gid='0'> <type>candlestick</type> <data_sources> <open>open</open> <high>high</high> <low>low</low> <close>close</close> </data_sources> <compare_source>close</compare_source> <cursor_color>002b6d</cursor_color> <positive_color>7f8da9</positive_color> <negative_color>db4c3c</negative_color> <fill_alpha>100</fill_alpha> <cursor_alpha>0</cursor_alpha> <legend> <date key='true' title='true'><![CDATA[{close}]]></date> <period key='true' title='true'> <![CDATA[open:<b>{open}</b> low:<b>{low}</b> high:<b>{high}</b> close:<b>{close}</b>]]></period> <date_comparing key='true' title='true'><![CDATA[<b>{close.percents}]]></date_comparing> <period_comparing key='true' title='true'><![CDATA[<b>{close.percents}]]></period_comparing> </legend> </graph> </graphs> </chart> <chart> <height>30</height> <title>Volume</title> <border_color>#CCCCCC</border_color> <border_alpha>0</border_alpha> <grid> <x> <alpha>10</alpha> <dashed>true</dashed> </x> <y_left> <alpha>10</alpha> <dashed>true</dashed> <approx_count>3</approx_count> </y_left> </grid> <values> <x> <enabled>true</enabled> </x> </values> <legend> <positive_color>7f8da9</positive_color> <negative_color>db4c3c</negative_color> <show_date>false</show_date> </legend> <column_width>70</column_width> <graphs> <graph> <type>column</type> <data_sources> <close>volume</close> </data_sources> <period_value>average</period_value> <cursor_color>002b6d</cursor_color> <fill_alpha>100</fill_alpha> <legend> <date key='false' title='false'>  <![CDATA[ {average} ]]>  </date> <period key='false' title='false'>  <![CDATA[ <b>{average.percents}</b> ]]>  </period> </legend> </graph> </graphs> </chart> <chart> <height>30</height> <title>Trades</title> <border_color>#CCCCCC</border_color> <border_alpha>0</border_alpha> <grid> <x> <alpha>10</alpha> <dashed>true</dashed> </x> <y_left> <alpha>10</alpha> <dashed>true</dashed> <approx_count>3</approx_count> </y_left> </grid> <values> <x> <enabled>true</enabled> </x> </values> <legend> <positive_color>7f8da9</positive_color> <negative_color>db4c3c</negative_color> <show_date>false</show_date> </legend> <column_width>70</column_width> <graphs> <graph> <type>column</type> <data_sources> <close>trades</close> </data_sources> <period_value>average</period_value> <cursor_color>002b6d</cursor_color> <fill_alpha>100</fill_alpha> <legend> <date key='false' title='false'>  <![CDATA[ {average} ]]>  </date> <period key='false' title='false'>  <![CDATA[ <b>{average.percents}</b> ]]>  </period> </legend> </graph> </graphs> </chart> </charts> <data_set_selector> <enabled>false</enabled> </data_set_selector> <period_selector> <button> <bg_color_hover>FEC514</bg_color_hover> <bg_color_selected>DB4C3C</bg_color_selected> <text_color_selected>FFFFFF</text_color_selected> </button> <periods> <period type='DD' count='10'>10D</period> <period type='MM' count='1'>1M</period> <period selected='true' type='MM' count='3'>3M</period> <period type='YYYY' count='1'>1Y</period> <period type='YYYY' count='3'>3Y</period> <period type='YTD' count='0'>YTD</period> <period type='MAX'>MAX</period> </periods> <periods_title>Zoom:</periods_title> <tom_period_title>Custom period:</tom_period_title> </period_selector> <header> <enabled>false</enabled> </header> <plot_area> <border_color>b6bece</border_color> </plot_area> <scroller> <graph_data_source>close</graph_data_source> <graph_selected_fill_alpha>100</graph_selected_fill_alpha> <resize_button_color>002b6d</resize_button_color> <playback> <enabled>true</enabled> <color>002b6d</color> <color_hover>db4c3c</color_hover> <speed>3</speed> <max_speed>10</max_speed> <speed_indicator> <color>002b6d</color> </speed_indicator> </playback> </scroller> <cursor> <pan>0</pan> </cursor> <export_as_image> <file>ampie/export.php</file> </export_as_image> </settings>");
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