<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.accenture.flowershop.be.entity.Order"%>
<%@ page import="java.util.List"%>
<%@ page import="com.accenture.flowershop.be.business.OrderBusinessService"%>

<!--redirect if session expires-->
<!--<meta http-equiv="refresh" content="${pageContext.session.maxInactiveInterval};url=login.jsp">-->

<%
    OrderBusinessService orderBusinessService = (OrderBusinessService)session.getAttribute("orderBusinessService");
    List<Order> orders = orderBusinessService.getAllOrdersSortedByDateAndStatus();
    List<Order> allOrders = (List<Order>)session.getAttribute("allOrders");
    allOrders.clear();
    allOrders.addAll(orders);
%>

<style>
table,th,td{
	border: 1px solid black;
    border-collapse: collapse;
}
th, td {
	padding: 5px;
}
</style>

Orders:
<table name="ordertable">
<tr>
    <td>order id</td>
    <td>customer's login</td>
    <td>total</td>
    <td>creation date</td>
    <td>closure date</td>
    <td>status</td>
</tr>
    <%
        allOrders = (List<Order>)session.getAttribute("allOrders");
        for(int i = 0; i < allOrders.size(); ++i) {
            Order curOrder = allOrders.get(i);
            %>
            <tr>
                <td><%out.println(curOrder.getOrderid()); %></td>
                <td><%out.println(curOrder.getCustlogin());%></td>
                <td><%out.println(curOrder.getTotal());%></td>
                <td><%out.println(curOrder.getCreatedate());%></td>
                <td>
                <%
                    out.println(curOrder.getClosedate() == null ? "" : curOrder.getClosedate());
                %>
                </td>
                <td>
                <%
                    if(curOrder.getStatus().equals("paid")) {
                        out.println(curOrder.getStatus()
                        + "<form action=\"closeServlet\" method=\"post\">"
                        + "<input id=\"orderid\" name=\"orderid\" type=\"hidden\" value=\"" + curOrder.getOrderid() + "\">"
                        + "<input id=\"index\" name=\"index\" type=\"hidden\" value=\"" + i + "\">"
                        + "<input type=\"submit\" value = \"Close\">"
                        + "</form>");
                    } else {
                        out.println(curOrder.getStatus());
                    }
                %>
                </td>
            </tr>
            <%
        }
    %>
</table>

<form action="logoutServlet" method="post">
     <input type="submit" value="logout"/>
</form>
