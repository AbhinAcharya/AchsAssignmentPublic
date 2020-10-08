package com.achs.controller;

import com.achs.dao.UserDao;
import com.achs.dao.UserDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by IntelliJ IDEA.
 * User: abhinacharya
 * Date: 10/4/20
 * Time: 4:57 PM
 */
@WebServlet("/DeleteCustomerServlet")
public class DeleteCustomerServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int uId = Integer.parseInt(request.getParameter("uid"));
        UserDao userDao = new UserDaoImpl();
        try {
            if(userDao.delete(uId)>0){
                request.setAttribute("status","Deleted Successfully");
            }else{
                request.setAttribute("status","oops!! something went wrong");

            }
            response.sendRedirect("views/welcome.jsp");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
