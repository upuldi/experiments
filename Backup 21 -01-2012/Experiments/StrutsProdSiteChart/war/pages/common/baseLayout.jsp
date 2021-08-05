<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>

        <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
        <title><tiles:insertAttribute name="title" ignore="true"/></title>
        <link rel="stylesheet" href="pages/styles.css" type="text/css"/>

        <script type="text/javascript" src="pages/js/jquery.js"></script>
        <script type="text/javascript" src="pages/js/slider.js"></script>
        <script type="text/javascript" src="pages/js/superfish.js"></script>
        <script type="text/javascript" src="pages/js/custom.js"></script>
        <sx:head />

    </head>


    <body class="<tiles:insertAttribute name="bodyclass" ignore="true"/>">

        <div id="container">

            <!-- Header -->
            <tiles:insertAttribute name="header"/>

            <!-- Navigation -->
            <tiles:insertAttribute name="navi"/>

            <!-- Sub-Header -->
            <tiles:insertAttribute name="sub-header"/>


            <!-- Slide Container -->
            <tiles:insertAttribute name="slidecontainer"/>


            <div id="body">

                <!-- body content -->
                <tiles:insertAttribute name="body-content"/>

                <!-- side bar -->
                <tiles:insertAttribute name="sidebar"/>

                <div class="clear"></div>

            </div>

        </div>

        <!-- footer -->
        <tiles:insertAttribute name="footer"/>
    </body>

</html>


 
