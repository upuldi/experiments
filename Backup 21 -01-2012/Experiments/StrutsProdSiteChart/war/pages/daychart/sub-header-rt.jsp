<%@taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<div id="sub-header">
    <s:if test="showSubheader == true ">
<%--
    <h2><font class="cusfont-orange" >Open</font> <s:property value="eodData.open" /> <font class="cusfont-orange" >High</font> <s:property value="eodData.high" /> <font class="cusfont-orange" >Low</font> <s:property value="eodData.low" /> <font class="cusfont-orange" >Close</font> <s:property value="eodData.close" /> <font class="cusfont-orange" >Vol</font> <s:property value="eodData.vol" /> <font class="cusfont-orange" >Trades</font> <s:property value="eodData.trade" /></h2>
--%>
    <h2><font class="<s:property value="changeClass" />"><s:property value="eodData.close" /></font> <img src="pages/images/<s:property value="imageLink" />"/>  <font class="cusfont-white" >(</font><font class="<s:property value="changeClassPresentage" />"><s:property value="change" /></font><font class="cusfont-big-white" >)</font> <font class="<s:property value="changeClassPresentage" />"><s:property value="changePresentage" />%</font>  <font class="cusfont-orange" >Trades </font> <s:property value="formatedTrades" /> <font class="cusfont-orange" >Volume </font> <s:property value="formatedVolume" />
    <s:else><h2>Daily Stock Charts</h2></s:else>
    </s:if>
</div>