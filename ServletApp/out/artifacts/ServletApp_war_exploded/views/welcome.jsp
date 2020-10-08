<%--
  Created by IntelliJ IDEA.
  User: abhinacharya
  Date: 10/3/20
  Time: 11:29 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script>
        if(${status!=null}){
            alert('${status}');
            ${status=null};
        }

    </script>
    <title>Welcome Page</title>
</head>
<body>
<%
    response.setHeader("Cache-Control","no-cache,no-store,must-revalidate");
if(session.getAttribute("username")==null){
    response.sendRedirect("../index.jsp");
}

%>
<h1>Welcome ${username}</h1>
<p>Do you want to
<form action="../Logout">
    <input type="submit" value="Logout">
</form>
</p>
<form action="../ViewAllUserServlet" method="post">
    <input type="hidden" name="uid" value="${uid}">
    <label>If you want to see all the customers then.
    <input type="submit" value="Click Here">
    </label>

</form>
</body>
</html>
