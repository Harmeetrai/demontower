package com.demontower;

// ground (empty square)

import javax.swing.*;

/**
 * this class represents the squares that can be occupied by dynamic entities
 */

public class Ground extends Square{

    /**
     * constructor for this
     * @param x the x-coordinate for this
     * @param y the y-coordinate for this
     */
    
    public Ground(int x, int y) {
        super(x, y);
        image = new ImageIcon("demontower/src/main/resources/images/ground.png").getImage();
    }
}
