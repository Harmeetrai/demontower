/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.demontower;

/**
 * This class handles the logic for normal rewards and bonus rewards in the game
 * 
 * @author Jesse
 */

public class Reward extends Entity {
    private boolean isActive;

    /**
     * creates a new Reward that occupies square
     * @param square the position of the reward
     */

    public Reward(Square square) {
        super(square);
        isActive = true;
    }

    /**
     * checks if the reward is active
     * @return true if the reward is active else false
     */

    public boolean isActive() {
        return isActive;
    }

    /**
     *  sets the active status of the reward 
     * @param active the flag that spacifies whether the reward is active or not
     */
    
    public void setActive(boolean active) {
        isActive = active;
    }
}
