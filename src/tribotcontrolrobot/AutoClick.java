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
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.Robot;
import java.awt.event.InputEvent;

public class AutoClick {
    public static void leftClick(Robot robot)
    {
        robot.delay(100);
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.delay(50);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
        robot.delay(100);
    }
    
    public static void doubleLeftClick(Robot robot)
    {
        robot.delay(100);
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.delay(50);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
        robot.delay(100);
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.delay(50);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);   
        robot.delay(100);
    }
    public static void movePos(Robot robot, int x, int y, int time)
    {
        PointerInfo a = MouseInfo.getPointerInfo();
        Point b = a.getLocation();
        int interval = 50;
        float curX = (float)b.getX();
        float curY = (float)b.getY();
        float xSpeed = (x - curX) / time * interval;
        float ySpeed = (y - curY) / time * interval;
        for( int i = 0; i < time / 50; i++)
        {
            curX += xSpeed;
            curY += ySpeed;
            robot.mouseMove((int)curX, (int)curY);
            robot.delay(interval);
        }
    }
}
