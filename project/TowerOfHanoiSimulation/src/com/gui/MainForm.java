/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gui;

import com.model.pile.Pile;
import com.sun.tools.javac.Main;
import com.swing.Button;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Image;
import java.awt.TextArea;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;
import javax.swing.DefaultListModel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButton;
import javax.swing.plaf.basic.BasicBorders;

/**
 *
 * @author Trần Hữu Đang
 */
public class MainForm extends javax.swing.JFrame {

    List<Pile> list = new ArrayList<>();
    boolean runThread = false;
    int cA = 0, cB = 0, cC = 0;
    boolean done = false;
    String times, tenGiaiThuat;
    int soBuoc;

    public MainForm() {
        initComponents();
        coc1.setVisible(false);
        coc2.setVisible(false);
        coc3.setVisible(false);
        coc4.setVisible(false);
        coc5.setVisible(false);

        btnStop.setEnabled(false);
    }

    public MainForm(int soDia) {
        initComponents();
        coc1.setVisible(false);
        coc2.setVisible(false);
        coc3.setVisible(false);
        coc4.setVisible(false);
        coc5.setVisible(false);

        snTheNum.setValue(soDia);
        initPole((int) snTheNum.getValue());
        btnStop.setEnabled(false);
    }

    void setTimer() {
        double time = timeDone - timeStart;
        time = (double) Math.round(time * 100) / 100;

        this.times = String.valueOf(time);
    }

    double timeStart = 0, timeDone = 0;

    double getTimer() {
        times = "0";
        int p = java.time.LocalTime.now().getMinute() * 60;
        int s = java.time.LocalTime.now().getSecond();
        int nano = java.time.LocalTime.now().getNano();

        String temp = String.valueOf(p + s) + "." + String.valueOf(nano);

        double rate = Double.valueOf(temp);
        rate = (double) Math.round(rate * 100) / 100;

        return rate;
    }

    void initPole(int n) {
        switch (n) {
            case 0:
                coc1.setVisible(false);
                coc2.setVisible(false);
                coc3.setVisible(false);
                coc4.setVisible(false);
                coc5.setVisible(false);
                break;
            case 1:
                coc1.setVisible(false);
                coc2.setVisible(false);
                coc3.setVisible(false);
                coc4.setVisible(false);
                coc5.setVisible(true);
                break;
            case 2:
                coc1.setVisible(false);
                coc2.setVisible(false);
                coc3.setVisible(false);
                coc4.setVisible(true);
                coc5.setVisible(true);
                break;
            case 3:
                coc1.setVisible(false);
                coc2.setVisible(false);
                coc3.setVisible(true);
                coc4.setVisible(true);
                coc5.setVisible(true);
                break;
            case 4:
                coc1.setVisible(false);
                coc2.setVisible(true);
                coc3.setVisible(true);
                coc4.setVisible(true);
                coc5.setVisible(true);
                break;
            case 5:
                coc1.setVisible(true);
                coc2.setVisible(true);
                coc3.setVisible(true);
                coc4.setVisible(true);
                coc5.setVisible(true);
                break;

        }
        cA = n;
        cB = 0;
        cC = 0;
        getAllPile();

        /*
        System.out.println("\n\n==========================\n");
        for (Pile pile : list) {
            System.out.println("index[" + (pile.getIndex() - 1) + "]: Coc " + pile.getIndex() + " Ten Panel: " + pile.getPn().getName());
        }
         */
    }

    void getAllPile() {
        list.clear();
        int n = (int) snTheNum.getValue();
        switch (n) {
            case 0:

                break;
            case 1:
                list.add(new Pile(1, coc5));
                break;
            case 2:
                list.add(new Pile(1, coc4));
                list.add(new Pile(2, coc5));
                break;
            case 3:
                list.add(new Pile(1, coc3));
                list.add(new Pile(2, coc4));
                list.add(new Pile(3, coc5));
                break;
            case 4:
                list.add(new Pile(1, coc2));
                list.add(new Pile(2, coc3));
                list.add(new Pile(3, coc4));
                list.add(new Pile(4, coc5));
                break;
            case 5:
                list.add(new Pile(1, coc1));
                list.add(new Pile(2, coc2));
                list.add(new Pile(3, coc3));
                list.add(new Pile(4, coc4));
                list.add(new Pile(5, coc5));
                break;
        }
    }

