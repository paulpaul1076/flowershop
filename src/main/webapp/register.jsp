<a href="login.jsp">login</a>
<hr/>

<h3>Register Form</h3>
<br/>

<form action="registerServlet" method="post">
   Name:<input type="text" name="name"/><br/><br/>
   Address:<input type="text" name="address"/><br/><br/>
   Phone:<input type="text" name="phone"/><br/><br/>
   <p id="login_infotext"></p>
   Login:<input type="text" name="login" id="login_textbox"/></p><br/><br/>
   Password:<input type="password" name="password"/><br/><br/>
   <input type="submit" value="register me!" id="register_button"/>
</form>
<head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<!--<script src="js/scripts.js"></script>-->
</head>
<script>
    ifExistsDisableButtonFunction = function() {
                                            $.ajax({
                                                 type: "GET",
                                                 dataType: "json",
                                                 url: "http://localhost:8080/rest/userLogin/doesLoginExist/" + $('#login_textbox').val(),
                                                 success: function(data){
                                                    if(data == true){
                                                        $('#register_button').prop('disabled', true);
                                                        $("#login_infotext").text('This login is taken');
                                                    } else {
                                                        $('#register_button').prop('disabled', false);
                                                        $("#login_infotext").text("This login isn\'t taken");
                                                    }
                                                 }
                                             })};
    $(document).ready(ifExistsDisableButtonFunction);
    $('#login_textbox').keyup(ifExistsDisableButtonFunction);
</script>

