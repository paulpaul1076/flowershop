<%@ include file="index.jsp"%>
<hr/>

<h3>Login Form</h3>
<br/>
<form action="loginServlet" method="get">
    Login:<input type="text" name="login"/><br/><br/>
    Password:<input type="password" name="password"/><br/><br/>
    <input type="submit" value="login"/>
</form>
<form action="register.jsp">
    <input type="submit" value="register"/>
</form>