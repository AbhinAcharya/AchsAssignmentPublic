<%@ page import="com.achs.entity.User" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: abhinacharya
  Date: 10/4/20
  Time: 8:30 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script>
        function myFunction() {

            var conformTheRecord = confirm("Do you want to delete the record?");
            if (conformTheRecord == true) {
                return true;
            } else {
                return false;
            }
        }
    </script>
    <title>View All Users</title>
</head>
<body>
<%
    response.setHeader("Cache-Control","no-cache,no-store,must-revalidate");
    if(session.getAttribute("username")==null){
        session.setAttribute("message","please login first to view all the users.");
        response.sendRedirect("../index.jsp");
    }

    List<User> users = (List<User>) request.getAttribute("users");
    if (users != null && users.size() > 0) {
%>

<h1>Welcome ${username}</h1>
<p>Do you want to
<form action="/project/Logout" method="get">
    <input type="submit" value="Logout">
</form>
</p>

<table align="center">
    <tr>
        <th> ID </th>
        <th> UserName</th>
        <th> Email </th>
        <th>Address</th>
        <th>Contact Number</th>
        <th colspan="2">Edit</th>

    </tr>
    <%for(User user : users){%>
    <tr>

        <td><%=user.getUid() %></td>
        <td><%=user.getUserName()%></td>
        <td><%=user.getEmail()%></td>
        <td><%=user.getAddress()%></td>
        <td><%=user.getContactNumber()%></td>
        <td><a href="EditUserServlet?uid=<%=user.getUid()%>">Edit</a></td>
        <td><a href="DeleteCustomerServlet?uid=<%=user.getUid()%>" onclick="return myFunction()">Delete</a></td>
    </tr>
    <% } %>
</table>
<%} else { %>

<h1>No Users found.</h1>
<% }%>
</body>
</html>
