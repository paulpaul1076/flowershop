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
<c:forEach items = "${sessionScope.flowerlist}" var = "f">
    <tr>
        <td><c:out value="${f.name}"/></td>
        <td><c:out value="${f.price}"/></td>
        <td><c:out value="${f.count}"/></td>
    </tr>
</c:forEach>

</table>
<form action="logoutServlet" method="post">
     <input type="submit" value="logout"/>
</form>

<form action=""