    synchronized void toTop(JPanel pn, int tower) {
        new Thread(new Runnable() {
            @Override
            public synchronized void run() {
                //đi lên
                while (pn.getY() > 100) {
                    try {
                        pn.setLocation(pn.getX(), pn.getY() - 1);
                        Thread.sleep(3);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                toRight(pn, tower);
                if (tower == 2) {
                    cB--;
                } else if (tower == 3) {
                    cC--;
                } else {
                    cA--;
                }
            }
        }).start();

    }

    synchronized void toRight(JPanel pn, int tower) {
        new Thread(new Runnable() {
            @Override
            public synchronized void run() {
                int towerToX;
                if (tower == 2) {
                    towerToX = 460;
                } else if (tower == 3) {
                    towerToX = 800;
                } else {
                    towerToX = 110;
                }
                if (pn.getX() < towerToX) {
                    //qua phải
                    while (pn.getX() < towerToX) {
                        try {
                            pn.setLocation(pn.getX() + 1, pn.getY());
                            Thread.sleep(3);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                } else {
                    //qua trái
                    while (pn.getX() > towerToX) {
                        try {
                            pn.setLocation(pn.getX() - 1, pn.getY());
                            Thread.sleep(3);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
                toBottom(pn, tower);

            }
        }).start();
    }

    int curTower(int soDia) {

        if (soDia == 0) {
            return 330;
        }
        if (soDia == 1) {
            return 310;
        }
        if (soDia == 2) {
            return 290;
        }
        if (soDia == 3) {
            return 270;
        }
        if (soDia == 4) {
            return 250;
        }
        return 330;
    }

    synchronized void toBottom(JPanel pn, int tower) {
        new Thread(new Runnable() {
            @Override
            public synchronized void run() {
                int getAlignmentY;
                if (tower == 1) {
                    getAlignmentY = curTower(cA);
                    cA++;
                } else if (tower == 2) {
                    getAlignmentY = curTower(cB);
                    cB++;
                } else {
                    getAlignmentY = curTower(cC);
                    cC++;
                }

                //đi xuống
                while (pn.getY() < getAlignmentY) {
                    try {
                        pn.setLocation(pn.getX(), pn.getY() + 1);
                        Thread.sleep(3);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                runThread = false;
                System.out.println("false");

            }
        }).start();

    }

    synchronized void go(JPanel pn, int tower) {
        int flash = getFlash();
        int towerToX;
        if (tower == 2) {
            towerToX = 460;
        } else if (tower == 3) {
            towerToX = 800;
        } else {
            towerToX = 110;
        }
        if (pn.getX() != towerToX) {
            runThread = true;
            new Thread(new Runnable() {
                @Override
                public synchronized void run() {
                    if (pn.getX() == 110) {
                        cA--;
                    } else if (pn.getX() == 460) {
                        cB--;
                    } else {
                        cC--;
                    }

                    //đi lên
                    while (pn.getY() > 100) {
                        try {
                            pn.setLocation(pn.getX(), pn.getY() - 1);
                            Thread.sleep(flash);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }

                    int towerToX;
                    if (tower == 2) {
                        towerToX = 460;
                    } else if (tower == 3) {
                        towerToX = 800;
                    } else {
                        towerToX = 110;
                    }
                    if (pn.getX() < towerToX) {
                        //qua phải
                        while (pn.getX() < towerToX) {
                            try {
                                pn.setLocation(pn.getX() + 1, pn.getY());
                                Thread.sleep(flash);
                            } catch (InterruptedException ex) {
                                Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    } else {
                        //qua trái
                        while (pn.getX() > towerToX) {
                            try {
                                pn.setLocation(pn.getX() - 1, pn.getY());
                                Thread.sleep(flash);
                            } catch (InterruptedException ex) {
                                Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    }

                    int getAlignmentY;
                    if (tower == 1) {
                        getAlignmentY = curTower(cA);
                        cA++;
                    } else if (tower == 2) {
                        getAlignmentY = curTower(cB);
                        cB++;
                    } else {
                        getAlignmentY = curTower(cC);
                        cC++;
                    }
                    //đi xuống
                    while (pn.getY() < getAlignmentY) {

                        try {
                            pn.setLocation(pn.getX(), pn.getY() + 1);
                            Thread.sleep(flash);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    runThread = false;
                    soBuoc++;
                }

            }).start();

        }
    }

    synchronized void shift(JPanel pn, int tower) {
        int towerToX;
        if (tower == 2) {
            towerToX = 460;
        } else if (tower == 3) {
            towerToX = 800;
        } else {
            towerToX = 110;
        }
        if (pn.getX() != towerToX) {
            runThread = true;
            toTop(pn, tower);

        }
    }

    synchronized void chuyenDia(int n, char a, char b) {
        System.out.printf("Chuyen dia thu %d tu coc %c sang coc %c\n", n, a, b);

        int to = b == 'B' ? 2 : 3;

        JPanel pnT = list.get(n - 1).getPn();

        go(pnT, to);
    }

    void towerHanoi(int n, char a, char b, char c) {
        if (n == 1) {
            chuyenDia(1, a, c);
        } else {
            towerHanoi(n - 1, a, c, b);
            chuyenDia(n, a, c);
            towerHanoi(n - 1, b, a, c);
        }
    }

    void chuyen(int n) {
        JPanel pnT = list.get(n - 1).getPn();

        go(pnT, 3);
    }

    void done() {
        btnStart.setEnabled(true);
        btnStop.setEnabled(false);
        sdFlash.setEnabled(true);
        snTheNum.setEnabled(true);
        done = true;

        timeDone = getTimer();
        setTimer();
        if (!times.equals("0")) {
            new Complate(this, true, rdoDeQuy.isSelected() ? "Đệ quy" : "AKT", times + "s", soBuoc).setVisible(true);
            soBuoc = 0;
        }

    }

    void start() {
        btnStart.setEnabled(false);
        sdFlash.setEnabled(false);
        snTheNum.setEnabled(false);
        btnStop.setEnabled(true);
        done = false;

        timeStart = getTimer();
    }

    void baiToan1Dia() {
        chuyenDia(1, 'A', 'C');
        new Thread(new Runnable() {
            @Override
            public void run() {

                go(coc5, 3);
                while (true) {
                    if (!runThread) {
                        break;
                    }
                    System.out.print("");
                }
                done();
            }
        }).start();
    }

    void baiToan2Dia() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                go(coc4, 2);
                while (true) {
                    if (!runThread) {
                        System.out.println("a");
                        break;
                    }
                    System.out.print("");
                }
                go(coc5, 3);
                while (true) {
                    if (!runThread) {
                        break;
                    }
                    System.out.print("");
                }
                go(coc4, 3);
                while (true) {
                    if (!runThread) {
                        break;
                    }
                    System.out.print("");
                }
                done();
            }
        }).start();
    }

    void baiToan3Dia() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                go(coc3, 3);
                while (true) {
                    if (!runThread) {
                        System.out.println("a");
                        break;
                    }
                    System.out.print("");
                }
                go(coc4, 2);
                while (true) {
                    if (!runThread) {
                        System.out.println("a");
                        break;
                    }
                    System.out.print("");
                }
                go(coc3, 2);
                while (true) {
                    if (!runThread) {
                        System.out.println("a");
                        break;
                    }
                    System.out.print("");
                }
                go(coc5, 3);
                while (true) {
                    if (!runThread) {
                        System.out.println("a");
                        break;
                    }
                    System.out.print("");
                }
                go(coc3, 1);
                while (true) {
                    if (!runThread) {
                        System.out.println("a");
                        break;
                    }
                    System.out.print("");
                }
                go(coc4, 3);
                while (true) {
                    if (!runThread) {
                        System.out.println("a");
                        break;
                    }
                    System.out.print("");
                }
                go(coc3, 3);
                while (true) {
                    if (!runThread) {
                        System.out.println("a");
                        break;
                    }
                    System.out.print("");
                }
                done();
            }
        }).start();
    }

    void baiToan4Dia() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                go(coc2, 2);
                while (true) {
                    if (!runThread) {
                        break;
                    }
                    System.out.print("");
                }
                go(coc3, 3);
                while (true) {
                    if (!runThread) {
                        break;
                    }
                    System.out.print("");
                }
                go(coc2, 3);
                while (true) {
                    if (!runThread) {
                        break;
                    }
                    System.out.print("");
                }
                go(coc4, 2);
                while (true) {
                    if (!runThread) {
                        break;
                    }
                    System.out.print("");
                }
                go(coc2, 1);
                while (true) {
                    if (!runThread) {
                        break;
                    }
                    System.out.print("");
                }
                go(coc3, 2);
                while (true) {
                    if (!runThread) {
                        break;
                    }
                    System.out.print("");
                }
                go(coc2, 2);
                while (true) {
                    if (!runThread) {
                        break;
                    }
                    System.out.print("");
                }
                go(coc5, 3);
                while (true) {
                    if (!runThread) {
                        break;
                    }
                    System.out.print("");
                }
                go(coc2, 3);
                while (true) {
                    if (!runThread) {
                        break;
                    }
                    System.out.print("");
                }
                go(coc3, 1);
                while (true) {
                    if (!runThread) {
                        break;
                    }
                    System.out.print("");
                }
                go(coc2, 1);
                while (true) {
                    if (!runThread) {
                        break;
                    }
                    System.out.print("");
                }
                go(coc4, 3);
                while (true) {
                    if (!runThread) {
                        break;
                    }
                    System.out.print("");
                }
                go(coc2, 2);
                while (true) {
                    if (!runThread) {
                        break;
                    }
                    System.out.print("");
                }
                go(coc3, 3);
                while (true) {
                    if (!runThread) {
                        break;
                    }
                    System.out.print("");
                }
                go(coc2, 3);
                while (true) {
                    if (!runThread) {
                        break;
                    }
                    System.out.print("");
                }
                done();
            }
        }).start();
    }

    void baiToan5Dia() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                go(coc1, 3);
                while (true) {
                    if (!runThread) {
                        break;
                    }
                    System.out.print("");
                }
                go(coc2, 2);
                while (true) {
                    if (!runThread) {
                        break;
                    }
                    System.out.print("");
                }
                go(coc1, 2);
                while (true) {
                    if (!runThread) {
                        break;
                    }
                    System.out.print("");
                }
                go(coc3, 3);
                while (true) {
                    if (!runThread) {
                        break;
                    }
                    System.out.print("");
                }
                go(coc1, 1);
                while (true) {
                    if (!runThread) {
                        break;
                    }
                    System.out.print("");
                }
                go(coc2, 3);
                while (true) {
                    if (!runThread) {
                        break;
                    }
                    System.out.print("");
                }
                go(coc1, 3);
                while (true) {
                    if (!runThread) {
                        break;
                    }
                    System.out.print("");
                }
                go(coc4, 2);
                while (true) {
                    if (!runThread) {
                        break;
                    }
                    System.out.print("");
                }
                go(coc1, 2);
                while (true) {
                    if (!runThread) {
                        break;
                    }
                    System.out.print("");
                }
                go(coc2, 1);
                while (true) {
                    if (!runThread) {
                        break;
                    }
                    System.out.print("");
                }
                go(coc1, 1);
                while (true) {
                    if (!runThread) {
                        break;
                    }
                    System.out.print("");
                }
                go(coc3, 2);
                while (true) {
                    if (!runThread) {
                        break;
                    }
                    System.out.print("");
                }
                go(coc1, 3);
                while (true) {
                    if (!runThread) {
                        break;
                    }
                    System.out.print("");
                }
                go(coc2, 2);
                while (true) {
                    if (!runThread) {
                        break;
                    }
                    System.out.print("");
                }
                go(coc1, 2);
                while (true) {
                    if (!runThread) {
                        break;
                    }
                    System.out.print("");
                }
                go(coc5, 3);
                while (true) {
                    if (!runThread) {
                        break;
                    }
                    System.out.print("");
                }
                go(coc1, 1);
                while (true) {
                    if (!runThread) {
                        break;
                    }
                    System.out.print("");
                }
                go(coc2, 3);
                while (true) {
                    if (!runThread) {
                        break;
                    }
                    System.out.print("");
                }
                go(coc1, 3);
                while (true) {
                    if (!runThread) {
                        break;
                    }
                    System.out.print("");
                }
                go(coc3, 1);
                while (true) {
                    if (!runThread) {
                        break;
                    }
                    System.out.print("");
                }
                go(coc1, 2);
                while (true) {
                    if (!runThread) {
                        break;
                    }
                    System.out.print("");
                }
                go(coc2, 1);
                while (true) {
                    if (!runThread) {
                        break;
                    }
                    System.out.print("");
                }
                go(coc1, 1);
                while (true) {
                    if (!runThread) {
                        break;
                    }
                    System.out.print("");
                }
                go(coc4, 3);
                while (true) {
                    if (!runThread) {
                        break;
                    }
                    System.out.print("");
                }
                go(coc1, 3);
                while (true) {
                    if (!runThread) {
                        break;
                    }
                    System.out.print("");
                }
                go(coc2, 2);
                while (true) {
                    if (!runThread) {
                        break;
                    }
                    System.out.print("");
                }
                go(coc1, 2);
                while (true) {
                    if (!runThread) {
                        break;
                    }
                    System.out.print("");
                }
                go(coc3, 3);
                while (true) {
                    if (!runThread) {
                        break;
                    }
                    System.out.print("");
                }
                go(coc1, 1);
                while (true) {
                    if (!runThread) {
                        break;
                    }
                    System.out.print("");
                }
                go(coc2, 3);
                while (true) {
                    if (!runThread) {
                        break;
                    }
                    System.out.print("");
                }
                go(coc1, 3);
                while (true) {
                    if (!runThread) {
                        break;
                    }
                    System.out.print("");
                }
                done();
            }
        }).start();
    }

