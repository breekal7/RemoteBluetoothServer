/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package remotebluetoothserver;

/**
 *
 * @author saifur
 */
import java.awt.AWTException;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.microedition.io.StreamConnection;

public class ProcessConnectionThread implements Runnable {

    private StreamConnection comm = null;
    private static final int EXIT_CMD = -1;
    private static final int KEY_RIGHT = 2;
    private static final int KEY_LEFT = 3;

    private static final int KEY_UP = 4;
    private static final int KEY_DOWN = 5;

    private static final int KEY_PAGE_UP = 6;
    private static final int KEY_PAGE_DOWN = 7;

    private static final int KEY_HOME = 8;
    private static final int KEY_END = 9;

    private static final int KEY_INSERT = 10;
    private static final int KEY_DELETE = 11;

    private static final int KEY_SPACE = 12;

    private static final int KEY_A = 13;
    private static final int KEY_B = 14;
    private static final int KEY_C = 15;
    private static final int KEY_D = 16;
    private static final int KEY_E = 17;
    private static final int KEY_F = 18;
    private static final int KEY_G = 19;
    private static final int KEY_H = 20;
    private static final int KEY_I = 21;
    private static final int KEY_J = 22;
    private static final int KEY_K = 23;
    private static final int KEY_L = 24;
    private static final int KEY_M = 25;
    private static final int KEY_N = 26;
    private static final int KEY_O = 27;
    private static final int KEY_P = 28;
    private static final int KEY_Q = 29;
    private static final int KEY_R = 30;
    private static final int KEY_S = 31;
    private static final int KEY_T = 32;
    private static final int KEY_U = 33;
    private static final int KEY_V = 34;
    private static final int KEY_W = 35;
    private static final int KEY_X = 36;
    private static final int KEY_Y = 37;
    private static final int KEY_Z = 38;
    private static final int KEY_CTRL = 39;
    private static final int KEY_ALT = 40;
    private static final int KEY_SHIFT = 41;
    private static final int KEY_FN = 42;
    private static final int KEY_MENU = 43;

    public ProcessConnectionThread(StreamConnection conn) {
        comm = conn;
    }

    private static String getStringFromInputStream(BufferedReader is) throws IOException {
        StringBuilder sb = new StringBuilder();
        String line;
        line = is.readLine();
        sb.append(line);
        return sb.toString();
    }

