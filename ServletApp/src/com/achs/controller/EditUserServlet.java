package com.achs.controller;

import com.achs.dao.UserDao;
import com.achs.dao.UserDaoImpl;
import com.achs.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.function.DoubleToIntFunction;

/**
 * Created by IntelliJ IDEA.
 * User: abhinacharya
 * Date: 10/4/20
 * Time: 3:35 PM
 */
@WebServlet("/EditUserServlet")
public class EditUserServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter printWriter = response.getWriter();
        printWriter.println("<h2> Update Customers</h2>");
          int uId = Integer.parseInt(request.getParameter("uid"));//String ma aako lai int ma parse gareko
        UserDao userDao = new UserDaoImpl();
        //  User user = userDao.getUserById(uId);
User user = new User();
        try {
            user = userDao.getUserById(uId);

        printWriter.print("<form action='EditUserSaveServlet' method='post'");
        printWriter.print("<table>");
        printWriter.print("<tr>" +
                            "<td>" +
                               "<input type='hidden' name='uid' value='"+user.getUid()+"'>"+
                            "</td>" +
                          "</tr><br>");

        printWriter.print("<tr>" +
                            "<td>UserName: </td>" +
                            "<td>" +
                                "<input type='text' name='username' value='"+user.getUserName()+"'>"+
                            "</td>" +
                        "</tr><br>");


        printWriter.print("<tr>" +
                "<td>Email: </td>" +
                "<td>" +
                "<input type='text' name='email' value='"+user.getEmail()+"'>"+
                "</td>" +
                "</tr><br>");

        printWriter.print("<tr>" +
                "<td>Password: </td>" +
                "<td>" +
                "<input type='password' name='password' value='"+user.getPassword()+"'>"+
                "</td>" +
                "</tr><br>");

        printWriter.print("<tr>" +
                "<td>Address: </td>" +
                "<td>" +
                "<input type='text' name='address' value='"+user.getAddress()+"'>"+
                "</td>" +
                "</tr><br>");

        printWriter.print("<tr>" +
                "<td>ContactNo.: </td>" +
                "<td>" +
                "<input type='text' name='phone' value='"+user.getContactNumber()+"'>"+
                "</td>" +
                "</tr><br>");
        printWriter.print("<tr><td>" +
                "<input type='submit' value='Update'></td></tr> ");
        printWriter.print("</table>");
        printWriter.print("</form>");
        printWriter.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        }
}