    int getFlash() {
        if (sdFlash.getValue() == 10) {
            return 4;
        }
        if (sdFlash.getValue() == 20) {
            return 3;
        }
        if (sdFlash.getValue() == 30) {
            return 2;
        }
        if (sdFlash.getValue() == 40) {
            return 1;
        }
        if (sdFlash.getValue() == 50) {
            return 0;
        }
        return 3;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnGAl = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        pnManHinhMoPhong = new javax.swing.JPanel();
        coc3 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        coc4 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        coc5 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        coc2 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        coc1 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        lblInfor = new javax.swing.JLabel();
        lblTittle = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        snTheNum = new javax.swing.JSpinner();
        jLabel8 = new javax.swing.JLabel();
        sdFlash = new javax.swing.JSlider();
        jLabel16 = new javax.swing.JLabel();
        btnStop = new com.swing.Button();
        btnStart = new com.swing.Button();
        jLabel17 = new javax.swing.JLabel();
        rdoDeQuy = new javax.swing.JRadioButton();
        rdoAKT = new javax.swing.JRadioButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Phần mềm mô phỏng bài toán tháp Hà Nội");
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(244, 244, 244));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnManHinhMoPhong.setBackground(new java.awt.Color(255, 255, 255));
        pnManHinhMoPhong.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                pnManHinhMoPhongMouseEntered(evt);
            }
        });
        pnManHinhMoPhong.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        coc3.setBackground(new java.awt.Color(255, 255, 255));
        coc3.setName("DIA 3"); // NOI18N

        jLabel12.setBackground(new java.awt.Color(153, 153, 255));
        jLabel12.setToolTipText("");
        jLabel12.setOpaque(true);

        javax.swing.GroupLayout coc3Layout = new javax.swing.GroupLayout(coc3);
        coc3.setLayout(coc3Layout);
        coc3Layout.setHorizontalGroup(
            coc3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(coc3Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
        );
        coc3Layout.setVerticalGroup(
            coc3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pnManHinhMoPhong.add(coc3, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 280, 245, -1));

        coc4.setBackground(new java.awt.Color(255, 255, 255));
        coc4.setName("DIA 4"); // NOI18N

        jLabel10.setBackground(new java.awt.Color(0, 153, 204));
        jLabel10.setToolTipText("");
        jLabel10.setOpaque(true);

        javax.swing.GroupLayout coc4Layout = new javax.swing.GroupLayout(coc4);
        coc4.setLayout(coc4Layout);
        coc4Layout.setHorizontalGroup(
            coc4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(coc4Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(16, Short.MAX_VALUE))
        );
        coc4Layout.setVerticalGroup(
            coc4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pnManHinhMoPhong.add(coc4, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 300, 245, -1));

        coc5.setBackground(new java.awt.Color(255, 255, 255));
        coc5.setName("DIA 5"); // NOI18N
        coc5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel11.setBackground(new java.awt.Color(0, 153, 153));
        jLabel11.setToolTipText("");
        jLabel11.setOpaque(true);
        coc5.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(2, 0, 240, 20));

        pnManHinhMoPhong.add(coc5, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 320, 245, 20));

        coc2.setBackground(new java.awt.Color(255, 255, 255));
        coc2.setName("DIA 2"); // NOI18N

        jLabel13.setBackground(new java.awt.Color(0, 153, 51));
        jLabel13.setToolTipText("");
        jLabel13.setOpaque(true);

        javax.swing.GroupLayout coc2Layout = new javax.swing.GroupLayout(coc2);
        coc2.setLayout(coc2Layout);
        coc2Layout.setHorizontalGroup(
            coc2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(coc2Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(50, Short.MAX_VALUE))
        );
        coc2Layout.setVerticalGroup(
            coc2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pnManHinhMoPhong.add(coc2, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 260, 245, -1));

        coc1.setBackground(new java.awt.Color(255, 255, 255));
        coc1.setName("DIA 1"); // NOI18N

        jLabel9.setBackground(new java.awt.Color(153, 153, 153));
        jLabel9.setToolTipText("");
        jLabel9.setOpaque(true);

        javax.swing.GroupLayout coc1Layout = new javax.swing.GroupLayout(coc1);
        coc1.setLayout(coc1Layout);
        coc1Layout.setHorizontalGroup(
            coc1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(coc1Layout.createSequentialGroup()
                .addGap(65, 65, 65)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(65, Short.MAX_VALUE))
        );
        coc1Layout.setVerticalGroup(
            coc1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pnManHinhMoPhong.add(coc1, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 240, 245, -1));

        lblInfor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/library/icon/businessman.png"))); // NOI18N
        lblInfor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblInforMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblInforMouseEntered(evt);
            }
        });
        pnManHinhMoPhong.add(lblInfor, new org.netbeans.lib.awtextra.AbsoluteConstraints(1110, 10, 70, 60));

        lblTittle.setFont(new java.awt.Font("Tahoma", 1, 28)); // NOI18N
        lblTittle.setForeground(new java.awt.Color(0, 153, 153));
        lblTittle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTittle.setText("MÔ PHỎNG BÀI TOÁN THÁP HÀ NỘI");
        pnManHinhMoPhong.add(lblTittle, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1180, 80));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("C");
        pnManHinhMoPhong.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 340, 245, 80));

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("A");
        pnManHinhMoPhong.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 350, 245, 70));

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText("B");
        pnManHinhMoPhong.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 350, 245, 70));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 0, 0, new java.awt.Color(0, 0, 0)));

        snTheNum.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        snTheNum.setModel(new javax.swing.SpinnerNumberModel(0, 0, 5, 1));
        snTheNum.setBorder(null);
        snTheNum.setOpaque(false);
        snTheNum.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                snTheNumStateChanged(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setText("Số tầng tháp:");

        sdFlash.setBackground(new java.awt.Color(255, 255, 255));
        sdFlash.setMajorTickSpacing(10);
        sdFlash.setMaximum(50);
        sdFlash.setMinimum(10);
        sdFlash.setMinorTickSpacing(10);
        sdFlash.setPaintLabels(true);
        sdFlash.setValue(30);
        sdFlash.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                sdFlashMouseEntered(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel16.setText("Tốc độ:");
        jLabel16.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        btnStop.setBackground(new java.awt.Color(204, 204, 255));
        btnStop.setText("Dừng");
        btnStop.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnStop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStopActionPerformed(evt);
            }
        });

        btnStart.setBackground(new java.awt.Color(153, 204, 255));
        btnStart.setText("Bắt đầu giải");
        btnStart.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnStart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStartActionPerformed(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel17.setText("Phương pháp:");
        jLabel17.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        rdoDeQuy.setBackground(new java.awt.Color(255, 255, 255));
        btnGAl.add(rdoDeQuy);
        rdoDeQuy.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        rdoDeQuy.setSelected(true);
        rdoDeQuy.setText("     Đệ quy");

        rdoAKT.setBackground(new java.awt.Color(255, 255, 255));
        btnGAl.add(rdoAKT);
        rdoAKT.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        rdoAKT.setText("     AKT");
        rdoAKT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoAKTActionPerformed(evt);
            }
        });

        jLabel1.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 0, 1, new java.awt.Color(0, 0, 0)));

        jLabel2.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 0, 1, new java.awt.Color(0, 0, 0)));

        jLabel3.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 0, 1, new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(snTheNum, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(76, 76, 76)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(sdFlash, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(rdoAKT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(rdoDeQuy, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(30, 30, 30)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnStart, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnStop, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(84, 84, 84))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(snTheNum, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(31, 31, 31))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(rdoDeQuy)
                                    .addGap(18, 18, 18)
                                    .addComponent(rdoAKT))
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(sdFlash, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                    .addGap(9, 9, 9)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(btnStart, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnStop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())))
        );

        pnManHinhMoPhong.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 499, 1140, -1));

        jLabel23.setBackground(new java.awt.Color(204, 153, 0));
        jLabel23.setToolTipText("");
        jLabel23.setOpaque(true);
        pnManHinhMoPhong.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 140, 5, 205));

        jLabel22.setBackground(new java.awt.Color(204, 153, 0));
        jLabel22.setToolTipText("");
        jLabel22.setOpaque(true);
        pnManHinhMoPhong.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 340, 245, 5));

        jLabel24.setBackground(new java.awt.Color(204, 153, 0));
        jLabel24.setToolTipText("");
        jLabel24.setOpaque(true);
        pnManHinhMoPhong.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 140, 5, 205));

        jLabel25.setBackground(new java.awt.Color(204, 153, 0));
        jLabel25.setToolTipText("");
        jLabel25.setOpaque(true);
        pnManHinhMoPhong.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 340, 245, 5));

        jLabel26.setBackground(new java.awt.Color(204, 153, 0));
        jLabel26.setToolTipText("");
        jLabel26.setOpaque(true);
        pnManHinhMoPhong.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 340, 245, 5));

        jLabel27.setBackground(new java.awt.Color(204, 153, 0));
        jLabel27.setToolTipText("");
        jLabel27.setOpaque(true);
        pnManHinhMoPhong.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 140, 5, 205));

        jPanel1.add(pnManHinhMoPhong, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1180, 650));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents


    private void lblInforMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblInforMouseClicked
        new Infor(this, true).setVisible(true);
    }//GEN-LAST:event_lblInforMouseClicked

    private void lblInforMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblInforMouseEntered

    }//GEN-LAST:event_lblInforMouseEntered

    private void pnManHinhMoPhongMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnManHinhMoPhongMouseEntered

    }//GEN-LAST:event_pnManHinhMoPhongMouseEntered

    private void btnStartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStartActionPerformed
        int n = (int) snTheNum.getValue();
        start();
        if (n == 1)
            baiToan1Dia();
        else if (n == 2)
            baiToan2Dia();
        else if (n == 3)
            baiToan3Dia();
        else if (n == 4)
            baiToan4Dia();
        else if (n == 5)
            baiToan5Dia();
    }//GEN-LAST:event_btnStartActionPerformed

    private void snTheNumStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_snTheNumStateChanged
        initPole((int) snTheNum.getValue());
    }//GEN-LAST:event_snTheNumStateChanged

    private void sdFlashMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sdFlashMouseEntered

    }//GEN-LAST:event_sdFlashMouseEntered

    private void btnStopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStopActionPerformed
        new MainForm((int) snTheNum.getValue()).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnStopActionPerformed

    private void rdoAKTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoAKTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdoAKTActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup btnGAl;
    private com.swing.Button btnStart;
    private com.swing.Button btnStop;
    private javax.swing.JPanel coc1;
    private javax.swing.JPanel coc2;
    private javax.swing.JPanel coc3;
    private javax.swing.JPanel coc4;
    private javax.swing.JPanel coc5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel lblInfor;
    private javax.swing.JLabel lblTittle;
    private javax.swing.JPanel pnManHinhMoPhong;
    private javax.swing.JRadioButton rdoAKT;
    private javax.swing.JRadioButton rdoDeQuy;
    private javax.swing.JSlider sdFlash;
    private javax.swing.JSpinner snTheNum;
    // End of variables declaration//GEN-END:variables
}
