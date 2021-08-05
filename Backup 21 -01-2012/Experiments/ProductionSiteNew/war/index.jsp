<HTML>

<head><title>DISPLAY DATE & SYSTEM PROPERTY</title></head>

<BODY>

Hello! The time is now <%= new java.util.Date() %>

<br>Java version :  <%= System.getProperty("java.version")%>

<br>Java home :     <%= System.getProperty("java.home")%>

<br>user name :     <%= System.getProperty("user.name")%>

<br>OS name :        <%= System.getProperty("os.name")%>

<br>user dir :           <%= System.getProperty("user.dir")%>

</BODY>

</HTML>