package com.achs.dao;

import com.achs.entity.User;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: abhinacharya
 * Date: 10/3/20
 * Time: 7:14 PM
 */
public interface UserDao {
    public boolean addUser(User user) throws SQLException, ClassNotFoundException;

    public int updateUser(User user) throws SQLException, ClassNotFoundException;

    public int delete(int uId) throws SQLException, ClassNotFoundException;

    public User getUserById(int uId) throws SQLException, ClassNotFoundException;

    public List<User> getAllUsersExceptGivenId(int uId) throws SQLException, ClassNotFoundException;
}
