<%
    out.println("Hello, " + request.getAttribute("name") + "<br>");
    out.println("Your info:<br>");
    out.println("Money: " + request.getAttribute("money") + "<br>");
    out.println("Discount: " + request.getAttribute("discount") + "<br>");
%>

<form action="logoutServlet" method="post">
     <input type="submit" value="logout"/>
</form>
