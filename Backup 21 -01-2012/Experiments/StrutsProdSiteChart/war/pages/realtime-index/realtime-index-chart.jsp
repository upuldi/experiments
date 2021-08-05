<%@ taglib prefix="s" uri="/struts-tags" %>

<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>



<script type="text/javascript">

    function AjaxMarketStatus() {
        var xmlHttp;
        try {
            xmlHttp = new XMLHttpRequest();
        }
        catch (e) {
            try {
                xmlHttp = new ActiveXObject("Msxml2.XMLHTTP");
            }
            catch (e) {
                try {
                    xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
                }
                catch (e) {
                    alert("No AJAX!?");
                    return false;
                }
            }
        }

        xmlHttp.onreadystatechange = function() {

            if (xmlHttp.readyState == 4) {

                var ajaxResponse = xmlHttp.responseText;
                var responseObjK = JSON.parse(ajaxResponse);

                if (null != responseObjK.negativeContributors) {
                    document.getElementById('no').innerHTML = responseObjK.negativeContributors;
                }
                if (null != responseObjK.positiveContributors) {
                    document.getElementById('po').innerHTML = responseObjK.positiveContributors;
                }
                if (null != responseObjK.turnover) {
                    document.getElementById('to').innerHTML = responseObjK.turnover;
                }
                if (null != responseObjK.volume) {
                    document.getElementById('vo').innerHTML = responseObjK.volume;
                }
                setTimeout('Ajax()', 10000);
            }
        }
        xmlHttp.open("GET", "getRTIndexDataRealtimeMarketStatusData.action", true);
        xmlHttp.send(null);
    }

    window.onload = function() {
        setTimeout('AjaxMarketStatus()', 10000);
    }


</script>


<div id="content">

<div class="box">

<h2>Index Charts</h2>

<p>

<p>

<h1></h1>

<h1></h1>

<h1></h1>

<h1></h1>

<h1></h1>

<p></p>

<p></p>

<p></p>

<p></p>

<p></p>

<h1></h1>

<h1></h1>

<h1></h1>

<h1></h1>

<p></p>

<p></p>

<p></p>

<p></p>

<p></p>

<h1></h1>

<h1></h1>

<h1></h1>

<h1></h1>

<p></p>

<p></p>

<p></p>

<p></p>

<p></p>

<h1></h1>

<h1></h1>

<h1></h1>

<h1></h1>

<p></p>

<p></p>

<p></p>

<p></p>

<p></p>

<h1></h1>

<h1></h1>

<h1></h1>

<h1></h1>

<p></p>

<p></p>

<p></p>

<p></p>

<p></p>

<fieldset>
    <legend>Market Snapshot</legend>
    <h1></h1>

<h1></h1>

<h1></h1>

<p></p>

<p></p>
    <table>
        <tr>
            <th>Turnover</th>
            <th>Volume</th>
            <th>Positive Contributors</th>
            <th>Negative Contributors</th>
        </tr>
        <tr>
            <td id="to" align="center"></td>
            <td id="vo" align="center"></td>
            <td id="po" align="center"></td>
            <td id="no" align="center"></td>
        </tr>
    </table>
</fieldset>


<h1></h1>

<h1></h1>

<h1></h1>

<h1></h1>

<p></p>

<p></p>

<p></p>

<p></p>

<p></p>

<h1></h1>

<h1></h1>

<h1></h1>

<h1></h1>

<p></p>

<p></p>

<p></p>

<p></p>

<p></p>


