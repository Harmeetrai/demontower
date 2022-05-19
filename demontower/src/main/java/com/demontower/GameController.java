/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.demontower;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import java.util.Timer;
import java.util.TimerTask;

/**
 * This class is the controller of the application. It interacts with the whole system to control the game
 * For instance, the board class is responsible for pausing, restarting, pausing, starting, and ending game
 *
 * @author Jesse
 */

public class GameController extends JPanel {
    private Square[][] squares;
    private Timer timer=new Timer();
    final int PERIOD = 500;
    public static Player player;
    public static Collision collision=new PlayerCollision();
    private Level level;
    private boolean isEnd = false;
    private boolean isPause = false;

    private static GameController uniqueInstance = null;
    private Image statusImg=new ImageIcon("demontower/src/main/resources/images/panel.png").getImage();
    private Image playerImg=new ImageIcon("demontower/src/main/resources/images/playerPanel.png").getImage();
    private Image fullHeart=new ImageIcon("demontower/src/main/resources/images/full-heart.png").getImage();

    private GameController() {        
        
        setUp();
        startGameLoop();
        startGame();
    }

    private void setUp()
    {
        squares = new Square[16][16];

        setLayout(null);
        setPreferredSize(new Dimension(720, 540));
        for(int i=0;i<16;i++){
            for(int j=0;j<16;j++){
                squares[i][j]=new Ground(i,j);
            }
        }
        addKeyListener(k);
        addMouseListener(m);
        setFocusable(true);
        setFont(new Font(Font.MONOSPACED,Font.BOLD, 15));
    }

