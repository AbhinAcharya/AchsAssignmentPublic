package com.achs.controller;

import com.achs.dao.LoginDao;
import com.achs.dao.LoginDaoImpl;
import com.achs.dao.UserDao;
import com.achs.dao.UserDaoImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

/**
 * Created by IntelliJ IDEA.
 * User: abhinacharya
 * Date: 10/1/20
 * Time: 12:45 PM
 */
@WebServlet("/Login")
public class LoginServlet extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
       String userName = request.getParameter("username");
       String password = request.getParameter("password");
        LoginDao dao = new LoginDaoImpl();

        try {
            HttpSession session = request.getSession();
           // int test = dao.isValidUser(userName,password);
//            System.out.println("---------------------------------");
//            System.out.println(test);
//            System.out.println("---------------------------------");
int uId = dao.isValidUser(userName,password);
            if(uId!=0){

               session.setAttribute("username",userName);
               session.setAttribute("uid",uId);
                response.sendRedirect("views/welcome.jsp");
            }else{
                session.setAttribute("message","wrong username or password");
            response.sendRedirect("index.jsp");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }




    }

}
