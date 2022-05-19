package com.demontower;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Timer;
import java.util.TimerTask;

import org.junit.Before;
import org.junit.Test;

public class GameControllerTest 
{

    private GameController controller;

    @Before
    public void setUp()
    {
        controller = GameController.objectify();
    }

    @Test
    public void pauseGame()
    {
        controller.pauseGame();

        assertTrue("Game did not pause.....", controller.isPause());
    }

    @Test
    public void resumeGame()
    {
        controller.pauseGame();
        controller.resumeGame();

        assertFalse("Game did not resume....", controller.isPause());
    }

    public void waitForGame(int waitTime)
    {
        controller.startGame();

        try 
        {
            Thread.sleep(waitTime);
        } 

        catch (InterruptedException e) 
        {
            e.printStackTrace();
        }

    }

    @Test
    public void getTime()
    {
        waitForGame(60000);

        String timeString = "0:58";
        String timeStringLeft = "0:57";
        String timeStringRight = "0:59";

        boolean res = timeString.equals(controller.getTime()) || timeStringLeft.equals(controller.getTime())
        || timeStringRight.equals(controller.getTime());

        assertTrue("Time does not count properly", res);    
    }

    @Test
    public void endGame()
    {
        controller.endGame();

        assertTrue("Game is supposed to end", controller.isEnd());

    }

    @Test
    public void resetGameByAssertingOnTimer()
    {
        controller.startGame();

        try 
        {
            Thread.sleep(3000);
        }

        catch (InterruptedException e) 
        {
            e.printStackTrace();
        }

        controller.resetGame();

        String timeAfterReset = controller.getTime();

        assertTrue("Game did not reset successfully based on time", timeAfterReset.equals("2:00"));
    }

    @Test 
    public void resetGameByAssertingOnPlayerPosition()
    {
        controller.startGame();

        Player player = Player.objectify();

        player.setXIndex(5);
        player.setYIndex(10);

        controller.resetGame();

        assertTrue("Game did not reset successfully based on player's position", Player.objectify().isPositionEqual(1, 1));

    }

    @Test
    public void startGameAssertingOnTime()
    {
        controller.startGame();

        try 
        {
            Thread.sleep(3000);
        } 
        catch (InterruptedException e) 
        {
            e.printStackTrace();
        }

        assertFalse("Game start up failed", controller.getTime().equals("2:00"));
    }

    

}
