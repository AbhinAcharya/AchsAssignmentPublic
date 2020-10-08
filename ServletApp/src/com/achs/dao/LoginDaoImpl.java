package com.achs.dao;
import com.achs.util.DbConnection;

import java.sql.*;

/**
 * Created by IntelliJ IDEA.
 * User: abhinacharya
 * Date: 10/3/20
 * Time: 7:14 PM
 */
public class LoginDaoImpl  implements LoginDao{
   // User user = new User();
    String sql = "SELECT * from users WHERE user_name = ? OR email = ? AND password=?  ";

    @Override
    public int isValidUser(String userName,String password) throws SQLException, ClassNotFoundException {

        try{

           DbConnection dbConnection = new DbConnection();
        //    System.out.println("yeha samma thik xa1");
            //coderepeat vayra connection util vitra rakheko xu
            PreparedStatement statement = dbConnection.getDbConnection().prepareStatement(sql);
            statement.setString(1,userName);
            statement.setString(2,userName);
            statement.setString(3,password);

//            System.out.println("yeha samma thik xa");
   //         System.out.println(userName);
     //       System.out.println(password);
            ResultSet rs  = statement.executeQuery();
            if(rs.next()){

                return rs.getInt(1);
            }

        }catch (Exception exception){

        }
        return 0;
    }
}
