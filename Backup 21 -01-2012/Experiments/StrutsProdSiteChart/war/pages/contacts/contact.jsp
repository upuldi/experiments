<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>

    <div id="content">
        <div class="box">
            <h2>Contact us from following email here......</h2>
            <h2>Let us know what you need. Provide us your valuable feedback......</h2>


            <h3>Form</h3>

            <fieldset>

                <legend>Add your comments</legend>

                <s:form action="AddComment">
                    <s:textfield name="userName" label="Name" />
                    <s:textfield name="password" label="Email" />
                    <s:textarea name="message" label="Message" />
                    <s:submit value="Send" />
                </s:form>
            </fieldset>



        </div>
    </div>

    </body>
</html>