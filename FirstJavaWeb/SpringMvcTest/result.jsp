<%@page import="java.util.*" %>
<html>
<body>
<h1 align="center">Beer Recommendations JSP</h1>

<p>
<%
    List<String> styles = (List) request.getAttribute("styles");
    Iterator it = styles.iterator();
    while(it.hasNext()) {
        out.println("<br/>try: " + it.next());
    }
%>
</p>
</form>
</body>
</html>
