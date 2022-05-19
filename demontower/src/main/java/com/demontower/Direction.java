package com.demontower;

/**
 * This is an enum for the Direction of navigation
 */

public enum Direction {

    NORTH(0, -1),
    SOUTH(0, 1),
    WEST(-1, 0),
    EAST(1, 0);

    private final int x;
    private final int y;

    /**
     * constructor for this enum
     * @param x the x-coordinate of the representation for West or East
     * @param y the y-coordinate of the represntation for North or South
     */

    Direction(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * gets the x-coordinate of this
     * @return the x-value of this
     */

    public int getX() {
        return x;
    }

    /**
     * the y-coordinate of this
     * @return the y-value of this
     */
    
    public int getY() {
        return y;
    }

}
