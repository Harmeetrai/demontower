package com.demontower;

import javax.swing.*;

/**
 * this specifies the square as a stair that is used to determine the ebnd of the game
 */

public class Stair extends Square {

    /**
     * creates a Stair object at (x,y)
     * @param x the x-coordinate of the Stair
     * @param y the y-coordinate of the Stair
     */
    
    public Stair(int x, int y) {
        super(x, y);
        setStair();
        image = new ImageIcon("demontower/src/main/resources/images/stair.png").getImage();
    }

}
