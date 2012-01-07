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

import java.awt.AWTException;
import java.awt.Robot;
import java.io.IOException;
import java.awt.event.KeyEvent;
import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinUser.WNDENUMPROC;
import com.sun.jna.platform.win32.WinUser;
import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.platform.win32.WinDef.HWND;
import static java.lang.Thread.sleep;
import java.util.ArrayList;
import java.util.prefs.Preferences;

public class TribotControlRobot {

    public Robot robot;
    private Runtime runtime;
    public static String javaPath = "";
    public static String tribotPath = "";
    public static int nTribotCount = 1;
    public static int nTabCount = 1;
    public static String heapSize = "";
    public static String tribotLoginUsername = "";
    public static String tribotLoginPassword = "";
    public static ArrayList<String> clientLoginUsernames = new ArrayList<String>();
    public static String clientLoginPassword = "";
    
    public static final String TRIBOT_COUNT = "TribotCount";
    public static final String TAB_COUNT = "TabCount";
    public static final String HEAP_SIZE = "HeapSize";
    public static final String JAVA_PATH = "JavaPath";
    public static final String TRIBOT_PATH = "TribotPath";
    public static final String TRIBOT_LOGIN_USERNAME = "TribotLoginUsername";
    public static final String TRIBOT_LOGIN_PASSWORD = "TribotLoginPassword";
    //public static final String CLIENT_LOGIN_USERNAME = "ClientLoginUsername";
    public static final String MAIL_PATH = "MailPath";
    public static final String CLIENT_LOGIN_PASSWORD = "ClientLoginPassword";
    public TribotControlRobot()
    {
        try {
            robot = new Robot();
        } catch (AWTException ex) {
            ex.printStackTrace();
        }
    }
    
    public void RunBot()
    {
        // step1
        runTribot(nTribotCount);
        // step2
        ArrayList<HWND> loginWnd = null;
        do{
            if(loginWnd != null)loginWnd.clear();
            loginWnd = findWindow("TRiBot Login");
        }while(loginWnd.size() < nTribotCount);
        // step3
        for(int i = 0; i < nTribotCount; i++)
            moveLoginWindow(loginWnd.get(i), i);
       
        robot.delay(1000);
        
        for(int i = 0; i < nTribotCount; i++){
            loginTribot(loginWnd.get(i), i);
        }
        
        ArrayList<HWND> tribotWnd = null;
        do{
            if(tribotWnd != null)tribotWnd.clear();
            tribotWnd = findWindow("TRiBot Old-School - The Desktop Botting Solution");
        }while(tribotWnd.size() < nTribotCount);
        
        for(int i = 0; i < nTribotCount; i++)
            moveTribotWindow(tribotWnd.get(i), i);
        
        for(int i = 0; i < nTribotCount; i++)
            User32.INSTANCE.SetForegroundWindow(tribotWnd.get(i));
        
        robot.delay(1000);
        for(int j = 0; j < nTabCount; j++){
            for(int i = 0; i < nTribotCount; i++){           
                newClient(tribotWnd.get(i));  
//                robot.delay(5000);
//                killMessageBox("Firewall Prompt");
//                robot.delay(5000);
//                killMessageBox("Firewall Prompt");
//                robot.delay(500);
            }

        }
        
        
        for(int i = 0; i < nTribotCount; i++)
            User32.INSTANCE.SetForegroundWindow(tribotWnd.get(i));
    }
        
    public void StartScript()
    {
        ArrayList<HWND> tribotWnd = null;
        do{
            if(tribotWnd != null)tribotWnd.clear();
            tribotWnd = findWindow("TRiBot Old-School - The Desktop Botting Solution");
        }while(tribotWnd.size() < nTribotCount);
        for(int i = 0; i < nTribotCount; i++){
            for(int j = 0; j < nTabCount; j++){
                startScript(tribotWnd.get(i), j);    
                
            }  
        }        
    }
    
    public void LoginClient()
    {
        ArrayList<HWND> tribotWnd = null;
        do{
            if(tribotWnd != null)tribotWnd.clear();
            tribotWnd = findWindow("TRiBot Old-School - The Desktop Botting Solution");
        }while(tribotWnd.size() < nTribotCount);
        for(int i = 0; i < nTribotCount; i++){
            for(int j = 0; j < nTabCount; j++){
                selectWorld(tribotWnd.get(i), j);    
//                robot.delay(3000);
//                killMessageBox("Firewall Prompt");
//                robot.delay(500);
                click301(tribotWnd.get(i));
            }  
        }
       
        for(int i = 0; i < nTribotCount; i++){
            for(int j = 0; j < nTabCount; j++){
                loginClient(tribotWnd.get(i), i, j);
                
            }
        }
    }
    
