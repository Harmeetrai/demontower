package com.demontower;

import org.junit.Before;
import org.junit.Test;

import javax.swing.*;

import static org.junit.Assert.*;

public class DemonTest {
    private Player player;
    private Demon demon;
    private Square square = new Square(1,1);

    @Before
    public void setUp() throws Exception {
        demon = new Demon(square);
        player = new Player();
    }

    @Test
    public void getSpeed() {
        assertEquals(1, demon.getSpeed());
    }

    @Test
    public void enemyAnimation() {

    }

    @Test
    public void loadImage() {
        demon.loadImage();
        assertEquals(new ImageIcon("demontower/src/main/resources/images/enemy/enemy-up-1.png").getImage(), demon.upImages);
        assertEquals(new ImageIcon("demontower/src/main/resources/images/enemy/enemy-down-1.png").getImage(), demon.downImages);
        assertEquals(new ImageIcon("demontower/src/main/resources/images/enemy/enemy-left-1.png").getImage(), demon.leftImages);
        assertEquals(new ImageIcon("demontower/src/main/resources/images/enemy/enemy-right-1.png").getImage(), demon.rightImages);
    }

    @Test
    public void setDirection() {
        demon.loadImage();
        Direction direction = Direction.NORTH;
        demon.setDirection(direction);
        assertEquals(demon.currentAnim, demon.upImages);

        direction = Direction.SOUTH;
        demon.setDirection(direction);
        assertEquals(demon.currentAnim, demon.downImages);

        direction = Direction.EAST;
        demon.setDirection(direction);
        assertEquals(demon.currentAnim, demon.rightImages);

        direction = Direction.WEST;
        demon.setDirection(direction);
        assertEquals(demon.currentAnim, demon.leftImages);
    }

}
