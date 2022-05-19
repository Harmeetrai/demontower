package com.demontower;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.demontower.*;

public class PlayerTest {
    Square square1 ;
    Square square2 ;
    Player player ;
    Demon demon ;
    LavaPit lavapit;
    Key key;
    Treasure treasure;
    MedKit medkit;
    PlayerCollision playerCollision;
    // test if player is alive
    @Test
    public void testPlayerIsAlive() {
        Player player = new Player(); 
        player.setAlive(true);
        assertTrue(player.isAlive());
    }

    // test if player is dead
    @Test
    public void testPlayerIsDead() {
        Player player = new Player(); 
        player.setAlive(false);
        assertTrue(!player.isAlive());
    }

    // check if player is alive after taking damage
    @Test
    public void testPlayerIsAliveAfterTakingDamage() {
        Player player = new Player(); 
        player.setAlive(true);
        player.removeHealth(1);
        assertTrue(player.isAlive());
    }

    // kill player
    @Test
    public void testKillPlayer() {
        Player player = new Player(); 
        player.setAlive(true);
        player.removeHealth(3);
        assertTrue(!player.isAlive());
    }

    // add health
    @Test
    public void testAddHealth() {
        Player player = new Player(); 
        player.setAlive(true);
        player.addHealth(1);
        assertTrue(player.getHealth() == 4);
    }

    // remove health
    @Test
    public void testRemoveHealth() {
        Player player = new Player(); 
        player.setAlive(true);
        player.removeHealth(1);
        assertTrue(player.getHealth() == 2);
    }

    // set alive status
    @Test
    public void testSetAlive() {
        Player player = new Player(); 
        player.setAlive(true);
        player.setAlive(false);
        assertTrue(!player.isAlive());
    }

    // add score
    @Test
    public void testAddScore() {
        Player player = new Player(); 
        player.setAlive(true);
        player.addScore(1);
        assertTrue(player.getScore() == 1);
    }

    // add keycount
    @Test
    public void testAddKeyCount() {
        Player player = new Player(); 
        player.setAlive(true);
        player.addKeyCount(1);
        assertTrue(player.getKeyCount() == 1);
    }

    // test player collision with demon
    @Test
    public void testPlayerCollisionWithDemon() {
        square1 = new Square(1,1);
        square2 = new Square(2,2);
        player = new Player();
        player.setAlive(true);
        player.setPosition(square1);
        square1.setEntity(player);
        demon = new Demon(square2);
        square2.setEntity(demon);
        playerCollision = new PlayerCollision();
        playerCollision.collide(player, demon);
        assertTrue(player.getHealth() == 2);
    }

    // test player collision with lava pit
    @Test
    public void testPlayerCollisionWithLavaPit() {
        square1 = new Square(1,1);
        square2 = new Square(2,2);
        player = new Player();
        player.setAlive(true);
        player.setPosition(square1);
        square1.setEntity(player);
        lavapit = new LavaPit(square2);
        square2.setEntity(lavapit);
        playerCollision = new PlayerCollision();
        playerCollision.collide(player, lavapit);
        assertTrue(player.getHealth() == 0);
    }

    // test player collision with key
    @Test
    public void testPlayerCollisionWithKey() {
        square1 = new Square(1,1);
        square2 = new Square(2,2);
        player = new Player();
        player.setAlive(true);
        player.setPosition(square1);
        square1.setEntity(player);
        key = new Key(square2);
        square2.setEntity(key);
        playerCollision = new PlayerCollision();
        playerCollision.collide(player, key);
        assertTrue(player.getKeyCount() == 1);
    }

    // test player collision with treasure
    @Test
    public void testPlayerCollisionWithTreasure() {
        square1 = new Square(1,1);
        square2 = new Square(2,2);
        player = new Player();
        player.setAlive(true);
        player.setPosition(square1);
        square1.setEntity(player);
        treasure = new Treasure(square2);
        square2.setEntity(treasure);
        playerCollision = new PlayerCollision();
        playerCollision.collide(player, treasure);
        assertTrue(player.getScore() == 1);
    }

    // test player collision with medkit
    @Test
    public void testPlayerCollisionWithMedKit() {
        square1 = new Square(1,1);
        square2 = new Square(2,2);
        player = new Player();
        player.setAlive(true);
        player.setPosition(square1);
        square1.setEntity(player);
        medkit = new MedKit(square2);
        square2.setEntity(medkit);
        playerCollision = new PlayerCollision();
        playerCollision.collide(player, medkit);
        assertTrue(player.getHealth() == 3);
    }
}