    public void KillBot()
    {
        ArrayList<HWND> tribotWnd = null;
        tribotWnd = findWindow("TRiBot Old-School - The Desktop Botting Solution");
        for(int i = 0; i < tribotWnd.size(); i++)
            User32.INSTANCE.PostMessage(tribotWnd.get(i), WinUser.WM_CLOSE, null, null);
        tribotWnd = findWindow("TRiBot Loader");
        for(int i = 0; i < tribotWnd.size(); i++)
            User32.INSTANCE.PostMessage(tribotWnd.get(i), WinUser.WM_CLOSE, null, null);
        tribotWnd = findWindow("TRiBot Login");
        for(int i = 0; i < tribotWnd.size(); i++)
            User32.INSTANCE.PostMessage(tribotWnd.get(i), WinUser.WM_CLOSE, null, null);
    }
    
    private void startScript(HWND hwnd, int tabNo)
    {
        User32.RECT rect = new User32.RECT();
        User32.INSTANCE.SetForegroundWindow(hwnd);
        User32.INSTANCE.GetWindowRect(hwnd, rect);
        int xSet = rect.left, ySet = rect.top;
        //robot.mouseMove(xSet + 80 + tabNo * 105, ySet + 70);
        AutoClick.movePos(robot, xSet + 80 + tabNo * 105, ySet + 70, 1000);
        AutoClick.leftClick(robot);//tab button
        robot.delay(500); 
        
        //robot.mouseMove(xSet + 440, ySet + 45);//Start Script
        AutoClick.movePos(robot, xSet + 440, ySet + 45, 1000);
        AutoClick.leftClick(robot);
        robot.delay(3000);
        //robot.mouseMove(xSet + 73, ySet + 216);//Woodcutting
        AutoClick.movePos(robot, xSet + 71, ySet + 216, 1000);
        AutoClick.leftClick(robot);
        robot.delay(200);
        //robot.mouseMove(xSet + 140, ySet + 235);//Einstein's Woodcutting
        AutoClick.movePos(robot, xSet + 140, ySet + 235, 1000);
        AutoClick.leftClick(robot);
        robot.delay(200);
        //robot.mouseMove(xSet + 685, ySet + 395);//Start
        AutoClick.movePos(robot, xSet + 685, ySet + 395, 1000);
        AutoClick.leftClick(robot);
        robot.delay(2000);
    }
    
    private void loginTribot(HWND hwnd, int no)
    {
        int ySet = no * 25;
        User32.INSTANCE.SetForegroundWindow(hwnd);
        Key.type(robot, KeyEvent.VK_DELETE);
        User32.INSTANCE.SetForegroundWindow(hwnd);
        Key.type(robot, tribotLoginUsername);
        User32.INSTANCE.SetForegroundWindow(hwnd);
        Key.type(robot, KeyEvent.VK_TAB);
        User32.INSTANCE.SetForegroundWindow(hwnd);
        Key.type(robot, tribotLoginPassword);
        //robot.mouseMove(135, 192 + ySet);
        AutoClick.movePos(robot, 135, 192 + ySet, 1000);
        AutoClick.doubleLeftClick(robot);
        Key.type(robot, heapSize);
        //robot.mouseMove(450, 237 + ySet);
        AutoClick.movePos(robot, 450, 237 + ySet, 1000);
        AutoClick.leftClick(robot);
        //robot.mouseMove(450, 237  + ySet + 24 * no);
        AutoClick.movePos(robot, 450, 237  + ySet + 24 * no, 1000);
        AutoClick.leftClick(robot);
        AutoClick.movePos(robot, 270, 272  + ySet, 500);
        AutoClick.leftClick(robot);
        robot.delay(500);
    }
    
    private void runTribot(int cnt)
    {
        for(int i = 0; i < cnt; i++){
            runtime = Runtime.getRuntime();
            try {
                runtime.exec(javaPath + " -jar " + tribotPath);
            } catch (IOException ex) {
                ex.printStackTrace();
                System.exit(0);
            } 
        }
    }
    
    private void newClient(HWND hwnd)
    {
        User32.RECT rect = new User32.RECT();
        User32.INSTANCE.SetForegroundWindow(hwnd);
        User32.INSTANCE.GetWindowRect(hwnd, rect);
        int xSet = rect.left, ySet = rect.top;
        //robot.mouseMove(xSet + 95, ySet + 45);//client menu
        AutoClick.movePos(robot, xSet + 95, ySet + 45, 1000);
        AutoClick.leftClick(robot);
        robot.delay(3000);
        //robot.mouseMove(xSet + 95, ySet + 70);//client/new client
        AutoClick.movePos(robot, xSet + 95, ySet + 70, 1000);
        AutoClick.leftClick(robot);
        robot.delay(2000);
    }
    
