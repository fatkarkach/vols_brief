package com.mvc.dao;

import com.mvc.bean.LoginBean;
import com.mvc.util.DBConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LoginDao {
    public  static  int id_user;
    public  static  String email;
    public String authenticateUser(LoginBean loginBean) {
        String userName = loginBean.getUserName();
        String password = loginBean.getPassword();

        Connection con = null;
        Statement statement = null;
        ResultSet resultSet = null;

        String userNameDB = "";
        String passwordDB = "";
        String roleDB = "";

        try {
            con = DBConnection.createConnection();
            statement = con.createStatement();
            resultSet = statement.executeQuery("select *  from users");

            while (resultSet.next()) {
                id_user=resultSet.getInt("id");
                email=resultSet.getString("Email");
                userNameDB = resultSet.getString("userName");
                passwordDB = resultSet.getString("password");
                roleDB = resultSet.getString("role");

                if (userName.equals(userNameDB) && password.equals(passwordDB) && roleDB.equals("Admin"))
                    return "Admin_Role";
                else if (userName.equals(userNameDB) && password.equals(passwordDB) && roleDB.equals("User"))
                    return "User_Role";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "Invalid user credentials";
    }
}