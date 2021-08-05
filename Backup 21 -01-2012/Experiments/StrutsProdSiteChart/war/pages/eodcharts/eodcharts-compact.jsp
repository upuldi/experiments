<%@ taglib prefix="s" uri="/struts-tags" %>

<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div id="content">
    <div class="box">
        <h2>EOD Line Charts Compact</h2>
        <p><p><h1></h1><br></br>

        <fieldset>

            <legend>Stock Data</legend>
            <s:form action="submitDataCompactEODChartSideBarAction.action">
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
                    so.addVariable("chart_settings", "<?xml version='1.0' encoding='UTF-8'?> <!-- Only the settings with values not equal to defaults are in this file. If you want to see the full list of available settings, check the amstock_settings.xml file in the amstock folder. --> <settings> <margins>0</margins> <text_size>10</text_size> <header> <enabled>false</enabled> </header> <number_format> <letters> <letter number='1000'>K</letter> <letter number='1000000'>M</letter> <letter number='1000000000'>B</letter> </letters> </number_format> <data_sets> <data_set did='0'> <title> <s:property value="stockCode"/> </title> <short> <s:property value="stockCode"/> </short> <description></description> <file_name>http://localhost:9090/struts-site/getEODChartDataEODDataAction.action?type=<s:property value="stockCode"/> </file_name> <main_drop_down selected='true'></main_drop_down> <compare_list_box selected='false'></compare_list_box> <csv> <reverse>true</reverse> <separator>,</separator> <date_format>YYYY-MM-DD</date_format> <decimal_separator>.</decimal_separator> <columns> <column>date</column> <column>volume</column> <column>close</column> <column>trades</column> </columns> </csv> </data_set> </data_sets> <error_messages> <enabled>0</enabled> </error_messages> <charts> <chart cid='0'> <bg_color>f5f5f5,ffffff</bg_color> <title>Value</title> <height>60</height> <border_color>#CCCCCC</border_color> <border_alpha>100</border_alpha> <grid> <x> <dashed>true</dashed> </x> <y_right> <color>cccccc</color> <alpha>100</alpha> <dashed>true</dashed> </y_right> </grid> <legend> <graph_on_off>false</graph_on_off> <fade_others_to>10</fade_others_to> <show_date>true</show_date> </legend> <graphs> <graph gid='0'> <alpha>76</alpha> <fill_alpha>25</fill_alpha> <cursor_alpha>85</cursor_alpha> <corner_radius>4</corner_radius> <axis>right</axis> <type>line</type> <smoothed>1</smoothed> <data_sources> <close>close</close> </data_sources> <compare_source>close</compare_source> <legend> <date key='true' title='true'><![CDATA[<b>{close}</b>]]></date> <period key='true' title='true'> <![CDATA[open:<b>{open}</b> low:<b>{low}</b> high:<b>{high}</b> close:<b>{close}</b>]]></period> <date_comparing key='true' title='true'><![CDATA[{close.percents}]]></date_comparing> <period_comparing key='true' title='true'><![CDATA[{close.percents}]]></period_comparing> </legend> </graph> </graphs> </chart> <chart> <title>Volume</title> <height>30</height> <border_color>#CCCCCC</border_color> <border_alpha>0</border_alpha> <grid> <x> <alpha>10</alpha> <dashed>true</dashed> </x> <y_left> <alpha>10</alpha> <dashed>true</dashed> <approx_count>3</approx_count> </y_left> </grid> <values> <x> <enabled>true</enabled> </x> </values> <legend> <positive_color>7f8da9</positive_color> <negative_color>db4c3c</negative_color> <show_date>false</show_date> </legend> <column_width>70</column_width> <graphs> <graph> <type>column</type> <data_sources> <close>volume</close> </data_sources> <period_value>average</period_value> <cursor_color>002b6d</cursor_color> <fill_alpha>100</fill_alpha> <legend> <date key='false' title='false'> <![CDATA[ {average} ]]> </date> <period key='false' title='false'> <![CDATA[ <b>{average.percents}</b> ]]> </period> </legend> </graph> </graphs> </chart> <chart> <title>Trades</title> <height>30</height> <border_color>#CCCCCC</border_color> <border_alpha>0</border_alpha> <grid> <x> <alpha>10</alpha> <dashed>true</dashed> </x> <y_left> <alpha>10</alpha> <dashed>true</dashed> <approx_count>3</approx_count> </y_left> </grid> <values> <x> <enabled>true</enabled> </x> </values> <legend> <positive_color>7f8da9</positive_color> <negative_color>db4c3c</negative_color> <show_date>false</show_date> </legend> <column_width>70</column_width> <graphs> <graph> <type>column</type> <data_sources> <close>trades</close> </data_sources> <period_value>average</period_value> <cursor_color>002b6d</cursor_color> <fill_alpha>100</fill_alpha> <legend> <date key='false' title='false'> <![CDATA[ {average} ]]> </date> <period key='false' title='false'> <![CDATA[ <b>{average.percents}</b> ]]> </period> </legend> </graph> </graphs> </chart> </charts> <data_set_selector> <width>130</width> <max_comparing_count>3</max_comparing_count> <main_drop_down_title>Select:</main_drop_down_title> <compare_list_box_title>Compare to:</compare_list_box_title> <balloon_text>{title}: {description}</balloon_text> </data_set_selector> <period_selector> <button> <bg_color_hover>b81d1b</bg_color_hover> <bg_color_selected>b81d1b</bg_color_selected> <text_color_hover>ffffff</text_color_hover> <text_color_selected>ffffff</text_color_selected> </button> <periods> <period type='DD' count='1'>1D</period> <period type='DD' count='5'>5D</period> <period type='DD' count='10'>10D</period> <period type='DD' count='20'>20D</period> <period type='MM' count='1'>1M</period> <period type='MM' count='3'>3M</period> <period type='MM' count='6'>6M</period> <period selected='true' type='YYYY' count='1'>1Y</period> <period type='YYYY' count='3'>3Y</period> <period type='YTD' count='0'>YTD</period> <period type='MAX'>MAX</period> </periods> <periods_title>Zoom:</periods_title> <tom_period_title>Custom period:</tom_period_title> </period_selector> <header> <enabled></enabled> <text><![CDATA[<b>{title}</b> ({short}) {description}]]></text> <text_size>12</text_size> </header> <plot_area> <border_color>cccccc</border_color> </plot_area> <scroller> <enabled>true</enabled> <height>50</height> <graph_data_source>close</graph_data_source> <bg_color>f5f5f5,ffffff</bg_color> <resize_button_style>dragger</resize_button_style> <playback> <enabled>true</enabled> <color>002b6d</color> <color_hover>db4c3c</color_hover> <speed>3</speed> <max_speed>10</max_speed> <speed_indicator> <color>002b6d</color> </speed_indicator> </playback> </scroller> </settings>");
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