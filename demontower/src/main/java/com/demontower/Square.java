package com.demontower;

import java.awt.*;
import java.util.List;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.Map;

/**
 * this class handles the logic for the position of entities on the map
 */

public class Square {

    // changed from an arraylist of entities to a singuar entity
    private Entity entity;
    private final Map<Direction, Square> neighbours;
    private final int x;
    private final int y;
    private boolean walkable;
    private boolean isStair = false;

    // list of entities
    private List<Entity> entities = new ArrayList<>();

    protected Image image;

    /**
     * gets the image of the entity 
     * @return an Image of the entity to be displayed on the screen
     */

    public Image getImage() {

        if(entity!=null&&(!(entity instanceof Reward)||((Reward)entity).isActive())){//display an entity, or a active reward
            return entity.getImage();
        }else{
            return image;
        }

    }

    /**
     * updates the image of the this square object to display the correct position of the entity
     * @param image
     */

    public void setImage(Image image) {
        this.image = image;
    }

    /**
     * creates a square object at coordinate (x,y)
     * @param x the x-coordinate of the square object
     * @param y the y-coordinate of the square object
     */

    public Square(int x, int y) {
        // removed arraylist initialization
        neighbours = new EnumMap<Direction, Square>(Direction.class);
        this.x = x;
        this.y = y;
        // defaulty inititalized to true (changed)
        this.walkable = true;
        this.entities = new ArrayList<>();
    }
    

    /**
     * get the x-coordinate of the square
     * @return the x-coordinate of the square
     */

    public int getX() {
        return x;
    }

    /**
     * get the y-coordinate of the square
     * @return the y-coordinate of the square
     */

    public int getY() {
        return y;
    }

    /**
     * get the square adjacent to this
     * @param direction the North,South,East, or West neighbour
     * @return the neighbouroing square
     */

    public Square getNeighbour(Direction direction) {
        return neighbours.get(direction);
    }

    /**
     * gets a list of entities
     * @return the list of entities that belongs to this square
     */

    public List<Entity> getEntities() {
        return entities;
    }

    /**
     * adds to the list of entities a new entity
     * @param entity the new entity to be added to the square
     */

    public void addEntity(Entity entity) {
        entities.add(entity);
    }

    /**
     * gets the entity occupying the square
     * @return the entity in the square
     */

    public Entity getEntity() {
        return entity;
    }

    /**
     * update the entity occupying this square
     * @param entity the new entity about to occupy this square
     */

    public void setEntity(Entity entity) {
        this.entity = entity;
    }


    /**
     * sets the neighbour in the given direction
     * @param direction the North, South, West, or East direction of the neighbour
     * @param neighbour the neighbouring square
     */

    public void setNeighbour(Direction direction, Square neighbour) {
        neighbours.put(direction, neighbour);
    }

    /**
     * checks if entity is allowed to move to this square
     * @return true if the entity is allowed to move to this square else false
     */

    public boolean isWalkable() {
        return walkable;
    }

    /**
     * updates the walkable status of the square
     * @param walkable the flag to determine if the square is walkable
     */

    public void setWalkable(boolean walkable) {
        this.walkable = walkable;
    }

    /**
     * check if the square is occupied by an entity
     * @return true if the square is occupied by an entity else false
     */

    public boolean isOccupied() {
        return entity != null;
    }

    /**
     * check if this square is occupied by a player
     * @return true if this square cpontains a player else false
     */

    public boolean isPlayer() 
    {
       return entity instanceof Player;
    }

    /**
     * check if the square has key in it
     * @return true if the square has key in it else false
     */
    
    public boolean hasKey() 
    {
       return entity instanceof Key;
    }

    /**
     * check if the square has reward in it
     * @return true if the square has reward in it else false
     */

    public boolean hasBonus() {
        return entity instanceof Treasure;
    }

    /**
     * check if the square has health in it
     * @return true if the square has health in it else false
     */

    public boolean hasHealth() {
       return entity instanceof MedKit;
    }

    /**
     * check if square is a stair
     * @return true if square is a stair else return false
     */

    public boolean isStair()
    {
        return this.isStair;
    }

    /**
     * updates the square to become a stair
     */

    public void setStair()
    {
        isStair = true;
    }

}
