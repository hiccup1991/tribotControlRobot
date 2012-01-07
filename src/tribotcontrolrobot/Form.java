/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tribotcontrolrobot;

/**
 *
 * @author Hiccup
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.prefs.Preferences;
import javax.swing.UIManager.*;

public class Form extends JFrame {

    private JButton btnRunBot  = new JButton("RunBot");
    private JButton btnLoginClient = new JButton("LoginClient");
    private JButton btnStartEinstein = new JButton("StartEinstein");
    private JButton btnKillBot = new JButton("KillBot");
    
    private JTextField txtTribotCount = new JTextField();
    private JTextField txtTabCount = new JTextField();
    private JTextField txtHeapSize = new JTextField();
    private JTextField txtJavaPath = new JTextField();
    private JTextField txtTribotPath = new JTextField();
    private JTextField txtTribotLoginUsername = new JTextField();
    private JPasswordField txtTribotLoginPassword = new JPasswordField();    
    //private JTextField txtClientLoginUsername = new JTextField();
    private JTextField txtMailPath = new JTextField();
    private JPasswordField txtClientLoginPassword = new JPasswordField();
    
    private JLabel lblTribotCount = new JLabel("Tribot Count :");
    private JLabel lblTabCount = new JLabel("Tab Count per Tribot :");
    private JLabel lblHeapSize = new JLabel("HeapSize :");
    private JLabel lblJavaPath = new JLabel("Java Path :");
    private JLabel lblTribotPath = new JLabel("Tribot Path :");
    private JLabel lblTribotLoginUsername = new JLabel("Tribot Username :");
    private JLabel lblTribotLoginPassword = new JLabel("Tribot Password :");
    //private JLabel lblClientLoginUsername = new JLabel("Client Username :");
    private JLabel lblMailPath = new JLabel("Mail Path :");
    private JLabel lblClientLoginPassword = new JLabel("Client Password :");   
    
    private TribotControlRobot myrobot = null;
    
    public Form(String tribotCount, String tabCount, String heapSize, String javaPath, String tribotPath
            , String tribotLoginUsername, String tribotLoginPassword, String mailPath, /*String clientLoginUsername,*/ String clientLoginPassword){
        setTitle("TribotAutoControl2.0");
        setSize(680,310);
        setLocation(new Point(200,250));
        setLayout(null);    
        setResizable(false);

        initComponent(tribotCount, tabCount, heapSize, javaPath, tribotPath, tribotLoginUsername, tribotLoginPassword, mailPath, /*clientLoginUsername,*/ clientLoginPassword);    
        initEvent();    
        
        myrobot = new TribotControlRobot();
    }

    private void initComponent(String tribotCount, String tabCount, String heapSize, String javaPath, String tribotPath
            , String tribotLoginUsername, String tribotLoginPassword, String mailPath, /* String clientLoginUsername,*/ String clientLoginPassword){
        try {
            for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
//                if ("Windows".equals(info.getName())) {
//                    UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//                if ("CDE/Motif".equals(info.getName())) {
//                    UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//                if ("Metal".equals(info.getName())) {
//                    UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
//                if ("Windows Classic".equals(info.getName())) {
//                    UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }               
                System.out.println(info.getName());
            }
//            UIManager.setLookAndFeel("com.seaglasslookandfeel.SeaGlassLookAndFeel");
        } catch (Exception e) {
            // If Nimbus is not available, you can set the GUI to another look and feel.
        }
        
        btnRunBot.setBounds(50,240, 120,25);
        btnLoginClient.setBounds(190, 240, 120, 25);
        btnStartEinstein.setBounds(330, 240, 120, 25);
        btnKillBot.setBounds(470, 240, 120, 25);
        
        txtTribotCount.setBounds(150,10,500,20);
        txtTabCount.setBounds(150,35,500,20);
        txtHeapSize.setBounds(150, 60, 500, 20);
        txtJavaPath.setBounds(150,85,500,20);
        txtTribotPath.setBounds(150, 110, 500, 20);
        txtTribotLoginUsername.setBounds(150,135,500,20);
        txtTribotLoginPassword.setBounds(150, 160, 500, 20);
        //txtClientLoginUsername.setBounds(150,185,500,20);
        txtMailPath.setBounds(150,185,500,20);
        txtClientLoginPassword.setBounds(150, 210, 500, 20);
        
        txtTribotCount.setText(tribotCount);
        txtTabCount.setText(tabCount);
        txtHeapSize.setText(heapSize);
        txtJavaPath.setText(javaPath);
        txtTribotPath.setText(tribotPath);
        txtTribotLoginUsername.setText(tribotLoginUsername);
        txtTribotLoginPassword.setText(tribotLoginPassword);
        txtTribotLoginPassword.setEchoChar('*');
        //txtClientLoginUsername.setText(clientLoginUsername);
        txtMailPath.setText(mailPath);
        txtClientLoginPassword.setText(clientLoginPassword);
        txtClientLoginPassword.setEchoChar('*');
        
        lblTribotCount.setBounds(20,10,130,20);
        lblTabCount.setBounds(20,35,130,20);
        lblHeapSize.setBounds(20,60,130,20);
        lblJavaPath.setBounds(20,85,130,20);
        lblTribotPath.setBounds(20, 110, 130, 20);
        lblTribotLoginUsername.setBounds(20,135,130,20);
        lblTribotLoginPassword.setBounds(20, 160, 130, 20);
        //lblClientLoginUsername.setBounds(20,185,165,20);
        lblMailPath.setBounds(20,185,165,20);
        lblMailPath.setBounds(20,185,165,20);
        lblClientLoginPassword.setBounds(20, 210, 130, 20);

        add(btnRunBot);
        add(btnLoginClient);
        add(btnStartEinstein);
        add(btnKillBot);
        
        add(lblTribotCount);
        add(lblTabCount);
        add(lblHeapSize);
        add(lblJavaPath);
        add(lblTribotLoginUsername);
        add(lblTribotLoginPassword);
        //add(lblClientLoginUsername);
        add(lblMailPath);
        add(lblClientLoginPassword);
        add(lblTribotPath);
        add(txtTribotCount);
        add(txtTabCount);
        add(txtHeapSize);
        add(txtJavaPath);
        add(txtTribotPath);
        add(txtTribotLoginUsername);
        add(txtTribotLoginPassword);
        //add(txtClientLoginUsername);
        add(txtMailPath);
        add(txtClientLoginPassword);
        
        btnEnableSetting(0);
        btnKillBot.setEnabled(false);
    }

    private void initEvent(){
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e){
                if(myrobot != null)
                    myrobot.KillBot();
                Preferences userPref = Preferences.userRoot();
                userPref.put(TribotControlRobot.TRIBOT_COUNT, txtTribotCount.getText());
                userPref.put(TribotControlRobot.TAB_COUNT, txtTabCount.getText());
                userPref.put(TribotControlRobot.HEAP_SIZE, txtHeapSize.getText());
                userPref.put(TribotControlRobot.JAVA_PATH, txtJavaPath.getText());
                userPref.put(TribotControlRobot.TRIBOT_PATH, txtTribotPath.getText());
                userPref.put(TribotControlRobot.TRIBOT_LOGIN_USERNAME, txtTribotLoginUsername.getText());
                userPref.put(TribotControlRobot.TRIBOT_LOGIN_PASSWORD, txtTribotLoginPassword.getText());
                userPref.put(TribotControlRobot.MAIL_PATH, txtMailPath.getText());
                //userPref.put(TribotControlRobot.CLIENT_LOGIN_USERNAME, txtClientLoginUsername.getText());
                userPref.put(TribotControlRobot.CLIENT_LOGIN_PASSWORD, txtClientLoginPassword.getText());
                System.exit(0);
            }
        });

        btnRunBot.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnRunBot(e);
            }
        });
        
        btnLoginClient.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnLoginClient(e);
            }
        });
        
        btnStartEinstein.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnStartEdison(e);
            }
        });
        
        btnKillBot.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnKillBot(e);

            }
        });
    }
  
    private void btnRunBot(ActionEvent evt){    
        myrobot.nTribotCount = Integer.parseInt(txtTribotCount.getText());
        myrobot.nTabCount = Integer.parseInt(txtTabCount.getText());
        myrobot.heapSize = txtHeapSize.getText();
        myrobot.javaPath = txtJavaPath.getText();
        myrobot.tribotPath = txtTribotPath.getText();
        myrobot.tribotLoginUsername = txtTribotLoginUsername.getText();
        myrobot.tribotLoginPassword = txtTribotLoginPassword.getText();
        myrobot.RunBot();
        btnEnableSetting(1);
        btnKillBot.setEnabled(true);
    }
    
    private void btnLoginClient(ActionEvent evt){
        //myrobot.clientLoginUsername = txtClientLoginUsername.getText();
        String filePath = txtMailPath.getText();
        
        try {
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();           
            while (line != null) {
                line = line.trim();
                if(!line.equals(""))
                    myrobot.clientLoginUsernames.add(line);
                line = br.readLine();
            }
        } catch(Exception ex) {
        }
        System.out.println(filePath);
        System.out.println(myrobot.clientLoginUsernames.size());
        myrobot.clientLoginPassword = txtClientLoginPassword.getText();
        myrobot.LoginClient();
        btnEnableSetting(2);
    }
    
    private void btnStartEdison(ActionEvent evt)
    {
        myrobot.StartScript();
        btnEnableSetting(3);
    }
    
    private void btnKillBot(ActionEvent evt){
        TribotControlRobot.clientLoginUsernames.clear();
        myrobot.KillBot();
        btnEnableSetting(0);
        btnKillBot.setEnabled(false);
    }
    
    private void btnEnableSetting(int i)
    {
        btnRunBot.setEnabled(false);
        btnLoginClient.setEnabled(false);
        btnStartEinstein.setEnabled(false);
        switch(i){
            case 0:btnRunBot.setEnabled(true);break;
            case 1:btnLoginClient.setEnabled(true);break;
            case 2:btnStartEinstein.setEnabled(true);break;
        }
    }
}
