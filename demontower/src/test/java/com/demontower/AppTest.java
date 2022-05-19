package com.demontower;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    // test if player is alive
    @Test
    public void testPlayerIsAlive()
    {
        Player player = new Player(); 
        player.setAlive(true);
        assertTrue(player.isAlive());
    }
}
