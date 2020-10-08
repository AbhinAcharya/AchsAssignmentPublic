package com.achs.dao;

import com.achs.entity.User;
import com.achs.util.DbConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: abhinacharya
 * Date: 10/3/20
 * Time: 9:25 PM
 */
public class UserDaoImpl implements UserDao {
    DbConnection dbConnection = new DbConnection();
    int result = 0;
    @Override
    public boolean addUser(User user) throws SQLException, ClassNotFoundException {
        try {

            String sql = "Insert into users" + "(user_name,email,password,address,contact_number) Values"+"(?,?,?,?,?) ;";
            PreparedStatement preparedStatement = dbConnection.getDbConnection().prepareStatement(sql);
          //  System.out.println(user.getUserName());
            preparedStatement.setString(1, user.getUserName());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setString(4, user.getAddress());
            preparedStatement.setString(5, user.getContactNumber());

           result =  preparedStatement.executeUpdate();



            if(result==1){

                return true;
            }
//            else{
//                mail.setContent("<h1>Hi '"+user.getUserName()+"' your registration is Failed</h1>","text/html");
//                mail.send();
//                return false;
//            }

        }catch (Exception exception){

            return false;
        }

        return false;
    }

    @Override
    public int updateUser(User user) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE users SET user_name = ?,email=?,password=? ,address=?,contact_number=? WHERE uid = ?";
        PreparedStatement statement = dbConnection.getDbConnection().prepareStatement(sql);
        statement.setString(1,user.getUserName());
        statement.setString(2,user.getEmail());
        statement.setString(3,user.getPassword());
        statement.setString(4,user.getAddress());
        statement.setString(5,user.getContactNumber());
        statement.setInt(6,user.getUid());

        return statement.executeUpdate();
    }

    @Override
    public int delete(int uId) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM users WHERE uid = ?";
        PreparedStatement statement = dbConnection.getDbConnection().prepareStatement(sql);
        statement.setInt(1,uId);
        return statement.executeUpdate();

    }

    @Override
    public User getUserById(int uId) throws SQLException, ClassNotFoundException {
        User user = new User();
        String sql = "SELECT * FROM users WHERE uid=?";
        PreparedStatement statement = dbConnection.getDbConnection().prepareStatement(sql);
    statement.setInt(1,uId);
    ResultSet rs = statement.executeQuery();
    if(rs.next()) {
        user.setUid(rs.getInt(1));
        user.setUserName(rs.getString(2));
        user.setEmail(rs.getString(3));
        user.setPassword(rs.getString(4));
        user.setAddress(rs.getString(5));
        user.setContactNumber(rs.getString(6));
          }

       // int userId = rs.getInt("uid");
//        String userName = rs.getString("user_name");
//        String email = rs.getString("email");
//        String password = rs.getString("password");
//        String address = rs.getString("address");
//        String contactNumber = rs.getString("contact_number");
//       user.setUid(uId);
//       user.setUserName(userName);
   // }
//        System.out.println(user.getUserName());
//        System.out.println(user.getUid());
    return user;
    }

    @Override
    public List<User> getAllUsersExceptGivenId(int uID) throws SQLException, ClassNotFoundException {
        List<User> allUsersExceptGivenId = new ArrayList<>();
        String sql = "SELECT * from users where uid!='"+uID+"'";
        Statement statement = dbConnection.getDbConnection().createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()){
            int uId = resultSet.getInt("uid");
            String userName = resultSet.getString("user_name");
            String email = resultSet.getString("email");
            String password = resultSet.getString("password");
            String address = resultSet.getString("address");
            String contactNumber = resultSet.getString("contact_number");
        allUsersExceptGivenId.add(new User(uId,userName,email,password,address,contactNumber));
//            User user = new User();
//            user.setUid(rs.getInt(1));
//            user.setUserName(rs.getString(2));
//            user.setEmail(rs.getString(3));
//            user.setPassword(rs.getString(4));
//            user.setAddress(rs.getString(5));
//            user.setContactNumber(rs.getString(6));


        }
        return allUsersExceptGivenId;
    }

}
