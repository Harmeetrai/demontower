/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.demontower;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

/**
 * This class is responsible for setting up the games by levels.
 * It generates entities in the game based on each level's difficulty
 * @author Jesse
 */

public abstract class Level 
{

    public final static int NUM_OF_SQUARES_X = 16;
    public final static int NUM_OF_SQUARES_Y = 16;
    public int keyCount;
    
    private Timer enemyScheduler = new Timer();
    private Timer timerScheduler = new Timer();
    private final int TIME_STARTING_VALUE = 120;
    private int time = TIME_STARTING_VALUE;
    private boolean isPause = false;

    protected Timer treasureScheduler = new Timer();
    protected Timer healthScheduler = new Timer();
    protected Square treasureSquare;
    protected Square healthSquare;

    protected ArrayList<Integer> emptySquares = new ArrayList<>();


    protected Square squares[][];
    protected List<Enemy> enemies=new ArrayList<>();
    
     /**
    * @author Jesse
    * Generates enemies based on level's difficulty
    */
    
    public abstract void generateEnemies() throws FileNotFoundException;
    
     /**
    * @author Jesse
    * Generates barriers based on level's difficulty
    */
    
    public abstract void generateWalls() throws FileNotFoundException;
    
     /**
    * @author Jesse
    * Generates lava pits based on level's difficulty
    */
    
    public abstract void generateLavaPits() throws FileNotFoundException;
    
     /**
    * @author Jesse
    * Generates keys based on level's difficulty
    */
    
    public abstract void generateKeys() throws FileNotFoundException;
    
     /**
    * @author Jesse
    * Generates treasures based on level's difficulty
    */
    
    public abstract void generateTreasures() throws FileNotFoundException;

    /**
     * pace the appearance of the treasure in the game
     * @param period the time it takes for a treasure to appear again after disappearing
     */

    public void scheduleTreasure(int period)
    {
        treasureScheduler.scheduleAtFixedRate(new TimerTask() {

            @Override
            public void run() {

                if (treasureSquare != null) {
                    treasureSquare.setEntity(null);
                }

                Random rand = new Random();
                int randomIndex = rand.nextInt(emptySquares.size() - 1);

                int squareIndex = emptySquares.get(randomIndex);
                Square square = squares[squareIndex / Level.NUM_OF_SQUARES_Y][squareIndex % Level.NUM_OF_SQUARES_X];
                if(square.isWalkable()&&square.getEntity()==null){//avoid random position is in the wall
                    Treasure treasure = new Treasure(square);
                    square.setEntity(treasure);
                    treasureSquare = square;
                }

            }

        }, 0, period);
    }

     /**
     * add walls to the boundaries of the map
     */

    public void surroundGameWithWalls()
    {
        for (int i = 0; i < squares.length; i++) {
            for (int j = 0; j < squares[0].length; j++) {
                if (i == 0 || j == 0 || i == squares.length - 1 || j == squares[0].length - 1) {
                    int xPos = squares[i][j].getX();
                    int yPos = squares[i][j].getY();

                    squares[i][j] = new Wall(xPos, yPos);
                }
            }
        }
    }

    /**
     * schedules the pace at which health is going to appear and disappear
     */

    public void generateHealth()
    {
        final int PERIOD = 5000;
        healthScheduler.scheduleAtFixedRate(new TimerTask() {

            @Override
            public void run() {

                if (healthSquare != null) {
                    healthSquare.setEntity(null);
                }

                Random rand = new Random();
                int randomIndex = rand.nextInt(emptySquares.size() - 1);

                int squareIndex = emptySquares.get(randomIndex);
                Square square = squares[squareIndex / Level.NUM_OF_SQUARES_Y][squareIndex % Level.NUM_OF_SQUARES_X];
                if(square.isWalkable()&&square.getEntity()==null){
                    MedKit medkit = new MedKit(square);
                    square.setEntity(medkit);
                }
                healthSquare = square;
            }

        }, 0, PERIOD);
    }

    /**
     * stops the level immediately and terminates all timers for the level
     */

    public void endLevel()
    {
        keyCount=0;
        timerScheduler.cancel();
        enemyScheduler.cancel();
        healthScheduler.cancel();
        treasureScheduler.cancel();
    }

    /**
     * moves the enemy at a certain pace
     */

    public void paceGame()
    {
        final int PERIOD = 2000;
        enemyScheduler.scheduleAtFixedRate(new TimerTask() {

            @Override
            public void run() {

                if(!isPause)
                {
                    for(Object obj: enemies.toArray())
                    {
                        Enemy enemy = (Enemy) obj;
                        if(enemy.isActive())
                            enemy.move();
                    }
                }
            }
            
        }, PERIOD/2, PERIOD);
    }

    /**
     * creates a player at the starting position
     * @return the player object
     */

    public Player generatePlayer()
    {
        Player player = Player.getNewPlayer();
        player.setXIndex(1);
        player.setYIndex(1);
        return player;
    }

