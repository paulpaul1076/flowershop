<%@ include file="index.jsp"%>
       <hr/>

       <h3>Register Form</h3>
       <br/>
       <form action="registerServlet" method="post">
           Login:<input type="text" name="login"/><br/><br/>
           Name:<input type="text" name="name"/><br/><br/>
           Password:<input type="password" name="password"/><br/><br/>
           <input type="submit" value="register me!"/>
       </form>