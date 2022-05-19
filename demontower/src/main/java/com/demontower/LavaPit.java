/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.demontower;

import javax.swing.*;

/**
 * This class handles the trap logic of the game
 * @author Huntley
 */

public class LavaPit extends Entity
{
    /**
     * constructor for this
     * @param square the position for this
     */
    
    public LavaPit(Square square) {
        super(square);
        image=new ImageIcon("demontower/src/main/resources/images/lava.png").getImage();
    }
    
}
