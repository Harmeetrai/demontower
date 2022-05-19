package com.demontower;

import javax.swing.ImageIcon;

/**
 * Bonus Reward class
 * - Handles the bonus reward position and score
 * @author Harmeet
 */
public class Treasure extends Reward {
    private int score;

    /**
     * Constructor for the Bonus Reward class
     * @param square the position for this
     */
    
    public Treasure(Square square) {
        super(square);
        score = 1;
        image = new ImageIcon("demontower/src/main/resources/images/chest.png").getImage();
    }

    /**
     * Get score
     * @return int
     */
    public int getScore() {
        return score;
    }

    /**
     * Set score
     * @param score the score value for this
     */

    public void setScore(int score) {
        this.score = score;
    }

    /**
     * Add score
     * @param score the score-value to be added to the player's score
     */

    public void addScore(int score) {
        this.score += score;
    }
}
