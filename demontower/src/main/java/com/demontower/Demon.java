/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.demontower;

import java.awt.*;
import javax.swing.*;

/**
 * This class is responsible for handling the logic to harm player
 * Demon is a movable enemy
 * @author Huntley
 */

public class Demon extends Entity implements Enemy
{
    /**
     * field for determinig if this is active
     */

    public boolean isActive=true;

    /**
     * construtor for this
     * @param square the position for this
     */

    // current square with smaller dist
    private Square closestSquare;
    public Demon(Square square) {
        super(square);
        loadImage();
        image=new ImageIcon("demontower/src/main/resources/images/enemy/enemy-down-1.png").getImage();
    }

    /**
     * gets the speed of the demon
     * @return the speed of the demon
     */

    public int getSpeed() {
        return speed;
    }

    /**
     * updates the speed of the enemy
     * @param speed the new speed
     */

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    @Override
    public boolean isActive() {
        return isActive;
    }

    //How fast Demon walking on the board
    private int speed = 1;

    Image upImages;
    Image downImages;
    Image leftImages;
    Image rightImages;

    Image currentAnim = null;

    // CalcDist function has been used for help enemy allocate player's position
    private double calcDist(int playerX,int playerY, int enemyX, int enemyY)
    {
        final int BOUND_X = 15;
        final int BOUND_Y = 15;

        if(enemyY > BOUND_X || enemyY < 0)
        {
            return Double.POSITIVE_INFINITY;
        }

        if(enemyX > BOUND_Y || enemyX < 0)
        {
            return Double.POSITIVE_INFINITY;
        }

        return Math.sqrt(Math.pow(enemyX - playerX, 2) + Math.pow(enemyY - playerY, 2));
    }

    /**
     * find next square the enemy will move to
     * @return
     */
    private Square getPositionClosestToPlayer()
    {
        // squares of gameController
        Square squares[][] = GameController.objectify().getBoard();
        // position of player
        Player player = Player.objectify();
        int playerY = player.getXIndex();
        int playerX = player.getYIndex();
        // position of enemy
        int enemyY = getYIndex();
        int enemyX = getXIndex();

        closestSquare=getSquare();
        // compute distance between player and enemy
        double smallDist = calcDist(playerX, playerY, enemyX, enemyY);
        // try to go left
        smallDist=next(squares,smallDist,Direction.WEST);
        // try to go up
        smallDist=next(squares,smallDist,Direction.NORTH);
        // try to go down
        smallDist=next(squares,smallDist,Direction.SOUTH);
        // try to go right
        smallDist=next(squares,smallDist,Direction.EAST);
        // update position of enemy
        setYIndex(closestSquare.getX());
        setXIndex(closestSquare.getY());
        // return next square the enemy will move to
        return closestSquare;

    }

    /**
     *
     * @param squares
     * @param y
     * @param x
     * @return
     */
    //Check if next square is walkable
    private boolean valid(Square[][] squares,int y,int x) {
        if(x<=0||x>=15||y<=0||y>=15){
            return false;
        }
        Square tmp=squares[y][x];
        if(tmp!=null&&tmp.isWalkable()&&!tmp.isOccupied()&&!tmp.isStair()){
            return true;
        }
        return false;
    }

    /**
     * update closestSquare and smallDist if square with the direction has smaller dist
     * @param squares game map
     * @param smallDist current small dist
     * @param direction current direction
     * @return
     */
    double next(Square[][] squares,double smallDist,Direction direction){
        // next position of enemy
        int enemyY = getYIndex()+direction.getY();
        int enemyX = getXIndex()+direction.getX();
        // next position of enemy is valid?
        if(!valid(squares,enemyY,enemyX)){
            return smallDist;
        }
        // position of player
        Player player = Player.objectify();
        int playerY = player.getXIndex();
        int playerX = player.getYIndex();
        // calculate distance
        double nextDist = calcDist( playerX,playerY, enemyX, enemyY);
        // update if current distance is smaller
        if (nextDist < smallDist) {
            // update smalldist
            smallDist=nextDist;
            // update closest square
            closestSquare = squares[enemyY][enemyX];
            // update direction
            setDirection(direction);
        }
        return smallDist;
    }

    @Override
    public void move()
    {
        this.getSquare().setEntity(null);
        Square square = getPositionClosestToPlayer();
        square.setEntity(this);
        setSquare(square);
        Player player=GameController.player;
        if(player.getYIndex()==square.getY()&&player.getXIndex()==square.getX()){
            GameController.collision.collide(player,this);
        }
    }

    /**
     * gets the images for this to be deiplayed in UI
     */

    public void loadImage() {

        upImages = new ImageIcon("demontower/src/main/resources/images/enemy/enemy-up-1.png").getImage();
        downImages = new ImageIcon("demontower/src/main/resources/images/enemy/enemy-down-1.png").getImage();
        leftImages = new ImageIcon("demontower/src/main/resources/images/enemy/enemy-left-1.png").getImage();
        rightImages = new ImageIcon("demontower/src/main/resources/images/enemy/enemy-right-1.png").getImage();

    }

    /**
     * updates the animation for this
     * @param anim the new image to be animated
     */

    public void setAnimation(Image anim) {

        currentAnim = anim;
        image=anim;

    }

    /**
     * updates the image according to the direction
     * @param direction the North, South, West, or East direction
     * @see Direction
     */

    public void setDirection(Direction direction) {
        switch (direction) {
            case NORTH:
                setAnimation(upImages);
                break;
            case SOUTH:
                setAnimation(downImages);
                break;
            case EAST:
                setAnimation(rightImages);
                break;
            case WEST:
                setAnimation(leftImages);
                break;
        }
    }


}
