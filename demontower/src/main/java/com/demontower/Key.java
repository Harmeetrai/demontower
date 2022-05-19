/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.demontower;

import javax.swing.*;

/**
 * This class serves as a normal reward for the player
 * @author Jesse
 */

public class Key extends Reward {
    private int score;

    /**
     * constructor for this
     * @param square the position for this
     */

    public Key(Square square) {
        super(square);
        image = new ImageIcon("demontower/src/main/resources/images/key.png").getImage();
        score = 1;
    }

    /**
     * gets the score value of this
     * @return the score point associated with this
     */

    public int getScore() {
        return score;
    }

    /**
     * updates the score value associated with this
     * @param score the new score value
     */

    public void setScore(int score) {
        this.score = score;
    }

    /**
     * adds the score to the player's score
     * @param score the score value
     */
    
    public void addScore(int score) {
        this.score += score;
    }
}
