/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.demontower;

import javax.swing.*;
import java.awt.*;

/**
 * This class handles the player logic
 * The logic are the movement, health, and score.
 *
 * @author Jesse
 */

public class Player extends Entity {
    private int health;
    private int score;
    private static Player uniqueInstance = null;
    private Square position;
    private int keyCount;
    public final static int PLAYER_HEALTH_MAX = 3;
    public final static Image UP_IMAGE=new ImageIcon("demontower/src/main/resources/images/player/player-up-1.png").getImage();
    public final static Image DOWN_IMAGE=new ImageIcon("demontower/src/main/resources/images/player/player-down-1.png").getImage();
    public final static Image LEFT_IMAGE=new ImageIcon("demontower/src/main/resources/images/player/player-left-1.png").getImage();
    public final static Image RIGHT_IMAGE=new ImageIcon("demontower/src/main/resources/images/player/player-right-1.png").getImage();

    /**
     * This method creates a new player
     */
    
    public Player() {
        health = PLAYER_HEALTH_MAX;
        score = 0;
        keyCount = 0;
        image=DOWN_IMAGE;
    }

    /**
     * get key count
     *
     * @return keyCount
     */
    public int getKeyCount() {
        return keyCount;
    }

    /**
     * gets the only instance of the player 
     * @return the Singleton instance of the player
     */

    public static Player objectify() {
        if (uniqueInstance == null) {
            uniqueInstance = new Player();
        }

        return uniqueInstance;
    }

    /**
     * gets a new instance of this for testing purposes
     * @return the instance of this
     */

    public static Player getNewPlayer()
    {
        uniqueInstance = new Player();
        return uniqueInstance;
    }

    /**
     * add key count
     *
     * @param keyCount
     */
    public void addKeyCount(int keyCount) {
        this.keyCount += keyCount;
    }

    /**
     * get health
     *
     * @return health
     */
    public int getHealth() {
        return health;
    }

    /**
     * set health
     *
     * @param health
     */
    public void setHealth(int health) {
        this.health = health;
    }

    /**
     * add health
     *
     * @param health
     */
    public void addHealth(int health) {
        this.health += health;
    }

    /**
     * remove health
     *
     * @param health
     */
    public void removeHealth(int health) {
        this.health -= health;
    }

    /**
     * is player alive
     *
     * @return true if player is alive
     */
    public boolean isAlive() {
        return health > 0;
    }

    /**
     * set alive status
     *
     * @param alive
     */
    public void setAlive(boolean alive) {
        if (alive) {
            health = 3;
        } else {
            health = 0;
        }
    }

    /**
     * get score
     *
     * @return score
     */
    public int getScore() {
        return score;
    }

    /**
     * set score
     *
     * @param score
     */
    public void setScore(int score) {
        this.score = score;
    }

    /**
     * add score
     *
     * @param score
     */
    public void addScore(int score) {
        this.score += score;
    }

    /**
     * gets the positon of this
     * @return the Square that this ocupies
     */

    public Square getPosition() {
        return position;
    }

    /**
     * updates this' position
     * @param square the new position for this
     */

    public void setPosition(Square square) {
        this.position = square;
    }

    /**
     * checks if all the keys are collected
     * @return true if all the keys are collected else false
     */

    public boolean areAllKeysCollected()
    {
        final int NUM_OF_KEYS = 6;
        return getKeyCount() == NUM_OF_KEYS;
    }

    /**
     * check if the position of this is equal to the position at (xIndex, yIndex)
     * @param xIndex the x-coordinate of the compared position
     * @param yIndex the y-0coordinate of the compared position
     * @return true if the position is equal else return false
     */

    public boolean isPositionEqual(int xIndex, int yIndex)
    {
        return getXIndex() == xIndex && getYIndex() == yIndex;
    }

    /**
     * gets the tips of the game
     * @return the String the representation of the tips for the game
     */

    public String [] getTips()
    {        
        final int NUM_OF_TIPS = 3;
        String [] tips = new String[NUM_OF_TIPS];

        tips[0] = "1.\tSurvive by avoiding enemies and collecting keys to unlock the next level";
        tips[1] = "2.\tCollect medkit to restore health";
        tips[2] = "3.\tAvoid the flaming lava pits";

        return tips;
    }
}
