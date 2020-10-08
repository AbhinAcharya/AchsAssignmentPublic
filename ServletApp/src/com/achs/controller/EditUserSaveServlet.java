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
import java.sql.SQLException;

/**
 * Created by IntelliJ IDEA.
 * User: abhinacharya
 * Date: 10/4/20
 * Time: 4:07 PM
 */
@WebServlet("/EditUserSaveServlet")
public class EditUserSaveServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    int uId = Integer.parseInt(request.getParameter("uid"));
    String userName= request.getParameter("username");
    String email = request.getParameter("email");
    String password = request.getParameter("password");
    String address = request.getParameter("address");
    String contactNumber = request.getParameter("phone");

    UserDao userDao = new UserDaoImpl();
    User user = new User();

    user.setUid(uId);
    user.setUserName(userName);
    user.setEmail(email);
    user.setPassword(password);
    user.setAddress(address);
    user.setContactNumber(contactNumber);

        try {
            if(userDao.updateUser(user)>0){
                request.setAttribute("status","Successfully Updated");
            }else{
                request.setAttribute("status","oops!! something went wrong,could not update");
            }
            response.sendRedirect("views/welcome.jsp");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
