<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Frameset//EN" "http://www.w3.org/TR/html4/frameset.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <title><tiles:insertAttribute name="title" ignore="true" /></title>
  
<script language="javascript" type="text/javascript">
var fname="content";  

  window.onload=function(){
  var d=document.location.search;
  if(d!='')top.frames[fname].document.location.href=d.substring(d.lastIndexOf('?')+1,d.length);
  }
  </script>

<link rel="stylesheet" href="framesset.css" type="text/css">

<style type="text/css">
table, td, th
{
border:1px solid black;
}
td
{
height:50px;
vertical-align:top;
}
</style>

    </head>



    <body>
        <table width="100%" border="1" cellpadding="2" cellspacing="2" align="center">
            <tr>
                <td height="30%" width="100%" colspan="2">
                    <tiles:insertAttribute name="header" />
                </td>
            </tr>
            <tr>
                <td height="60%" width="15%">
                    <tiles:insertAttribute name="menu" />
                </td>
                <td  height="60%" width="70%" >
                    <tiles:insertAttribute name="body" />
                </td>
            </tr>
            <tr>
                <td height="10%" colspan="2">
                    <tiles:insertAttribute name="footer" />
                </td>
            </tr>
        </table>
    </body>
</html>
