<%--
  Created by IntelliJ IDEA.
  User: abhinacharya
  Date: 10/3/20
  Time: 11:56 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script>

        if(${message!=null}){
            alert('${message}');
            ${message=null};
        }
    </script>
    <title>SignUp Page</title>
</head>
<body>
<H1 align="center">Welcome to Registration Page</H1><br>

<div align="center" style=" margin-left: 35%;background-color:silver; height: 180px; width: 280px; alignment:auto;">
    <h2 align="left" style="margin: 10% 0% 5% 15%; ">Customer Signup</h2>
    <form action="../UserRegisterServlet" method="post">
        <label>UserName: <input type="text" name="username" placeholder="Your UserName"> </label><br>
        <label>UserName: <input type="text" name="email" placeholder="Your Email"> </label><br>
        <label>Password: <input type="password" name="password" placeholder="Your Password"></label><br>
        <label>Address: <input type="text" name="address" placeholder="Your Address"></label><br>
        <label>ContactNo: <input type="text" name="phone" placeholder="Your ContactNumber"></label><br>
        <input type="Submit" value="register">
        <input type="Reset" value="Clear">

    </form>

    <p>Already had an account?<a href="../index.jsp">Click Here</a></p>

</div>

</body>
</html>
