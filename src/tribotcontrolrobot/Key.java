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
import java.awt.Robot;
import java.awt.event.KeyEvent;
public class Key {
    public static void type(Robot robot, int i)
    {
        robot.delay(100);
        robot.keyPress(i);
        robot.keyRelease(i);
        robot.delay(100);
    }

    public static void type(Robot robot, String s)
    {
        robot.delay(100);
        byte[] bytes = s.getBytes();
        for (byte b : bytes)
        {
            int code = b;
            // keycode only handles [A-Z] (which is ASCII decimal [65-90])
            if (code >= 'a' && code <= 'z') 
            {
                code = code - 32;
                robot.keyPress(code);
                robot.keyRelease(code);
            }
            else if (code >= 'A' && code <= 'Z')
            {
                robot.keyPress(KeyEvent.VK_SHIFT);
                robot.delay(50);
                robot.keyPress(code);
                robot.keyRelease(code);
                robot.delay(50);
                robot.keyRelease(KeyEvent.VK_SHIFT);
            }
            else if (code == '.' || code >= '0' && code <= '9')
            {
                robot.keyPress(code);
                robot.keyRelease(code);
            }
            else if (code == '@')
            {
                robot.keyPress(KeyEvent.VK_SHIFT);
                robot.delay(50);
                robot.keyPress(KeyEvent.VK_2);
                robot.keyRelease(KeyEvent.VK_2);
                robot.delay(50);
                robot.keyRelease(KeyEvent.VK_SHIFT);
            }
            robot.delay(50);
        }
        robot.delay(100);
    }
}