/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.demontower;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * This class is a child class of level that provides a specific implementation to generate entities in the game.
 * @author Jesse
 */

public class Level2 extends Level
{
    /**
     * constructor for Level2
     * @param squares the grid for the game
     */
    
    public Level2(Square [][] squares)
    {
        this.squares = squares;
    }

    @Override
    public void generateEnemies() throws FileNotFoundException 
    {
        final String FILE_NAME = "Level2-Enemy.txt";
        readSetUpFromFileEntity(getFilePath(FILE_NAME), LevelSetup.DEMON);
    }

    @Override
    public void generateWalls() throws FileNotFoundException 
    {
         // generate walls arround the edges of the game map

         final String FILE_NAME = "Level2-Wall.txt";

         surroundGameWithWalls();
         // generates walls that are away from the edges of the map by reading from a set up file
         readSetUpFromFileWall(getFilePath(FILE_NAME));
    }

    @Override
    public void generateLavaPits() throws FileNotFoundException 
    {
        final String FILE_NAME = "Level2-Lavapit.txt";
        readSetUpFromFileEntity(getFilePath(FILE_NAME), LevelSetup.LAVAPIT);
    }

    @Override
    public void generateKeys() throws FileNotFoundException 
    {
        final String FILE_NAME = "Level2-Key.txt";
        readSetUpFromFileEntity(getFilePath(FILE_NAME), LevelSetup.KEYS);
    }

    @Override
    public void generateTreasures() throws FileNotFoundException 
    {
        final String FILE_NAME = "Level2-Ground.txt";
        final int PERIOD = 5000;
        readSetUpFromFileReward(getFilePath(FILE_NAME));
        scheduleTreasure(PERIOD);
    }

    @Override
    public String getFilePath(String fileName) {
        return new File("").getAbsolutePath().concat("/src/main/resources/Level2-Setup/"+fileName);
    }
    
}
