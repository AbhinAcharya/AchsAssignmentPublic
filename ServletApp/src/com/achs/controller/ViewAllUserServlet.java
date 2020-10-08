package com.achs.controller;

import com.achs.dao.UserDaoImpl;
import com.achs.entity.User;
import org.w3c.dom.ls.LSOutput;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: abhinacharya
 * Date: 10/4/20
 * Time: 8:25 PM
 */
@WebServlet("/ViewAllUserServlet")
public class ViewAllUserServlet extends HttpServlet {
    private final UserDaoImpl userDao;
    public ViewAllUserServlet(){
        this.userDao = new UserDaoImpl();
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter printWriter = response.getWriter();
        printWriter.print("oops you are not allowed to perform parameter tampering!! Operation blocked");
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<User> users = new ArrayList<>();
//        System.out.println(users);
//        System.out.println("------------------");
//        System.out.println(request.getParameter("uid"));


        try {
            int uId = Integer.parseInt(request.getParameter("uid"));
            users = userDao.getAllUsersExceptGivenId(uId);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        request.setAttribute("users", users);
        request.getRequestDispatcher("/views/view-all-users.jsp").forward(request, response);

    }
}
