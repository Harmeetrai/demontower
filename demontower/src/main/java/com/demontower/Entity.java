/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.demontower;


import java.awt.*;

/**
 * This class abstracts all the common functionalities of entities in the game
 */

public class Entity {

    private int xIndex;
    private int yIndex;
    protected Image image;

    /**
     * constructor for this
     */

    public Entity()
    {
        
    }



    private Square square;

    private Direction direction;

    /**
     * gets the direction of this
     * @return the Direction North, South, West, or East of the entity
     */

    public Direction getDirection() {
        return direction;
    }

    /**
     * gets the image of this
     * @return the image of the entity to be diaplayed in the UI
     */

    public Image getImage() {
        return image;
    }

    /**
     * updates the image of this
     * @param image the new image
     */

    public void setImage(Image image) {
        this.image = image;
    }

    /**
     * constructor for this
     * @param square the position for this
     */

    public Entity(Square square) {
        this.square = square;
    }

    /**
     * updates the direction of this
     * @param direction the new Direction North, South, West, or East
     * @see Direction
     */

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    /**
     * gets the position of this
     * @return the position for this
     */

    public Square getSquare() {
        return square;
    }

    /**
     * updates the position for this 
     * @param square the new position
     */

    public void setSquare(Square square) {
        this.square = square;
    }

    /**
     * updates the Xth index of this in the grid
     * @param xIndex the new Xth index
     */

    public void setXIndex(int xIndex)
    {
        this.xIndex = xIndex;
    }

    /**
     * updates the Yth index of this in the grid
     * @param yIndex the new Yth index
     */

    public void setYIndex(int yIndex)
    {
        this.yIndex = yIndex;
    }

    /**
     * gets the Xth index of this in the grid
     * @return the xth index of this
     */
    public int getXIndex()
    {
        return xIndex;
    }

    /**
     * gets the Yth index of this in the grid
     * @return the yth index
     */
    
    public int getYIndex()
    {
        return yIndex;
    }


}
