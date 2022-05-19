package com.demontower;

/**
 * Player collision class handles the player's collision with the walls, enemies, and the exit
 * @author Harmeet
 */

public class PlayerCollision implements Collision {

    @Override
    public void collide(Entity entity1, Entity entity2) {
        // if entity1 is player
        if (entity2 instanceof Demon) {
            collidePlayerWithEnemy((Player) entity1, (Demon) entity2);
        }
        // if entity2 is lava pit
        else if (entity2 instanceof LavaPit) {
            collidePlayerWithLavaPit((Player) entity1, (LavaPit) entity2);
        }
        // if entity2 is key
        else if (entity2 instanceof Key) {
            collidePlayerWithKeyReward((Player) entity1, (Key) entity2);
        }
        // if entity2 is treasure
        else if (entity2 instanceof Treasure) {
            collidePlayerWithScoreReward((Player) entity1, (Treasure) entity2);
        }
        // if entity2 is health
        else if (entity2 instanceof MedKit) {
            collidePlayerWithHealthReward((Player) entity1, (MedKit) entity2);
        }
    }

    /**
     * collides player with enemy
     * @param player the player object 
     * @param enemy the enemy object
     */
    
    private void collidePlayerWithEnemy(Player player, Demon enemy) {
        // if enemy is trap enemy
        if (enemy instanceof Demon) {
            player.removeHealth(1);
            // if player is dead
            if (player.getHealth() <= 0) {
                player.setAlive(false);
            }
            //enemy was defeated
            enemy.getSquare().setEntity(null);
            enemy.isActive=false;
        }
    }

    /**
     * collide player with health reward
     * @param player
     * @param healthReward
     */
    private void collidePlayerWithHealthReward(Player player, MedKit healthReward) {
        // if player is alive and healthReward is Active
        if (player.isAlive()&& healthReward.isActive()) {
            // add health reward to player
            if(player.getHealth()<3)
                player.addHealth(1);
            // remove health reward from board
            healthReward.setActive(false);
        }
    }

    /**
     * collide player with key reward
     * @param player
     * @param keyReward
     */
    private void collidePlayerWithKeyReward(Player player, Key keyReward) {
        // if player is alive and key is active
        if (player.isAlive()&&keyReward.isActive()) {
            // add key reward to player
            player.addKeyCount(1);
            // remove key reward from board
            keyReward.setActive(false);
        }
    }

    /**
     * collide player with score reward
     * @param player
     * @param scoreReward
     */
    private void collidePlayerWithScoreReward(Player player, Treasure scoreReward) {
        // if player is alive and scoreReward is active
        if (player.isAlive()&&scoreReward.isActive()) {
            // add score reward to player
            player.addScore(scoreReward.getScore());
            // remove score reward from board
            scoreReward.setActive(false);
        }
    }

    /**
     * collide player with lava pit
     * @param player
     * @param lavaPit
     */
    private void collidePlayerWithLavaPit(Player player, LavaPit lavaPit) {
        // if player is alive
        if (player.isAlive()) {
            // remove health reward from player
            player.removeHealth(3);
            // if player is dead
            if (player.getHealth() <= 0) {
                player.setAlive(false);
            }
        } 
    }
}
