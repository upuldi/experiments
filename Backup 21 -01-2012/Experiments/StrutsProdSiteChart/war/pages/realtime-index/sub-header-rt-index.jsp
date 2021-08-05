<%@taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<html>
<body>

<div id="sub-header">

    <h2><font class="cusfont-orange"><font id="aspiString"></font></font> <font id="aspiValue"></font> <img id="picASPI"
                                                                                                            src=""/>
        <font class=""><font id="aspiChange"></font>
            <font class="<s:property value="fontClassASPIChangePresentage" />"><font
                    id="aspiChangePresentage"></font><font id="presentageMark1"></font>
            </font><font class="cusfont-orange"> <font id="mpiString"></font></font> <font id="mpiValue"></font>
            <img id="picMPI" src=""/>
            <font class="">  <font id="mpiChange">  </font></font><font
                    class=""><font
                    id="mpiChangePresentage"></font><font id="presentageMark2"></font></h2>

    <h2>   &nbsp;</h2>
</div>


<script type="text/javascript">

    function Ajax() {
        var xmlHttp;
        try {
            xmlHttp = new XMLHttpRequest();// Firefox, Opera 8.0+, Safari
        }
        catch (e) {
            try {
                xmlHttp = new ActiveXObject("Msxml2.XMLHTTP"); // Internet Explorer
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

                if (null != responseObjK.ASPI) {
                    document.getElementById('aspiString').innerHTML = "ASPI";
                    document.getElementById('aspiValue').innerHTML = responseObjK.ASPI;
                }
                if (null != responseObjK.aspiChange) {
                    document.getElementById('presentageMark1').innerHTML = "%";
                    document.getElementById('aspiChange').innerHTML = responseObjK.aspiChange;
                    document.getElementById('aspiChange').setAttribute("class", responseObjK.aspiChangeClass);
                    document.getElementById('presentageMark1').setAttribute("class", responseObjK.aspiChangeClass);
                    document.getElementById('picASPI').src = "pages/images/" + responseObjK.aspiImage;
                }
                if (null != responseObjK.aspiChangePresentage) {
                    document.getElementById('aspiChangePresentage').innerHTML = responseObjK.aspiChangePresentage;
                    document.getElementById('aspiChangePresentage').setAttribute("class", responseObjK.aspiChangeClass);
                }
                if (null != responseObjK.MPI) {
                    document.getElementById('mpiString').innerHTML = "MPI";
                    document.getElementById('mpiValue').innerHTML = responseObjK.MPI;
                }
                if (null != responseObjK.mpiChange) {
                    document.getElementById('mpiChange').innerHTML = responseObjK.mpiChange;
                    document.getElementById('mpiChange').setAttribute("class", responseObjK.mpiChangeClass);
                    document.getElementById('presentageMark2').setAttribute("class", responseObjK.mpiChangeClass);
                    document.getElementById('picMPI').src = "pages/images/" + responseObjK.mpiImage;
                }
                if (null != responseObjK.mpiChangePresentage) {
                    document.getElementById('presentageMark2').innerHTML = "%";
                    document.getElementById('mpiChangePresentage').innerHTML = responseObjK.mpiChangePresentage;
                    document.getElementById('mpiChangePresentage').setAttribute("class", responseObjK.mpiChangeClass);
                }

                setTimeout('Ajax()', 10000);
            }
        }
        xmlHttp.open("GET", "getRTIndexDataRealtimeIndexAJAXData.action", true);
        xmlHttp.send(null);
    }

    window.onload = function() {
        setTimeout('Ajax()', 10000);
    }


</script>

</body>
</html>