<h3>Login Form</h3>
<br/>
<form action="/loginServlet" method="get">
    <input id="pagename" name="pagename" type="hidden" value="servlet/login">
    Login:<input type="text" name="login"/><br/><br/>
    Password:<input type="password" name="password"/><br/><br/>
    <input type="submit" value="login"/>
</form>
<form action="register.jsp">
    <input type="submit" value="register"/>
</form>