<%@ taglib prefix="s" uri="/struts-tags" %>

<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div id="content">
    <div class="box">
        <h2>EOD Line Charts Simple</h2>
        <p><p><h1></h1><br></br>

        <fieldset>

            <legend>Stock Data</legend>
            <s:form action="submitDataSimpleEODChartSideBarAction.action">
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
                    so.addVariable("chart_settings", "<settings> <number_format> <letters> <letter number='1000'>K</letter> <letter number='1000000'>M</letter> <letter number='1000000000'>B</letter> </letters> </number_format> <data_sets> <data_set did='0'> <title>East Stock</title> <short>ES</short> <color>000000</color> <file_name>http://localhost:9090/struts-site/getEODChartDataEODDataAction.action?type=<s:property value="stockCode"/> </file_name> <trend_lines_file_name>pages/eodcharts/trend_lines_simple.xml</trend_lines_file_name> <main_drop_down selected='1'/> <csv> <reverse>1</reverse> <separator>,</separator> <columns> <column>date</column> <column>volume</column> <column>close</column> </columns> </csv> </data_set> </data_sets> <error_messages> <enabled>0</enabled> </error_messages> <charts> <chart cid='0'> <title>Value</title> <height>60</height> <grid/> <values> <x> <enabled>0</enabled> </x> </values> <legend> <show_date>1</show_date> </legend> <comparing> <recalculate_from_start>0</recalculate_from_start> </comparing> <events> <use_hand_cursor>1</use_hand_cursor> </events> <trend_lines> <drawing_enabled>1</drawing_enabled> <line_width>2</line_width> <dash_length>5</dash_length> </trend_lines> <graphs> <graph gid='0'> <data_sources> <close>close</close> </data_sources> <compare_source>close</compare_source> <legend> <date title='0' key='0'>{close}</date> <period title='0' key='0'> <![CDATA[open:<b>{open}</b> low:<b>{low}</b> high:<b>{high}</b> close:<b>{close}</b>]]></period> <date_comparing title='0' key='0'/> <period_comparing title='0' key='0'/> </legend> </graph> </graphs> </chart> <chart> <height>30</height> <title>Volume</title> <border_color>#CCCCCC</border_color> <border_alpha>0</border_alpha> <grid> <x> <alpha>10</alpha> <dashed>true</dashed> </x> <y_left> <alpha>10</alpha> <dashed>true</dashed> <approx_count>3</approx_count> </y_left> </grid> <values> <x> <enabled>true</enabled> </x> </values> <legend> <positive_color>7f8da9</positive_color> <negative_color>db4c3c</negative_color> <show_date>false</show_date> </legend> <column_width>70</column_width> <graphs> <graph> <type>column</type> <data_sources> <close>volume</close> </data_sources> <period_value>average</period_value> <cursor_color>002b6d</cursor_color> <fill_alpha>100</fill_alpha> <legend> <date key='false' title='false'> <![CDATA[ {average} ]]> </date> <period key='false' title='false'> <![CDATA[ <b>{average.percents}</b> ]]> </period> </legend> </graph> </graphs> </chart> </charts> <date_formats> <legend> <days>month DD, YYYY</days> </legend> </date_formats> <data_set_selector> <enabled>0</enabled> <compare_list_box_title>Compare to:</compare_list_box_title> <drop_down> <scroller_color>C7C7C7</scroller_color> </drop_down> </data_set_selector> <period_selector> <periods_title>Zoom:</periods_title> <tom_period_title>Custom period:</tom_period_title> <periods> <period pid='0' type='DD' count='10'>10D</period> <period pid='1' type='MM' count='1'>1M</period> <period pid='2' type='MM' count='3'>3M</period> <period pid='3' type='MM' count='6' selected='1'>6M</period> <period pid='4' type='YYYY' count='1'>1Y</period> <period pid='5' type='YYYY' count='3'>3Y</period> <period pid='6' type='YTD' count='0'>YTD</period> <period pid='7' type='MAX' count='0'>MAX</period> </periods> </period_selector> <header> <enabled>0</enabled> <text><![CDATA[<b>{title}</b> ({short}) {description}]]></text> <text_size>13</text_size> </header> <balloon> <border_color>B81D1B</border_color> </balloon> <scroller> <graph_data_source>close</graph_data_source> <playback> <enabled>true</enabled> <color>002b6d</color> <color_hover>db4c3c</color_hover> <speed>3</speed> <max_speed>10</max_speed> <speed_indicator> <color>002b6d</color> </speed_indicator> </playback> </scroller> <context_menu> <menu function_name='printChart' title='Print chart'/> </context_menu> </settings>");
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