    /**
     * creates stairs at the starting point and ending point of the game
     */

    public void generateStair()
    {
        int posX = squares[1][0].getX();
        int posY = squares[1][0].getY();

        squares[1][0] = new Stair(posX, posY);

        posX = squares[squares.length - 2][squares[0].length-1].getX();
        posY = squares[squares.length - 2][squares[0].length-1].getY();

        squares[squares.length - 2][squares[0].length-1] = new Stair(posX, posY);
    }

    /**
     * gets the current time count down
     * @return string representation of the current time
     */

    public String getCurrentTime()
    {
        int min = time / 60;
        int sec = time % 60;

        String minString = ""+min;
        String secString  = "";

        if(sec < 10)
        {
            secString = secString.concat("0");
        }

        secString = secString + sec;

        return minString + ":" +secString;
    }

    /**
     * pauses the level
     */


    public void pause()
    {
        isPause = true;
    }

     /**
     * resumes the level
     */

    public void resume()
    {
        isPause = false;
    }

    /**
     * schedules the countdown for the timer
     */

    public void countDown()
    {
        timerScheduler.scheduleAtFixedRate(new TimerTask() {

            @Override
            public void run() {

                if(time >= 0)
                {
                    if(!isPause)
                    {
                        time--;
                    }
                }
                else
                {
                    timerScheduler.cancel();
                    GameController.objectify().endGame();
                }
            }
            
        }, 0, 1000);
    }

    /**
     * gets the absolute file path of the file
     * @param fileName the fileName that we want to read from
     * @return the string representation of the file path
     */

    public abstract String getFilePath(String fileName);

    /**
     * reads game walls from file
     * @param filePath the filepath representing the file we are going to read from
     */

    public void readSetUpFromFileWall(String filePath)
    {
        try
        {

        File file = new File(filePath);

        Scanner fileScanner = new Scanner(file);

        while (fileScanner.hasNextLine()) {
            int index = Integer.parseInt(fileScanner.nextLine());

            // converts indexes -> [i,j] e.g 21 -> [1,7]

            Square square = squares[index / (NUM_OF_SQUARES_Y)][index % (NUM_OF_SQUARES_X)];
            int xPos = square.getX();
            int yPos = square.getY();


            square = new Wall(xPos, yPos);


            // sets the squares to either wall or ground based on polymorphic behaviour

            squares[index / (NUM_OF_SQUARES_Y)][index % (NUM_OF_SQUARES_X)] = square;
        }

        fileScanner.close();
    }
    catch(FileNotFoundException ex)
    {

    }

    }

    /**
     * reads game reward positions from file
     * @param filePath the filepath representing the file to be read
     */

    public void readSetUpFromFileReward(String filePath)
    {
        try
        {
        emptySquares.clear();
        File file = new File(filePath);

        Scanner fileScanner = new Scanner(file);

        while (fileScanner.hasNextLine()) {
            emptySquares.add(Integer.parseInt(fileScanner.nextLine()));
        }

        fileScanner.close();
        }
        catch(FileNotFoundException ex)
        {
            
        }
    }
    
    /**
     * reads the entities' positions then loads them into the map
     * @param filePath filepath representing the file to be read
     * @param levelSetup the levelSetup represents the type of entities you want to load the map with using positions that are specified in the file
     */

    public void readSetUpFromFileEntity(String filePath, LevelSetup levelSetup)
    {
        try
        {

        File file = new File(filePath);

        Scanner fileScanner = new Scanner(file);

        while (fileScanner.hasNextLine()) {
            int index = Integer.parseInt(fileScanner.nextLine());

            // converts indexes -> [i,j] e.g 21 -> [1,7]

            Square square = squares[index / (NUM_OF_SQUARES_Y)][index % (NUM_OF_SQUARES_X)];
            Entity entity = null;

            if (levelSetup == LevelSetup.LAVAPIT) {
                entity = new LavaPit(square);
                square.setEntity(entity);
            } else if (levelSetup == LevelSetup.DEMON) {
                entity = new Demon(square);

                entity.setYIndex(index / NUM_OF_SQUARES_Y);
                entity.setXIndex(index % NUM_OF_SQUARES_X);

                square.setEntity(entity);
                enemies.add((Enemy) entity);
            } else if (levelSetup == LevelSetup.KEYS) {
                entity = new Key(square);
                square.setEntity(entity);
                keyCount++;
            }

            // sets the squares to either wall or ground based on polymorphic behaviour

            squares[index / (NUM_OF_SQUARES_Y)][index % (NUM_OF_SQUARES_X)] = square;
        }

        fileScanner.close();
    }
    catch(FileNotFoundException ex)
    {

    }
    }

    /**
     * returns the squares for this level
     * @return a 2D array representing the board
     */
        
    public Square[][] getSquares() {
        return squares;
    }
    
}
