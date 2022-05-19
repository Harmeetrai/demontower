/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.demontower;

/**
 * This class handles the functionality of the enemy entity
 * This extends Entity and implements MovableEntity
 * @author Jesse
 */

public interface Enemy
{
    /**
     * updates the enemy position by using path finding algorithm
     */
    public void move();

    /**
     * gets the enemy image
     */
    public void loadImage();

    /**
     * checks if the enemy is active
     * @return true if the enemy is active else false
     */
    
    public boolean isActive();



}
