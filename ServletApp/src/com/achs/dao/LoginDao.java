package com.achs.dao;

import com.achs.entity.User;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: abhinacharya
 * Date: 10/3/20
 * Time: 4:47 PM
 */
public interface LoginDao {
    public int isValidUser(String userName,String password) throws SQLException, ClassNotFoundException;
}