    private void selectWorld(HWND hwnd, int tabNo)
    {
        User32.RECT rect = new User32.RECT();
        User32.INSTANCE.SetForegroundWindow(hwnd);
        User32.INSTANCE.GetWindowRect(hwnd, rect);
        int xSet = rect.left, ySet = rect.top;
        //robot.mouseMove(xSet + 80 + tabNo * 105, ySet + 70);
        AutoClick.movePos(robot, xSet + 80 + tabNo * 105, ySet + 70, 1000);
        AutoClick.leftClick(robot);//tab button
        robot.delay(3000); 
        User32.INSTANCE.SetForegroundWindow(hwnd);
        //robot.mouseMove(xSet + 60, ySet + 565);//world button    
        AutoClick.movePos(robot, xSet + 60, ySet + 565, 1000);
        AutoClick.leftClick(robot);
        robot.delay(3000);
    }
    private void click301(HWND hwnd)
    {
        User32.RECT rect = new User32.RECT();
        User32.INSTANCE.SetForegroundWindow(hwnd);
        User32.INSTANCE.GetWindowRect(hwnd, rect);
        int xSet = rect.left, ySet = rect.top;
        //robot.mouseMove(xSet + 155, ySet + 140);//301 button
        AutoClick.movePos(robot, xSet + 155, ySet + 140, 1000);
        AutoClick.leftClick(robot);
        robot.delay(2000);
    }
    
    private void loginClient(HWND hwnd, int botNo, int tabNo)
    {
        User32.RECT rect = new User32.RECT();
        User32.INSTANCE.SetForegroundWindow(hwnd);
        User32.INSTANCE.GetWindowRect(hwnd, rect);
        int xSet = rect.left, ySet = rect.top;
        //robot.mouseMove(xSet + 80 + tabNo * 105, ySet + 70);
        AutoClick.movePos(robot, xSet + 80 + tabNo * 105, ySet + 70, 1000);
        AutoClick.leftClick(robot);//tab button
        robot.delay(500); 
        User32.INSTANCE.SetForegroundWindow(hwnd);
        //robot.mouseMove(xSet + 470, ySet + 375);//Existing User button  
        AutoClick.movePos(robot, xSet + 470, ySet + 375, 1000);
        AutoClick.leftClick(robot);
        robot.delay(2000);
        User32.INSTANCE.SetForegroundWindow(hwnd);
        int index = botNo * nTabCount + tabNo;
        if(index >= clientLoginUsernames.size()) index = index % clientLoginUsernames.size();
        Key.type(robot, clientLoginUsernames.get(index));
        Key.type(robot, KeyEvent.VK_TAB);
        Key.type(robot, clientLoginPassword);
        //robot.mouseMove(xSet + 310, ySet + 405);//301 button
        AutoClick.movePos(robot, xSet + 310, ySet + 405, 1000);
        robot.delay(2000);
        AutoClick.leftClick(robot);
        robot.delay(1000);
    }
    
    private void moveLoginWindow(HWND hwnd, int no)
    {
        User32.RECT rect = new User32.RECT();
        User32.INSTANCE.GetWindowRect(hwnd, rect);
        User32.INSTANCE.MoveWindow(hwnd, 0, no * 25, rect.right - rect.left, rect.bottom - rect.top, true);
    }
    
    private void moveTribotWindow(HWND hwnd, int no)
    {
        User32.RECT rect = new User32.RECT();
        User32.INSTANCE.GetWindowRect(hwnd, rect);
        User32.INSTANCE.MoveWindow(hwnd, 500 + no * 25, no * 25, rect.right - rect.left, rect.bottom - rect.top, true);
    }
    
    public static ArrayList<HWND> findWindow(final String name) {
        ArrayList<HWND> result = new ArrayList<HWND>();
        User32.INSTANCE.EnumWindows(new WNDENUMPROC() {
            public boolean callback(HWND hWnd, Pointer arg1) {
                char[] windowText = new char[512];
                User32.INSTANCE.GetWindowText(hWnd, windowText, 512);
                String wText = Native.toString(windowText);
                if (wText.equals(name)) {
                    result.add(hWnd);
                }
                return true;
            }
        }, null);
        return result;
    }
    
    private void killMessageBox(String name)
    {
        ArrayList<HWND> messageWnd = null;
        messageWnd = findWindow(name);
        for(WinDef.HWND hwnd : messageWnd)
        {
            Key.type(robot, KeyEvent.VK_ENTER);
        }  
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Preferences userPref = Preferences.userRoot();
        Form form = new Form(
            userPref.get(TRIBOT_COUNT, "1"),
            userPref.get(TAB_COUNT, "1"),
            userPref.get(HEAP_SIZE, "768"),
            userPref.get(JAVA_PATH, "C:\\Program Files (x86)\\Java\\jdk1.8.0_171\\bin\\java.exe"),
            userPref.get(TRIBOT_PATH, "C:\\Users\\Hiccup\\Downloads\\TRiBot_Loader.jar"),
            userPref.get(TRIBOT_LOGIN_USERNAME, "Hunterkiss"),
            userPref.get(TRIBOT_LOGIN_PASSWORD, "Hpm.41771"),
            //userPref.get(CLIENT_LOGIN_USERNAME, "osrsfan250@gmail.com"),
            userPref.get(MAIL_PATH, "C:\\Accounts.txt"),
            userPref.get(CLIENT_LOGIN_PASSWORD, "hpm41771")
        );
        form.setVisible(true);
    }
         
}
     