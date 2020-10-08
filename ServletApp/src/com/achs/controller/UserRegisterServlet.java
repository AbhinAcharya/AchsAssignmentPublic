package com.achs.controller;

import com.achs.dao.UserDao;
import com.achs.dao.UserDaoImpl;
import com.achs.entity.User;
import com.achs.util.Mail;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by IntelliJ IDEA.
 * User: abhinacharya
 * Date: 10/3/20
 * Time: 9:28 PM
 */
@WebServlet("/UserRegisterServlet")
public class UserRegisterServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = new User();
        UserDao userDao = new UserDaoImpl();

        String userName = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String address = request.getParameter("address");
        String contactNumber = request.getParameter("phone");
        user.setUserName(userName);
        user.setEmail(email);
        user.setPassword(password);
        user.setAddress(address);
        user.setContactNumber(contactNumber);
        //mail
        Mail mail = new Mail("sujanthapa2790@gmail.com","computare1file");
        //from
        try {
            mail.setFrom("sujanthapa2790@gmail.com","Assignment mail");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        try {
            mail.setSubject("About Recent Registration");
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        //recipent add garne hae
        mail.addRecipient(user.getEmail());

        try {
            if(userDao.addUser(user)){
                mail.setContent("<h1>Hi '"+user.getUserName()+"' your registration is success.</h1>","text/html");


                session.setAttribute("message","Success Now Login");
                response.sendRedirect("index.jsp");
            } else{
                mail.setContent("<h1>Hi '"+user.getUserName()+"' your registration is Failed.</h1>","text/html");

                session.setAttribute("message","UserName or Email is not unique ");
              response.sendRedirect("views/registration.jsp");
            }
        } catch (SQLException throwables) {
           // throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
           // e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        try {
            mail.send();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }


}
