package com.achs;

import javax.swing.*;
import java.awt.*;

/**
 * Created by IntelliJ IDEA.
 * User: abhinacharya
 * Date: 10/7/20
 * Time: 11:45 AM
 */
 class WarningFrame extends JFrame{
     WarningFrame(String uName){
         JLabel jLabel1= new JLabel("oops!! We cannot create  ");
        add(jLabel1);
        JLabel jLabel2 = new JLabel("the user with following userName ");
        add(jLabel2);
        JLabel jLabel3 = new JLabel(uName+" because it is already ");
        add(jLabel3);
         JLabel jLabel4 = new JLabel(" present in our db ");
        add(jLabel4);
         setLayout(new FlowLayout());
         setVisible(true);
         setSize(300,300);

     }



}
