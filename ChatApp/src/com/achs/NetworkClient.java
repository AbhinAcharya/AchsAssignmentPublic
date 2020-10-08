package com.achs;

import com.achs.util.DbConnection;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class NetworkClient extends Thread {
    Socket s1 = null;
    String line = null;
    BufferedReader br = null;
    BufferedReader in = null;
    PrintWriter os = null;
    static JTextArea conversationView = new JTextArea();
    JTextField messageField = new JTextField();
    JButton sendMessageBtn = new JButton("Send Message");
    JButton logoutBtn = new JButton("Logout");//yo maile banako
    private String username;
    private boolean isConnectionCreated;
    public static  final String newLine = System.getProperty("line.separator");//yo pani


    public NetworkClient(String username) {
        this.username = username;
    }

    //yo innerview ko  purai maile lekheko
    public void createInnerView() throws SQLException, ClassNotFoundException {
        String innerViewSql = "SELECT * FROM messages";
        DbConnection dbConnection = new DbConnection();
        PreparedStatement preparedStatement =
        dbConnection.getDbConnection().prepareStatement(innerViewSql);
        ResultSet rs = preparedStatement.executeQuery();

        while (rs.next()){
            conversationView.append(rs.getString(2) + " -> ");
            conversationView.append(rs.getString(3) );
            conversationView.append(newLine);

        }



    }

    //yeha samma

    @Override

    public void run() {

        JFrame userFrame = new JFrame(this.username + " chat window");
        userFrame.setLayout(null);
        userFrame.setSize(500, 500);

        conversationView.setEditable(false);
        conversationView.setBounds(0, 0, 500, 350);
        userFrame.add(conversationView);

        messageField.setBounds(0, 355, 350, 65);
        userFrame.add(messageField);

        sendMessageBtn.setBounds(355, 355, 145, 65);
        userFrame.add(sendMessageBtn);
        //yo ne maile ho
        logoutBtn.setBounds(0,422,100,40);
        userFrame.add(logoutBtn);


        Runnable runnableView = new Runnable(){
            public void run() {
                conversationView.setText("");

                try {
                    createInnerView();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }

            };
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        executor.scheduleAtFixedRate(runnableView, 0, 2, TimeUnit.SECONDS);

        logoutBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String sql="UPDATE users SET is_logged_in=? WHERE uname = ? ";
           DbConnection dbConnection = new DbConnection();
                try {
                    PreparedStatement preparedStatement = dbConnection.getDbConnection().prepareStatement(sql);
                    preparedStatement.setInt(1,0);
                    preparedStatement.setString(2,username);
                    preparedStatement.executeUpdate();
         //           dbConnection.close();

                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                } catch (ClassNotFoundException classNotFoundException) {
                    classNotFoundException.printStackTrace();
                }
                userFrame.dispose();
            }
        });



//yeha samma

        sendMessageBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {

                InetAddress address = null;
                try {
                    address = InetAddress.getLocalHost();
                } catch (UnknownHostException ex) {

                }
                if (!isConnectionCreated) {
                    try {
                        s1 = new Socket(address, 4445); // You can use static final constant PORT_NUM
                        in = new BufferedReader(new InputStreamReader(s1.getInputStream()));
                        os = new PrintWriter(s1.getOutputStream());
                        isConnectionCreated = true;
                    } catch (IOException e) {
                        e.printStackTrace();
                        System.err.print("IO Exception");
                    }

                    System.out.println("Client Address : " + address);
                    System.out.println("Enter Data to echo Server ( Enter QUIT to end):");
                }

                String response = null;
                line = messageField.getText();

                //yehabata maile edit gareko
                String sql = "INSERT INTO messages(uname, messages) VALUES (?,?)";
                DbConnection dbConnection = new DbConnection();
                try {

                    PreparedStatement preparedStatement = dbConnection.getDbConnection().prepareStatement(sql);
                    preparedStatement.setString(1,username);
                    preparedStatement.setString(2,messageField.getText());
                preparedStatement.executeUpdate();
            //    dbConnection.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

                //yeha samma
                if (!line.equalsIgnoreCase("QUIT")) {
                    os.println(username + ": " + line);
                    os.flush();
                    response = messageField.getText();
                    System.out.println("Server Response : " + response);
                    line = messageField.getText();
                    messageField.setText("");
                } else {
                    try {
                        in.close();
                        os.close();
                        s1.close();
                        System.out.println("Connection Closed");
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }

            }
        });

        //userFrame.setDefaultCloseOperation(3);
        userFrame.addWindowListener(new MyWindowListener(username));
        userFrame.setVisible(true);
    }
}
class MyWindowListener extends JFrame implements WindowListener{
String userName;
    public MyWindowListener(String userName){
        this.userName = userName;
    this.addWindowListener(this);
    }
    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {

       DbConnection connection = new DbConnection();

       String sql="UPDATE users SET is_logged_in=? WHERE uname = ? ";
        DbConnection dbConnection = new DbConnection();

        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = dbConnection.getDbConnection().prepareStatement(sql);
            preparedStatement.setInt(1,0);
            preparedStatement.setString(2,userName);
            preparedStatement.executeUpdate();
         //   connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException classNotFoundException) {
            classNotFoundException.printStackTrace();
        }


    }

    @Override
    public void windowClosed(WindowEvent e) {


    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }
}