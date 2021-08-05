<%@ taglib prefix="s" uri="/struts-tags" %>

<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>

<%@page contentType="text/html" pageEncoding="UTF-8" %>
<div id="content">
    <div class="box">
        <h2>Price Indexes</h2>

        <p></p>

        <p>A stock market index is a method of measuring a section of the stock market. Many indices are cited by news
            or financial services firms and are used as benchmarks, to measure the performance of portfolios such as
            mutual funds.
            Alternatively, an index may also be considered as an instrument (after all it can be traded) which derives
            its value from other instruments or indices. The index may be weighted to reflect the market capitalization
            of its components, or may be a simple index which merely represents the net change in the prices of the
            underlying instruments.</p>

        <p></p>

        <h3>ASPI Vs MPI Comparison</h3>

        <p></p>

        <p align="left">The CSE has two main price indices (ASPI & MPI), 20 sector price indices and total return
            indices which are based on ASPI, MPI and sector indices. Index values are calculated on an on-going basis
            during the trading session, with the closing values published at the end of each session.

            The two main price indices are the All Share Price Index (ASPI) and the Milanka Price Index (MPI). These
            indices are market capitalization weighted indices where the weight of any company is taken as the number of
            ordinary shares listed in the market. This weighting system allows the price movements of larger companies
            to have a greater impact on the index. Such a weighting system was adopted on the assumption that the
            general economic situation has a greater influence on larger companies than on smaller ones.
        </p>


        <script type="text/javascript" src="pages/amstock/swfobject.js"></script>
        <div id="flashcontent" align="CENTER">
            <strong>You need to upgrade your Flash Player</strong>
        </div>

        <script type="text/javascript">
            var so = new SWFObject("pages/amstock/amstock.swf", "amstock", "900", "500", "8", "#FFFFFF");
            so.addVariable("path", "");
            so.addVariable("settings_file", encodeURIComponent("pages/market/amstock_settings_index.xml"));
            so.write("flashcontent");
        </script>

        <h1></h1>

        <h1></h1>

        <h1></h1>

        <h1></h1>

        <h1></h1>

        <h1></h1>

        <h3> ASPI Movements </h3>

        <p></p>
        <p>
            The ASPI indicates the price fluctuations of all the listed companies and covers all the traded companies during a market day.
        </p>
        <blockquote><p>ASPI = (Market Capitalisation of All Listed Companies / Base Market Capitalisation ) * 100 </p></blockquote>


        <script type="text/javascript" src="pages/amstock/swfobject.js"></script>
        <div id="flashcontent-aspi" align="CENTER">
            <strong>You need to upgrade your Flash Player</strong>
        </div>

        <script type="text/javascript">
            var so = new SWFObject("pages/amstock/amstock.swf", "amstock", "900", "500", "8", "#FFFFFF");
            so.addVariable("path", "");
            so.addVariable("chart_settings", "<settings> <margins>0</margins> <text_size>10</text_size> <header> <enabled>false</enabled> </header> <number_format> <letters> <letter number='1000'>K</letter> <letter number='1000000'>M</letter> <letter number='1000000000'>B</letter> </letters> </number_format> <data_sets> <data_set did='0'> <title>ASPI Movements</title> <short>ASPI</short> <description></description> <file_name>http://localhost:9090/struts-site/getMarketSummeryDataMarketDataAction.action?type=ALL </file_name> <main_drop_down selected='true'></main_drop_down> <compare_list_box selected='false'></compare_list_box> <csv> <reverse>true</reverse> <separator>,</separator> <date_format>YYYY-MM-DD</date_format> <decimal_separator>.</decimal_separator> <columns> <column>date</column> <column>close</column> </columns> </csv> </data_set> </data_sets><error_messages> <enabled>0</enabled> </error_messages> <charts> <chart cid='0'> <bg_color>f5f5f5,ffffff</bg_color> <title>Value</title> <height>60</height> <border_color>#CCCCCC</border_color> <border_alpha>100</border_alpha> <grid> <x> <dashed>true</dashed> </x> <y_right> <color>cccccc</color> <alpha>100</alpha> <dashed>true</dashed> </y_right> </grid> <legend> <graph_on_off>false</graph_on_off> <fade_others_to>10</fade_others_to> <show_date>true</show_date> </legend> <graphs> <graph gid='0'> <alpha>76</alpha> <fill_alpha>25</fill_alpha> <cursor_alpha>85</cursor_alpha> <corner_radius>4</corner_radius> <axis>right</axis> <type>line</type> <smoothed>1</smoothed> <data_sources> <close>close</close> </data_sources> <compare_source>close</compare_source> <legend> <date key='true' title='true'><![CDATA[<b>{close}</b>]]></date> <period key='true' title='true'> <![CDATA[open:<b>{open}</b> low:<b>{low}</b> high:<b>{high}</b> close:<b>{close}</b>]]></period> <date_comparing key='true' title='true'><![CDATA[{close.percents}]]></date_comparing> <period_comparing key='true' title='true'><![CDATA[{close.percents}]]></period_comparing> </legend> </graph> </graphs> </chart> </charts> <data_set_selector> <width>130</width> <max_comparing_count>3</max_comparing_count> <main_drop_down_title>Select:</main_drop_down_title> <compare_list_box_title>Compare to:</compare_list_box_title> <balloon_text>{title}: {description}</balloon_text> </data_set_selector> <period_selector> <button> <bg_color_hover>b81d1b</bg_color_hover> <bg_color_selected>b81d1b</bg_color_selected> <text_color_hover>ffffff</text_color_hover> <text_color_selected>ffffff</text_color_selected> </button> <periods> <period type='DD' count='1'>1D</period> <period type='DD' count='5'>5D</period> <period type='DD' count='10'>10D</period> <period type='DD' count='20'>20D</period> <period type='MM' count='1'>1M</period> <period type='MM' count='3'>3M</period> <period type='MM' count='6'>6M</period> <period selected='true' type='YYYY' count='1'>1Y</period> <period type='YYYY' count='3'>3Y</period> <period type='YTD' count='0'>YTD</period> <period type='MAX'>MAX</period> </periods> <periods_title>Zoom:</periods_title> <tom_period_title>Custom period:</tom_period_title> </period_selector> <header> <enabled></enabled> <text><![CDATA[<b>{title}</b> ({short}) {description}]]></text> <text_size>12</text_size> </header> <plot_area> <border_color>cccccc</border_color> </plot_area> <scroller> <enabled>true</enabled> <height>50</height> <graph_data_source>close</graph_data_source> <bg_color>f5f5f5,ffffff</bg_color> <resize_button_style>dragger</resize_button_style> <playback> <enabled>true</enabled> <color>002b6d</color> <color_hover>db4c3c</color_hover> <speed>3</speed> <max_speed>10</max_speed> <speed_indicator> <color>002b6d</color> </speed_indicator> </playback> </scroller> </settings>");
            so.write("flashcontent-aspi");
        </script>


        <h1></h1>

        <h1></h1>

        <h1></h1>

        <h1></h1>

        <h1></h1>

        <h1></h1>

        <h3> MPI Movements </h3>
        <p></p>
        <p>The Colombo Stock Exchange introduced the ï¿½Milanka Price Indexï¿½(MPI) on 4th January 1999. The MPI comprises of 25 companies and they are selected by considering their performances over the past four quarters. The base index was set at 1000 points as at 31st December 1998.

            The CSE reviewed and revised the companies to be included in the MPI annually up to 2004 and quarterly from 2005, considering the increased level of activity and the need for the MPI to represent the changes in the market conditions more regularly. The CSE now reviews & revises the companies to be included in the MPI on a biannual basis commencing from 2007.
        </p>
        <blockquote><p>MPI = ( Market Cap of 25 Milanka Companies / Base Market Cap of those 25 Companies ) * 1000 </p></blockquote>

        <script type="text/javascript" src="pages/amstock/swfobject.js"></script>
        <div id="flashcontent-mpi" align="CENTER">
            <strong>You need to upgrade your Flash Player</strong>
        </div>

        <script type="text/javascript">
            var so = new SWFObject("pages/amstock/amstock.swf", "amstock", "900", "500", "8", "#FFFFFF");
            so.addVariable("path", "");
            so.addVariable("chart_settings", "<?xml version='1.0' encoding='UTF-8'?> <!-- Only the settings with values not equal to defaults are in this file. If you want to see the full list of available settings, check the amstock_settings.xml file in the amstock folder. --> <settings> <margins>0</margins> <text_size>10</text_size> <header> <enabled>false</enabled> </header> <number_format> <letters> <letter number='1000'>K</letter> <letter number='1000000'>M</letter> <letter number='1000000000'>B</letter> </letters> </number_format> <data_sets> <data_set did='0'> <title>MPI Movements</title> <short>MPI</short> <description></description> <file_name>http://localhost:9090/struts-site/getMarketSummeryDataMarketDataAction.action?type=MIL </file_name> <main_drop_down selected='true'></main_drop_down> <compare_list_box selected='false'></compare_list_box> <csv> <reverse>true</reverse> <separator>,</separator> <date_format>YYYY-MM-DD</date_format> <decimal_separator>.</decimal_separator> <columns> <column>date</column> <column>close</column> </columns> </csv> </data_set> </data_sets> <error_messages> <enabled>0</enabled> </error_messages> <charts> <chart cid='0'> <bg_color>f5f5f5,ffffff</bg_color> <title>Value</title> <height>60</height> <border_color>#CCCCCC</border_color> <border_alpha>100</border_alpha> <grid> <x> <dashed>true</dashed> </x> <y_right> <color>cccccc</color> <alpha>100</alpha> <dashed>true</dashed> </y_right> </grid> <legend> <graph_on_off>false</graph_on_off> <fade_others_to>10</fade_others_to> <show_date>true</show_date> </legend> <graphs> <graph gid='0'> <alpha>76</alpha> <fill_alpha>25</fill_alpha> <cursor_alpha>85</cursor_alpha> <corner_radius>4</corner_radius> <axis>right</axis> <type>line</type> <smoothed>1</smoothed> <data_sources> <close>close</close> </data_sources> <compare_source>close</compare_source> <legend> <date key='true' title='true'><![CDATA[<b>{close}</b>]]></date> <period key='true' title='true'> <![CDATA[open:<b>{open}</b> low:<b>{low}</b> high:<b>{high}</b> close:<b>{close}</b>]]></period> <date_comparing key='true' title='true'><![CDATA[{close.percents}]]></date_comparing> <period_comparing key='true' title='true'><![CDATA[{close.percents}]]></period_comparing> </legend> </graph> </graphs> </chart> </charts> <data_set_selector> <width>130</width> <max_comparing_count>3</max_comparing_count> <main_drop_down_title>Select:</main_drop_down_title> <compare_list_box_title>Compare to:</compare_list_box_title> <balloon_text>{title}: {description}</balloon_text> </data_set_selector> <period_selector> <button> <bg_color_hover>b81d1b</bg_color_hover> <bg_color_selected>b81d1b</bg_color_selected> <text_color_hover>ffffff</text_color_hover> <text_color_selected>ffffff</text_color_selected> </button> <periods> <period type='DD' count='1'>1D</period> <period type='DD' count='5'>5D</period> <period type='DD' count='10'>10D</period> <period type='DD' count='20'>20D</period> <period type='MM' count='1'>1M</period> <period type='MM' count='3'>3M</period> <period type='MM' count='6'>6M</period> <period selected='true' type='YYYY' count='1'>1Y</period> <period type='YYYY' count='3'>3Y</period> <period type='YTD' count='0'>YTD</period> <period type='MAX'>MAX</period> </periods> <periods_title>Zoom:</periods_title> <tom_period_title>Custom period:</tom_period_title> </period_selector> <header> <enabled></enabled> <text><![CDATA[<b>{title}</b> ({short}) {description}]]></text> <text_size>12</text_size> </header> <plot_area> <border_color>cccccc</border_color> </plot_area> <scroller> <enabled>true</enabled> <height>50</height> <graph_data_source>close</graph_data_source> <bg_color>f5f5f5,ffffff</bg_color> <resize_button_style>dragger</resize_button_style> <playback> <enabled>true</enabled> <color>002b6d</color> <color_hover>db4c3c</color_hover> <speed>3</speed> <max_speed>10</max_speed> <speed_indicator> <color>002b6d</color> </speed_indicator> </playback> </scroller> </settings>");
            so.write("flashcontent-mpi");
        </script>


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