    @Override
    public void run() {
        try {

            System.out.println("waiting for the input");
            Robot robo = new Robot();
            String lineRead;
            boolean mouseOk = false;
            boolean keyOk = false;
            BufferedReader in = null;
            int i = 0;
            int command = 0;
            in = new BufferedReader(new InputStreamReader(comm.openDataInputStream()));

            while (true) {
                if (mouseOk == false && keyOk == false) {
                    if (mouseOk == false) {
                        command = in.read();
                        if (command == 1) {
                            mouseOk = true;
                            keyOk = false;
                        } else {
                            keyOk = true;
                            mouseOk = false;
                        }
                    }
                } else if (mouseOk == true) {

                    in.readLine();

                    String s = getStringFromInputStream(in);

                    s.substring(0, s.length() - 2);

                    PointerInfo a = MouseInfo.getPointerInfo();
                    Point b = a.getLocation();
                    int x = (int) b.getX();
                    int y = (int) b.getY();

                    if (s.compareTo("left") == 0) {
                        //single left click
                        robo.mousePress(InputEvent.BUTTON1_MASK);
                        robo.mouseRelease(InputEvent.BUTTON1_MASK);
                    } else if (s.compareTo("leftd") == 0) {
                        robo.mousePress(InputEvent.BUTTON1_MASK);
                        robo.mouseRelease(InputEvent.BUTTON1_MASK);
                        robo.mousePress(InputEvent.BUTTON1_MASK);
                        robo.mouseRelease(InputEvent.BUTTON1_MASK);
                        System.out.println(s);
                    } else if (s.compareTo("right") == 0) {
                        robo.mousePress(InputEvent.BUTTON3_MASK);
                        robo.mouseRelease(InputEvent.BUTTON3_MASK);
                        System.out.println(s);
                    } else if (s.compareTo("scrollup") == 0) {
                        robo.mouseWheel(-5);
                        System.out.println(s);
                    } else if (s.compareTo("scrolldown") == 0) {
                        robo.mouseWheel(+5);
                        System.out.println(s);
                    } else {
                        int x1 = 0, x2 = 0, y1 = 0, y2 = 0;
                        char opX = '+';
                        char opY = '+';

                        opX = s.charAt(0);
                        opY = s.charAt(1);
                        if (opX == '+') {
                            x += 2;
                        }
                        if (opX == '-') {
                            x -= 2;
                        }
                        if (opY == '+') {
                            y += 2;
                        }
                        if (opY == '-') {
                            y -= 2;
                        }

                        System.out.println(s);
                        System.out.println("\nMoving to: " + x + " " + y);
                        robo.mouseMove(x, y);

                    }

                } else {//keyboard on
                    command = in.read();

                    if (command == EXIT_CMD) {
                        System.out.println("Process terminating");
                        break;
                    } else {

                        switch (command) {
                            case KEY_RIGHT:
                                robo.keyPress(KeyEvent.VK_RIGHT);
                                System.out.println("right");
                                break;
                            case KEY_LEFT:
                                robo.keyPress(KeyEvent.VK_LEFT);
                                System.out.println("left");
                                break;

                            case KEY_UP:
                                robo.keyPress(KeyEvent.VK_UP);
                                System.out.println("up");
                                break;
                            case KEY_DOWN:
                                robo.keyPress(KeyEvent.VK_DOWN);
                                System.out.println("down");
                                break;
                            case KEY_PAGE_UP:
                                robo.keyPress(KeyEvent.VK_PAGE_UP);
                                System.out.println("pageup");
                                break;
                            case KEY_PAGE_DOWN:
                                robo.keyPress(KeyEvent.VK_PAGE_DOWN);
                                System.out.println("page down");
                                break;
                            case KEY_HOME:
                                robo.keyPress(KeyEvent.VK_HOME);
                                System.out.println("HOME");
                                break;
                            case KEY_END:
                                robo.keyPress(KeyEvent.VK_END);
                                System.out.println("END");
                                break;
                            case KEY_INSERT:
                                robo.keyPress(KeyEvent.VK_INSERT);
                                System.out.println("INSERT");
                                break;
                            case KEY_DELETE:
                                robo.keyPress(KeyEvent.VK_DELETE);
                                System.out.println("DELETE");
                                break;
                            case KEY_SPACE:
                                robo.keyPress(KeyEvent.VK_SPACE);
                                System.out.println("SPACE");
                                break;

                            //A- Z
                            case KEY_A:
                                robo.keyPress(KeyEvent.VK_A);
                                System.out.println("page down");
                                break;
                            case KEY_B:
                                robo.keyPress(KeyEvent.VK_B);
                                System.out.println("page down");
                                break;
                            case KEY_C:
                                robo.keyPress(KeyEvent.VK_C);
                                System.out.println("page down");
                                break;
                            case KEY_D:
                                robo.keyPress(KeyEvent.VK_D);
                                System.out.println("page down");
                                break;
                            case KEY_E:
                                robo.keyPress(KeyEvent.VK_E);
                                System.out.println("page down");
                                break;
                            case KEY_F:
                                robo.keyPress(KeyEvent.VK_F);
                                System.out.println("page down");
                                break;
                            case KEY_G:
                                robo.keyPress(KeyEvent.VK_G);
                                System.out.println("page down");
                                break;
                            case KEY_H:
                                robo.keyPress(KeyEvent.VK_H);
                                System.out.println("page down");
                                break;
                            case KEY_I:
                                robo.keyPress(KeyEvent.VK_I);
                                System.out.println("page down");
                                break;
                            case KEY_J:
                                robo.keyPress(KeyEvent.VK_J);
                                System.out.println("page down");
                                break;
                            case KEY_K:
                                robo.keyPress(KeyEvent.VK_K);
                                System.out.println("page down");
                                break;
                            case KEY_L:
                                robo.keyPress(KeyEvent.VK_L);
                                System.out.println("page down");
                                break;
                            case KEY_M:
                                robo.keyPress(KeyEvent.VK_M);
                                System.out.println("page down");
                                break;
                            case KEY_N:
                                robo.keyPress(KeyEvent.VK_N);
                                System.out.println("page down");
                                break;
                            case KEY_O:
                                robo.keyPress(KeyEvent.VK_O);
                                System.out.println("page down");
                                break;
                            case KEY_P:
                                robo.keyPress(KeyEvent.VK_P);
                                System.out.println("page down");
                                break;
                            case KEY_Q:
                                robo.keyPress(KeyEvent.VK_Q);
                                System.out.println("page down");
                                break;
                            case KEY_R:
                                robo.keyPress(KeyEvent.VK_R);
                                System.out.println("page down");
                                break;
                            case KEY_S:
                                robo.keyPress(KeyEvent.VK_S);
                                System.out.println("page down");
                                break;
                            case KEY_T:
                                robo.keyPress(KeyEvent.VK_T);
                                System.out.println("page down");
                                break;
                            case KEY_U:
                                robo.keyPress(KeyEvent.VK_U);
                                System.out.println("page down");
                                break;
                            case KEY_V:
                                robo.keyPress(KeyEvent.VK_V);
                                System.out.println("page down");
                                break;
                            case KEY_W:
                                robo.keyPress(KeyEvent.VK_W);
                                System.out.println("page down");
                                break;
                            case KEY_X:
                                robo.keyPress(KeyEvent.VK_X);
                                System.out.println("page down");
                                break;
                            case KEY_Y:
                                robo.keyPress(KeyEvent.VK_Y);
                                System.out.println("page down");
                                break;
                            case KEY_Z:
                                robo.keyPress(KeyEvent.VK_Z);
                                System.out.println("page down");
                                break;

                        }//switchends

                    }

                }
            }//while ends
        } catch (IOException ex) {
            Logger.getLogger(ProcessConnectionThread.class.getName()).log(Level.SEVERE, null, ex);
        } catch (AWTException ex) {
            Logger.getLogger(ProcessConnectionThread.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public int getx(String s) {
        int x = 0;
        int i = 0;
        if (s.charAt(0) != '+') {
            i = 1;
        }
        while (s.charAt(i) != '.') {
            x *= 10;
            x += (int) s.charAt(0);
        }
        if (s.charAt(0) == '-') {
            x *= (-1);
        }
        return x;
    }

    public int gety(String s) {
        int y = 0;
        int i = 0;
        while (s.charAt(i) != '.') {
            i++;
        }
        i += 2;
        if (s.charAt(i) != '+') {
            i += 1;
        }
        while (s.charAt(i) != '.') {
            y *= 10;
            y += (int) s.charAt(0);
        }
        if (s.charAt(i) == '-') {
            y *= (-1);
        }
        return y;
    }

}