<fieldset>
    <legend>ASPI Index Movements</legend>
    <h1></h1>

    <h1></h1>

    <h1></h1>

    <h1></h1>

    <p></p>

    <p></p>

    <p></p>

    <p></p>

    <p></p>

    <script type="text/javascript" src="pages/amstock/swfobject.js"></script>

    <div id="flashcontent-aspi" align="center">
        <strong>You need to upgrade your Flash Player</strong>
    </div>

    <script type="text/javascript">
        var so = new SWFObject("pages/amstock/amstock.swf", "amstock", "900", "600", "8", "#FFFFFF");
        so.addVariable("path", "");
        so.addVariable("chart_settings", "<settings> <margins>0</margins> <text_size>10</text_size> <data_reloading> <interval>60</interval> <show_preloader>1</show_preloader> </data_reloading> <header> <enabled>false</enabled> </header> <number_format> <letters> <letter number='1000'>K</letter> <letter number='1000000'>M</letter> <letter number='1000000000'>B</letter> </letters> </number_format> <data_sets> <data_set did='0'> <title>ASPI Data</title> <short>ASPI</short> <description></description> <file_name>http://localhost:9090/struts-site/getRealTimeIndexDataRealTImeIndexDataCSVAction.action?param=<s:property value="aspiparam"/></file_name> <trend_lines_file_name>pages/realtime/trend_lines.xml</trend_lines_file_name> <main_drop_down selected='true'></main_drop_down> <csv> <reverse>true</reverse> <separator>,</separator> <date_format>YYYY-MM-DD hh:mm:ss</date_format> <decimal_separator>.</decimal_separator> <columns> <column>date</column> <column>value</column> </columns> </csv> </data_set> </data_sets> <error_messages> <enabled>0</enabled> </error_messages> <charts> <chart cid='0'> <bg_color>f5f5f5,ffffff</bg_color> <title>Value</title> <height>60</height> <border_color>#CCCCCC</border_color> <border_alpha>100</border_alpha> <grid> <x> <dashed>true</dashed> </x> <y_right> <color>cccccc</color> <alpha>100</alpha> <dashed>true</dashed> </y_right> </grid> <legend> <graph_on_off>false</graph_on_off> <fade_others_to>10</fade_others_to> <show_date>true</show_date> </legend> <comparing> <recalculate_from_start>0</recalculate_from_start> </comparing> <events> <use_hand_cursor>1</use_hand_cursor> </events> <trend_lines> <drawing_enabled>1</drawing_enabled> <line_width>2</line_width> <dash_length>5</dash_length> </trend_lines> <graphs> <graph gid='0'> <axis>right</axis> <type>line</type> <smoothed>1</smoothed> <data_sources> <close>value</close> </data_sources> <compare_source>value</compare_source> <legend> <date key='true' title='true'><![CDATA[<b>{close}</b>]]></date> <period key='true' title='true'> <![CDATA[Index:<b>{Index}</b>]]></period> <date_comparing key='true' title='true'><![CDATA[{close.percents}]]></date_comparing> <period_comparing key='true' title='true'><![CDATA[{close.percents}]]></period_comparing> </legend> </graph> </graphs> </chart> </charts> <date_formats> <legend> <days>hh:mm:ss month DD, YYYY</days> </legend> </date_formats> <data_set_selector> <width>130</width> <max_comparing_count>3</max_comparing_count> <main_drop_down_title>Select:</main_drop_down_title> <compare_list_box_title>Compare to:</compare_list_box_title> <balloon_text>{title}: {description}</balloon_text> </data_set_selector> <period_selector> <button> <bg_color_hover>b81d1b</bg_color_hover> <bg_color_selected>b81d1b</bg_color_selected> <text_color_hover>ffffff</text_color_hover> <text_color_selected>ffffff</text_color_selected> </button> <date_format>hh:mm</date_format> <periods_title>Zoom:</periods_title> <tom_period_title>Custom period:</tom_period_title> <periods> <period pid='0' type='mm' count='5'>5M</period> <period pid='1' type='mm' count='10' selected='1'>10M</period> <period pid='2' type='mm' count='20' selected='1'>20M</period> <period pid='3' type='MAX' count='30'>30M</period> <period pid='4' type='hh' count='1'>1H</period> <period pid='5' type='hh' count='2'>2H</period> <period pid='6' type='hh' count='3' selected='1'>3H</period> <period pid='7' type='hh' count='4'>4H</period> <period pid='8' type='hh' count='6' selected='1'>6H</period> <period pid='9' type='MAX' count='0'>MAX</period> </periods> <periods_title>Zoom:</periods_title> <tom_period_title>Custom period:</tom_period_title> </period_selector> <date_formats> <!-- [24] (12 / 24) The time in the legend and x axis might be displayed using 12 or 24 hour format --> <hour_format></hour_format> <legend> <minutes>mm:ss</minutes> </legend> <x_axis> <minutes>mm:ss</minutes> </x_axis> </date_formats> <header> <enabled></enabled> <text><![CDATA[<b>{title}</b> ({short}) {description}]]></text> <text_size>12</text_size> </header> <plot_area> <border_color>cccccc</border_color> </plot_area> <scroller> <enabled>true</enabled> <height>50</height> <graph_data_source>value</graph_data_source> <bg_color>f5f5f5,ffffff</bg_color> <resize_button_style>dragger</resize_button_style> <playback> <enabled>true</enabled> <color>002b6d</color> <color_hover>db4c3c</color_hover> <speed>3</speed> <max_speed>10</max_speed> <speed_indicator> <color>002b6d</color> </speed_indicator> </playback> </scroller> </settings>");
        so.write("flashcontent-aspi");
    </script>

</fieldset>

<h1></h1>

<h1></h1>

<h1></h1>

<h1></h1>

<h1></h1>

<h1></h1>

<h1></h1>

<h1></h1>

<p></p>

<p></p>

<p></p>

<p></p>

