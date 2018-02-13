<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ page import="com.accenture.flowershop.fe.dto.UserDTO"%>
<%@ page import="com.accenture.flowershop.be.entity.Flower"%>
<%@ page import="java.util.List"%>
<%@ page import="com.accenture.flowershop.be.entity.Order"%>
<%@ page import="com.accenture.flowershop.be.business.OrderBusinessService"%>
<%@ page import="com.accenture.flowershop.be.business.FlowerBusinessService"%>
<%@ page import="com.accenture.flowershop.fe.dto.CartFlower"%>

<!--if session expired redirect-->
<!--<meta http-equiv="refresh" content="${pageContext.session.maxInactiveInterval};url=login.jsp">-->

<%
    UserDTO user = (UserDTO)session.getAttribute("userdto");
    out.println("Hello, " + user.getName() + "<br>");
    out.println("Your info:<br>");
    out.println("Money: " + user.getBalance() + "<br>");
    out.println("Discount: " + user.getDiscount() + "<br>");
    out.println("Address: " + user.getAddress() + "<br>");
    out.println("Phone: " + user.getPhone() + "<br>");
    out.println("<br><br>");

    //update flowerlist
    List<Flower> flowerlist = (List<Flower>)session.getAttribute("flowerlist");
    if(session.getAttribute("newflowerlist") == null) {
        FlowerBusinessService flowerBusinessService = (FlowerBusinessService)session.getAttribute("flowerBusinessService");
        flowerlist.clear();
        flowerlist.addAll(flowerBusinessService.getAllFlowers());
    } else {
        List<Flower> newflowerlist = (List<Flower>)session.getAttribute("newflowerlist");
        flowerlist.clear();
        flowerlist.addAll(newflowerlist);
        session.removeAttribute("newflowerlist");
    }

    List<CartFlower> cartlist = (List<CartFlower>)session.getAttribute("cartlist");

    for(Flower f : flowerlist) {
        for(CartFlower cartF : cartlist) {
            if(f.getName().equals(cartF.getName())) {
                f.setCount(f.getCount() - cartF.getHowmany());
            }
        }
    }

    //update orderlist
    List<Order> orderlist = (List<Order>)session.getAttribute("orderlist");
    OrderBusinessService orderBusinessService = (OrderBusinessService)session.getAttribute("orderBusinessService");
    orderlist.clear();
    orderlist.addAll(orderBusinessService.getAllCustomersOrders(user.getLogin()));

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

<form action="searchByNameServlet" method="get">
    Search by name: <input type="text" name="searchByName">
    <input type="submit" name="searchByNameButton" value="Search">
</form>

<form action="searchInRangeServlet" method="get">
    Search in price range(from a to b):<input type="text" name="searchInRange">
    <input type="submit" name="searchInRangeButton" value="Search">
</form>

<form action="showAllFlowersServlet" method="get">
    <input type="submit" name="showAllFlowersButton" value="Show all flowers">
</form>

<h3 style='color:red'>${error}</h3>

<table name="flowerstable">
    <tr>Flowers table:</tr>
    <tr>
        <td>Name</td>
        <td>Price</td>
        <td>Count</td>
        <td>How many?</td>
        <td>Add?</td>
    </tr>
    <c:forEach items = "${sessionScope.flowerlist}" var = "f">
        <tr>
        <form action="addToCartServlet" method="get">
            <input id="name" name="name" type="hidden" value="${f.name}">
            <input id="price" name="price" type="hidden" value="${f.price}">
            <!--get the number from the number element-->

            <td><c:out value="${f.name}"/></td>
            <td><c:out value="${f.price}"/></td>
            <td><c:out value="${f.count}"/></td>
            <td><input id="number" name="number" type="number"></td>
            <td><input type="submit" name="addToCartButton" value="Add to cart"></td>
        </form>
        </tr>
    </c:forEach>
</table>

<br>

<table id="cart">
    <tr>Cart:</tr>
    <tr>
        <td>Name</td>
        <td>Price</td>
        <td>Count</td>
     </tr>
     <c:forEach items="${sessionScope.cartlist}" var="f">
        <tr>
        <td>${f.name}</td>
        <td>${f.total}</td>
        <td>${f.howmany}</td>
        </tr>
     </c:forEach>
      <tr>
         <td>Total with discount applied: </td>
         <td>${sessionScope.total}</td>
     </tr>
</table>

<form action="placeOrderServlet" method="post">
    ${placeOrderButton}
</form>

Placed orders:
<table id="Orders">
    <tr>
        <td>Total</td>
        <td>Create date</td>
        <td>Close date</td>
        <td>Status(Paid?)</td>
    </tr>
     <%
        for(int i = 0; i < orderlist.size(); ++i){
            Order o = orderlist.get(i);
        %>
        <tr>
            <td><%out.println(o.getTotal());%></td>
            <td><%out.println(o.getCreatedate());%></td>
            <td><%out.println(o.getClosedate() == null ? "" : o.getClosedate());%></td>
            <td>
            <%
            if(o.getStatus().equals("created")) {
                out.println(o.getStatus()
                + "<form action=\"payServlet\" method=\"post\">"
                + "<input id=\"orderid\" name=\"orderid\" type=\"hidden\" value=\"" + o.getOrderid() + "\">"
                + "<input id=\"index\" name=\"index\" type=\"hidden\" value=\"" + i + "\">"
                + "<input type=\"submit\" value = \"Pay\">"
                + "</form>");
            } else {
                out.println(o.getStatus());
            }
            %>
            </td>
         </tr>
        <%}
     %>
</table>

<form action="logoutServlet" method="post">
     <input type="submit" value="logout"/>
</form>

