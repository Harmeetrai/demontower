package com.demontower;

/**
 * This interface handles the entities' collisions in the game
 */
public interface Collision {

    /**
     * 
     * @param <C1>
     * @param <C2>
     * @param entity1
     * @param entity2
     */
    public <C1 extends Entity, C2 extends Entity> void collide(C1 entity1, C2 entity2);
}
