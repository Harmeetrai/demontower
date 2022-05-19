package com.demontower;

import java.awt.*;

import javax.swing.JFrame;

/**
 * This class controls the UI for the game
 */

public class DemonTower extends JFrame {

    public DemonTower() {
        initUI();
    }

    private void initUI() {
        setSize(720, 560);

        // made changes to this code... GameController() was made private
        add(GameController.objectify());

        setTitle("DemonTower");

        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setLocationRelativeTo(null);

        setVisible(true);

    }

    /**
     * main
     * @param args
     */
    
    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {

                        DemonTower ex = new DemonTower();//  allow system to execute future events before executing all the previous one

                        ex.setVisible(true);
                    }
        }
        );
    }
}