<p></p>
<fieldset>
    <legend>MPI Index Movements</legend>

    <h1></h1>

    <h1></h1>

    <h1></h1>

    <h1></h1>

    <p></p>

    <p></p>

    <p></p>

    <p></p>

    <p></p>

    <script type="text/javascript" src="pages/amstock/swfobject.js"></script>

    <div id="flashcontent-mpi" align="center">
        <strong>You need to upgrade your Flash Player</strong>
    </div>

    <script type="text/javascript">
        var so = new SWFObject("pages/amstock/amstock.swf", "amstock", "900", "600", "8", "#FFFFFF");
        so.addVariable("path", "");
        so.addVariable("chart_settings", "<settings> <margins>0</margins> <text_size>10</text_size> <data_reloading> <interval>60</interval> <show_preloader>1</show_preloader> </data_reloading> <header> <enabled>false</enabled> </header> <number_format> <letters> <letter number='1000'>K</letter> <letter number='1000000'>M</letter> <letter number='1000000000'>B</letter> </letters> </number_format> <data_sets> <data_set did='0'> <title>ASPI Data</title> <short>ASPI</short> <description></description> <file_name>http://localhost:9090/struts-site/getRealTimeIndexDataRealTImeIndexDataCSVAction.action?param=<s:property value="mpiparam"/></file_name> <trend_lines_file_name>pages/realtime/trend_lines.xml</trend_lines_file_name> <main_drop_down selected='true'></main_drop_down> <csv> <reverse>true</reverse> <separator>,</separator> <date_format>YYYY-MM-DD hh:mm:ss</date_format> <decimal_separator>.</decimal_separator> <columns> <column>date</column> <column>value</column> </columns> </csv> </data_set> </data_sets> <error_messages> <enabled>0</enabled> </error_messages> <charts> <chart cid='0'> <bg_color>f5f5f5,ffffff</bg_color> <title>Value</title> <height>60</height> <border_color>#CCCCCC</border_color> <border_alpha>100</border_alpha> <grid> <x> <dashed>true</dashed> </x> <y_right> <color>cccccc</color> <alpha>100</alpha> <dashed>true</dashed> </y_right> </grid> <legend> <graph_on_off>false</graph_on_off> <fade_others_to>10</fade_others_to> <show_date>true</show_date> </legend> <comparing> <recalculate_from_start>0</recalculate_from_start> </comparing> <events> <use_hand_cursor>1</use_hand_cursor> </events> <trend_lines> <drawing_enabled>1</drawing_enabled> <line_width>2</line_width> <dash_length>5</dash_length> </trend_lines> <graphs> <graph gid='0'> <axis>right</axis> <type>line</type> <smoothed>1</smoothed> <data_sources> <close>value</close> </data_sources> <compare_source>value</compare_source> <legend> <date key='true' title='true'><![CDATA[<b>{close}</b>]]></date> <period key='true' title='true'> <![CDATA[Index:<b>{Index}</b>]]></period> <date_comparing key='true' title='true'><![CDATA[{close.percents}]]></date_comparing> <period_comparing key='true' title='true'><![CDATA[{close.percents}]]></period_comparing> </legend> </graph> </graphs> </chart> </charts> <date_formats> <legend> <days>hh:mm:ss month DD, YYYY</days> </legend> </date_formats> <data_set_selector> <width>130</width> <max_comparing_count>3</max_comparing_count> <main_drop_down_title>Select:</main_drop_down_title> <compare_list_box_title>Compare to:</compare_list_box_title> <balloon_text>{title}: {description}</balloon_text> </data_set_selector> <period_selector> <button> <bg_color_hover>b81d1b</bg_color_hover> <bg_color_selected>b81d1b</bg_color_selected> <text_color_hover>ffffff</text_color_hover> <text_color_selected>ffffff</text_color_selected> </button> <date_format>hh:mm</date_format> <periods_title>Zoom:</periods_title> <tom_period_title>Custom period:</tom_period_title> <periods> <period pid='0' type='mm' count='5'>5M</period> <period pid='1' type='mm' count='10' selected='1'>10M</period> <period pid='2' type='mm' count='20' selected='1'>20M</period> <period pid='3' type='MAX' count='30'>30M</period> <period pid='4' type='hh' count='1'>1H</period> <period pid='5' type='hh' count='2'>2H</period> <period pid='6' type='hh' count='3' selected='1'>3H</period> <period pid='7' type='hh' count='4'>4H</period> <period pid='8' type='hh' count='6' selected='1'>6H</period> <period pid='9' type='MAX' count='0'>MAX</period> </periods> <periods_title>Zoom:</periods_title> <tom_period_title>Custom period:</tom_period_title> </period_selector> <date_formats> <!-- [24] (12 / 24) The time in the legend and x axis might be displayed using 12 or 24 hour format --> <hour_format></hour_format> <legend> <minutes>mm:ss</minutes> </legend> <x_axis> <minutes>mm:ss</minutes> </x_axis> </date_formats> <header> <enabled></enabled> <text><![CDATA[<b>{title}</b> ({short}) {description}]]></text> <text_size>12</text_size> </header> <plot_area> <border_color>cccccc</border_color> </plot_area> <scroller> <enabled>true</enabled> <height>50</height> <graph_data_source>value</graph_data_source> <bg_color>f5f5f5,ffffff</bg_color> <resize_button_style>dragger</resize_button_style> <playback> <enabled>true</enabled> <color>002b6d</color> <color_hover>db4c3c</color_hover> <speed>3</speed> <max_speed>10</max_speed> <speed_indicator> <color>002b6d</color> </speed_indicator> </playback> </scroller> </settings>");
        so.write("flashcontent-mpi");
    </script>

</fieldset>

<h1></h1>

<h1></h1>

<h1></h1>

<h1></h1>

<h1></h1>

<h1></h1>

<h1></h1>

<h1></h1>

<p></p>

<p></p>

<p></p>

<p></p>

<p></p>

</div>

</div>