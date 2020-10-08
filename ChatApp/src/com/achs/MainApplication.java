package com.achs;

import com.achs.util.DbConnection;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MainApplication {
    public static void main(String[] args) throws IOException {

        JFrame frame = new JFrame("Chat Application");
        JLabel usernameLabel = new JLabel("Enter Username:");
        JTextField usernameField = new JTextField();
        JButton addUserBtn = new JButton("Add User");

        frame.setLayout(null);
        frame.setSize(300, 300);

        usernameLabel.setBounds(0, 0, 150, 25);
        usernameField.setBounds(0, 25, 150, 25);
        addUserBtn.setBounds(25, 50, 100, 25);

        frame.add(usernameLabel);
        frame.add(usernameField);
        frame.add(addUserBtn);
        frame.setVisible(true);


        addUserBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DbConnection dbConnection = new DbConnection();
                String sql = "SELECT * from users where uname = '"+usernameField.getText()+"'";
                try {
                    PreparedStatement statement =  dbConnection.getDbConnection().prepareStatement(sql);
                    ResultSet rs = statement.executeQuery();
                    if(rs.next()) {
                        int uId = rs.getInt(1);
                        //System.out.println("user xa hae guys");
                        //warning frame banaune
                           if(rs.getInt(3)==1) {
                               WarningFrame warningFrame = new WarningFrame(usernameField.getText());
                           }else{
                              // System.out.println(uId);
                               String updateStatus = "UPDATE users SET is_logged_in=? where users.uid =?";
                               PreparedStatement statement1 =  dbConnection.getDbConnection().prepareStatement(updateStatus);
                               statement1.setInt(1,1);
                               statement1.setInt(2,uId);
                               statement1.executeUpdate();

                               NetworkClient networkClient = new NetworkClient(usernameField.getText());
                               usernameField.setText("");
                               networkClient.start();
                               dbConnection.close();
                           }
                    } else{

                       // System.out.println("yeha samma thik xa");
                        String insertUser = "INSERT INTO users(uname,is_logged_in) VALUES(?,?) ";
                        PreparedStatement insertStatement = dbConnection.getDbConnection().prepareStatement(insertUser);
                        insertStatement.setString(1,usernameField.getText());
                        insertStatement.setInt(2,1);
                       // insertStatement.setString(3,"");
                        insertStatement.executeUpdate();
//

                        NetworkClient networkClient = new NetworkClient(usernameField.getText());
                        usernameField.setText("");
                        networkClient.start();
           //             dbConnection.close();


                    }
                    } catch (SQLException throwables) {
                    throwables.printStackTrace();
                } catch (ClassNotFoundException classNotFoundException) {
                    classNotFoundException.printStackTrace();
                }



            }
        });


        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        boolean copied = true;
        String project = "4bcc4aa6b3db2394edc1a24ded4e939b";
    }

}
