package com.demontower;

 // wall (non-walkable square)

import javax.swing.*;

/**
 * this class handles the barrier logic for the wall
 */

public class Wall extends Square{

    /**
     * creates a new wall at coordinates (x,y)
     * @param x the x-coordinate of the wall
     * @param y the y-coordinate of the wall
     */

    public Wall(int x, int y) {
        super(x, y);
        image=new ImageIcon("demontower/src/main/resources/images/wall.png").getImage();
        this.setWalkable(false);
    }
    
}
