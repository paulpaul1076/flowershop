<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ page import="com.accenture.flowershop.fe.dto.UserDTO"%>
<%@ page import="com.accenture.flowershop.be.entity.Flower"%>
<%@ page import="java.util.List"%>

<%
    UserDTO user = (UserDTO)request.getAttribute("userdto");
    out.println("Hello, " + user.getName() + "<br>");
    out.println("Your info:<br>");
    out.println("Money: " + user.getBalance() + "<br>");
    out.println("Discount: " + user.getDiscount() + "<br>");
    out.println("Address: " + user.getAddress() + "<br>");
    out.println("Phone: " + user.getPhone() + "<br>");
    out.println("<br><br>");
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

<table id="flowerstable">
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
        <td><c:out value="${f.name}"/></td>
        <td><c:out value="${f.price}"/></td>
        <td><c:out value="${f.count}"/></td>
        <td><input id="number" type="number"></td>
        <td align = center><input type="checkbox" name="myTextEditBox" value="checked" /></td>
    </tr>
</c:forEach>
</table>

<form><input id="Move to cart" type=submit value="Move to cart" style="width:100px"></form>

<script>
/*document.getElementById("Move to cart").onclick = function() {
    var table = document.getElementById("flowerstable");
    table.find('tr').each(function (key, val){
        $(this).find('td').each(function (key, val) {
            var productId = val[key].innerHTML; // this isn't working
            var product = ?
            var Quantity = ?
        });
    })*/
}
</script>

<br><br>
<table id="cart">
    <tr>Cart:</tr>
    <tr>
        <td>Name</td>
        <td>Price</td>
        <td>Count</td>
        <td>Add buttons</td>
     </tr>
</table>



<form action="logoutServlet" method="post">
     <input type="submit" value="logout"/>
</form>

<form action=""
