package com.demontower;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class LevelTest 
{
    private GameController controller;
    private Level level;
    private int index = 0;
    String fileName = "";

    @Before
    public void setUp()
    {
        controller = GameController.objectify();
        controller.startGame();
        level  = controller.getLevel();
    }

    private void initFileSetUp(int index, String fileName)
    {
        this.index = index;
        this.fileName = fileName;
    }

    @Test
    public void readFromFileWall()
    {
        initFileSetUp(3, "Wall-Test.txt");

        level.readSetUpFromFileWall(level.getFilePath(fileName));

        Square [][] squares = level.getSquares();

        assertFalse(squares[index / Level.NUM_OF_SQUARES_Y] [index % Level.NUM_OF_SQUARES_X].isWalkable());
    }

    @Test
    public void readFromFileLavaPit()
    {
        initFileSetUp(95, "LavaPit-Test.txt");
        level.readSetUpFromFileEntity(level.getFilePath(fileName), LevelSetup.LAVAPIT);

        Square [][] squares = level.getSquares();

        assertTrue(squares[index / Level.NUM_OF_SQUARES_Y][index % Level.NUM_OF_SQUARES_X].getEntity() instanceof LavaPit);
    }

    @Test
    public void readFromFileReward()
    {
        initFileSetUp(56, "Ground-Test.txt");

        level.readSetUpFromFileReward(level.getFilePath(fileName));

        Square [][] squares = level.getSquares();
        
        assertTrue(squares[index / Level.NUM_OF_SQUARES_Y][index % Level.NUM_OF_SQUARES_X].isWalkable());

    }

    @Test
    public void readFromFileEnemy()
    {
        initFileSetUp(146, "Enemy-Test.txt");

        level.readSetUpFromFileEntity(level.getFilePath(fileName), LevelSetup.DEMON);

        Square [][] squares = level.getSquares();

        assertTrue(squares[index / Level.NUM_OF_SQUARES_Y][index % Level.NUM_OF_SQUARES_X].getEntity() instanceof Demon);

    }
}