    private void startGameLoop()
    {
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                repaint();
            }
        },0,PERIOD);
    }
    
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        for(int i=0;i<24;i++){
            for(int j=0;j<18;j++){
                g.drawImage(new Ground(0,0).getImage(), i*30, j*30 , 30,30,null);
            }
        }
        if(!isEnd){
            drawMap(g);
            g.drawImage(statusImg,520,30,180,360,null);
            g.drawImage(playerImg,530,90,40,40,null);


            g.drawString("Demon Tower",550,80);
            g.drawString("STATUS",570,120);
            g.drawString("Health:",535,170);
            g.drawString("Score:"+player.getScore(),535,220);
            g.drawString("Keys Collected:"+player.getKeyCount(),535,270);
            g.drawString("Time",535,320);
            g.drawString("Level:1",535,370);
            for(int i=0;i<player.getHealth();i++){
                g.drawImage(fullHeart,600+i*30,150,20,20,null);
            }
            g.setColor(Color.white);
            g.drawString("Exit", 520, 420);
            g.drawString("Restart", 580, 420);
            g.drawString("Tips", 660, 420);
            g.setColor(Color.black);
        }else{
            g.setColor(Color.white);
            g.setFont(new Font("italic", Font.ITALIC, 40));
            if(player.isAlive()){
                g.drawString("CONGRATULATION",220,200);
                g.drawString("we finish our mission",200,270);
                g.drawString("Score:"+player.getScore(),300,320);
                g.drawString("Health:"+player.getHealth(),300,370);
                g.drawString("Try again",60,420);
                g.drawString("Exit",600,420);
                g.drawString("we finish our mission",200,270);
            }else{
                g.drawString("GAME OVER",250,220);
                g.drawString("Score:"+player.getScore(),300,320);
                g.drawString("Try again",60,420);
                g.drawString("Exit",600,420);
            }
            g.setColor(Color.black);
        }

    }

    /**
     * creates the UI for the map
     * @param g graphics which we would use to draw on the JPanel
     */

    public void drawMap(Graphics g) {
        for (int i = 0; i < squares.length; i++) {
            for (int j = 0; j < squares[0].length; j++) {
                Square square = squares[i][j];
                Image eleImg = square.getImage();
                g.drawImage(eleImg, j*30,  (i+1)*30, 30,30,null);
            }
        }
        g.drawImage(player.getImage(),player.getYIndex()*30,(player.getXIndex()+1)*30,30,30,null);
    }

    /**
     * sets the grid to a new grid
     * @param grid the new grid
     */

    public void setGrid(Square[][] grid) {
        this.squares = grid;
    }

    /**
     * gets the object of the Singleton GameController
     * @return the controller object
     */

    public static GameController objectify() {
        if (uniqueInstance == null) {
            uniqueInstance = new GameController();
        }

        return uniqueInstance;
    }

    /**
     * gets the square at coordinate (x,y)
     * @param x the x coordinate
     * @param y the y coordinate
     * @return the square at (x,y)
     */

    public Square getSquare(int x, int y) {
        return squares[x][y];
    }

    /**
     * creates an ground on the map
     * @param x the x coordinate
     * @param y the y coordinate
     * @return the ground object
     */

    public Square createGround(int x, int y) {
        return new Ground(x, y);
    }

    /**
     * creates a wall at (x,y)
     * @param x the x coordinate
     * @param y the y coordinate
     * @return a wall object 
     */

    public Square createWall(int x, int y) {
        return new Wall(x, y);
    }

    /**
     * checks if the square is walkable
     * @param square the square we are checking
     * @return true if the square is walkable else false
     */

    public boolean isWalkable(Square square) {
        return square.isWalkable();
    }


    /**
     * gets the xth index this square belongs to in the 2D Square array
     * @param square the square, whose index we are trying to find
     * @return the xth index of the square
     */

    public int getX(Square square) {
        return square.getX();
    }

    /**
     * gets the yth index this square belongs to in the 2D Square array
     * @param square the square, whose index we are trying to find
     * @return the yth index of the square
     */

    public int getY(Square square) {
        return square.getY();
    }

    /**
     * loads the level
     * @param level the current level of the game
     */

    public void setUpLevel(Level level)
    {
        try
        {

            level.generateWalls();
            level.generateEnemies();

            level.generateLavaPits();
            level.generateKeys();

            level.paceGame();

            level.generateTreasures();
            level.generateHealth();
            level.generateStair();
            player = level.generatePlayer();

            level.countDown();
        }
        catch(FileNotFoundException ex)
        {
            displayFileError();
        }
    }

    /**
     * gets the next level of the game
     * @throws FileNotFoundException
     */

    public void nextLevel() throws FileNotFoundException {
        for(int i=0;i<16;i++){
            for(int j=0;j<16;j++){
                squares[i][j]=new Ground(i,j);
            }
        }
        if (level == null) {
            level = new Level1(squares);
        } else if (level instanceof Level1) {
            level = new Level2(squares);
        } else {
            level = new Level3(squares);
        }
        setUpLevel(level);
        
    }

    /**
     * creates a pop up to display file error message
     */
    
    public void displayFileError()
    {
        JOptionPane.showMessageDialog(this,"File was not found");
    }

    /**
     * starts the gane by calling nextLevel()
     * @see nextLevel
     */

    public void startGame(){
        isEnd = false;
        try 
        {
            nextLevel();
        } 
        catch (FileNotFoundException e) 
        {
            displayFileError();
        }
    }

    /**
     * checks if the game is on pause
     * @return  true if the game is on pause else false
     */

    public boolean isPause()
    {
        return isPause;
    }

    /**
     * pauses the game
     */

    public void pauseGame() 
    {
        isPause = true;
        level.pause();
    }

    /**
     * resumes the game
     */

    public void resumeGame()
    {
        isPause = false;
        level.resume();
    }

    /**
     * resets the game
     */

    public void resetGame()
    {
        level.endLevel();

        if(level instanceof Level1)
        {
            level = new Level1(squares);
        }

        else if(level instanceof Level2)
        {
            level = new Level2(squares);
        }

        else
        {
            level = new Level3(squares);
        }

        setUpLevel(level);
    }

    /**
     * exits the game
     */

    public void exitGame()
    {
        endGame();
    }

    /**
     * ends the game
     */

    public void endGame() 
    {
        level.endLevel();
        isEnd = true;

        // do some UI tricks
    }

    /**
     * gets the current timer of the game as a string
     * @return the timer of the game as a string
     */

    public String getTime()
    {
        return level.getCurrentTime();
    }

    /**
     * checks if the game has ended
     * @return true if the game has ended else return false
     */

    public boolean isEnd()
    {
        return isEnd;
    }

    /**
     * gets the score of the player to be displayed in UI
     * @return the score of the player
     */

    public int getScore()
    {
        return Player.objectify().getScore();
    }

    /**
     * gets the 2D array of squares
     * @return the 2D array of squares for the game
     */

    public Square[][] getBoard() {
        return squares;
    }

    MouseAdapter m=new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            super.mouseClicked(e);
            // get position of the click of mouse
            int x=e.getX();
            int y=e.getY();
            if(!isEnd){// game ends
                // the position of click is on the "exit"
                if(x>=520&&x<=560&&y>=410&&y<=425){
                    System.exit(0);
                }
                // the position of click is on the "try again"
                if(x>=580&&x<=645&&y>=410&&y<=425){
                    // stop the period task of level
                    level.endLevel();
                    level=null;
                    // start game
                    startGame();
                }
            }else{
                // the position of click is on the "exit"
                if(x>=600&&x<=680&&y>=390&&y<=520){
                    System.exit(0);
                }
                // the position of click is on the "restart"
                if(x>=65&&x<=235&&y>=395&&y<=420){
                    level.endLevel();
                    level=null;
                    startGame();
                }
            }
        }
    };

    KeyAdapter k = new KeyAdapter() {
        public void keyPressed(KeyEvent e) {
            // pressed key invoke event when game is running
            if(!isEnd && !isPause)
            {
                // next position of player
                int x1 = 0;
                int y1 = 0;
                if (e.getKeyCode() == KeyEvent.VK_UP) {// move up
                    x1 = player.getXIndex()-1;
                    y1 = player.getYIndex();
                    // up position is not allowed to move
                    if (!allowed(x1, y1)) {
                        return;
                    }
                    // update image
                    player.setImage(Player.UP_IMAGE);
                } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {// move left
                    x1 = player.getXIndex();
                    y1 = player.getYIndex()-1;
                    // left position is not allowed to move
                    if (!allowed(x1, y1)) {
                        return;
                    }
                    player.setImage(Player.LEFT_IMAGE);
                } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {// move down
                    x1 = player.getXIndex()+1;
                    y1 = player.getYIndex();
                    // down position is not allowed to move
                    if (!allowed(x1, y1)) {
                        return;
                    }
                    player.setImage(Player.DOWN_IMAGE);
                } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {// move right
                    x1 = player.getXIndex();
                    y1 = player.getYIndex()+1;
                    // right position is not allowed to move
                    if (!allowed(x1, y1)) {
                        return;
                    }
                    player.setImage(Player.RIGHT_IMAGE);
                }else{
                    GameController gc=(GameController)(e.getSource());
                    gc.repaint();
                    return;
                }
                // update position of player
                player.setXIndex(x1);
                player.setYIndex(y1);
                // collision happens after player move
                if(squares[x1][y1].isOccupied()){
                    Entity entity=squares[x1][y1].getEntity();
                    collision.collide(player,entity);
                }
                // win if collect all the key
                if(player.getKeyCount()==level.keyCount){
                    endGame();
                    win();
                }
                // lose if player lost all the health after collision
                if(!player.isAlive()){
                    endGame();
                }
                // repaint
                GameController gc=(GameController)(e.getSource());
                gc.repaint();
            }
        }


    };

    private void win() {

    }

    private boolean allowed(int x1, int y1) {
        if(x1>=0&&x1<16&&y1>=0&&y1<16&&isWalkable(squares[x1][y1])){
            return true;
        }else{
            return false;
        }
    }

    /**
     * gets the current level
     * @return the level of the game
     */

    public Level getLevel() {
        return level;
    }

}
