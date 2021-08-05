<%@taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<div id="sub-header">
    <s:if test="showSubheader == true ">
    <h2><strong id="orange" class="orange">Open</strong> <s:property value="eodData.open" /> <strong id="orange" class="orange">High</strong> <s:property value="eodData.high" /> <strong id="orange" class="orange">Low</strong> <s:property value="eodData.low" /> <strong id="orange" class="orange">Close</strong> <s:property value="eodData.close" /> <strong id="orange" class="orange">Vol</strong> <s:property value="eodData.vol" /> <strong id="orange" class="orange">Trades</strong> <s:property value="eodData.trade" /></h2>
    <s:else><h2>Daily Stock Charts</h2></s:else>
    </s:if>
</div>
