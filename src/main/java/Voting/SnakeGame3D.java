package Voting;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.LinkedList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

class SnakeGame3D extends JFrame {
    private static final long serialVersionUID = 1L;

    private static final int GRID_SIZE = 40;
    private static final int TILE_SIZE = 20;
    private static final int GAME_SPEED = 80;

    private LinkedList<Point> snake;
    private Point food;
    private int direction; // 0: up, 1: right, 2: down, 3: left

    public SnakeGame3D() {
        setTitle("NitInGSnakeGame");
        setSize(GRID_SIZE * TILE_SIZE, GRID_SIZE * TILE_SIZE);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        snake = new LinkedList<>();
        snake.add(new Point(GRID_SIZE / 2, GRID_SIZE / 2));
        direction = 1; // start moving to the right

        spawnFood();

        addKeyListener(new KeyListener() {
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_UP:
                        if (direction != 2) direction = 0;
                        break;
                    case KeyEvent.VK_RIGHT:
                        if (direction != 3) direction = 1;
                        break;
                    case KeyEvent.VK_DOWN:
                        if (direction != 0) direction = 2;
                        break;
                    case KeyEvent.VK_LEFT:
                        if (direction != 1) direction = 3;
                        break;
                }
            }

            public void keyReleased(KeyEvent e) {
            }

            public void keyTyped(KeyEvent e) {
            }
        });

        // Game loop
        new Thread(() -> {
            while (true) {
                update();
                repaint();
                try {
                    Thread.sleep(GAME_SPEED);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void spawnFood() {
        Random rand = new Random();
        int x, y;
        do {
            x = rand.nextInt(GRID_SIZE);
            y = rand.nextInt(GRID_SIZE);
        } while (snake.contains(new Point(x, y)));

        food = new Point(x, y);
    }

    private void update() {
        Point head = snake.getFirst();
        Point newHead;

        switch (direction) {
            case 0:
                newHead = new Point(head.x, (head.y - 1 + GRID_SIZE) % GRID_SIZE);
                break;
            case 1:
                newHead = new Point((head.x + 1) % GRID_SIZE, head.y);
                break;
            case 2:
                newHead = new Point(head.x, (head.y + 1) % GRID_SIZE);
                break;
            case 3:
                newHead = new Point((head.x - 1 + GRID_SIZE) % GRID_SIZE, head.y);
                break;
            default:
                return;
        }

        if (snake.contains(newHead)) {
            // Game over, reset
            snake.clear();
            snake.add(new Point(GRID_SIZE / 2, GRID_SIZE / 2));
            direction = 1;
            spawnFood();
            return;
        }

        snake.addFirst(newHead);

        if (newHead.equals(food)) {
            // Eat food, spawn new food
            spawnFood();
        } else {
            // Move snake
            snake.removeLast();
        }
    }

    public void paint(Graphics g) {
        super.paint(g);
        
     
        // Draw grid
        g.setColor(Color.lightGray);
        for (int i = 0; i < GRID_SIZE; i++) {
            g.drawLine(i * TILE_SIZE, 0, i * TILE_SIZE, GRID_SIZE * TILE_SIZE);
            g.drawLine(0, i * TILE_SIZE, GRID_SIZE * TILE_SIZE, i * TILE_SIZE);
        }

        // Draw food
        g.setColor(Color.RED);
        g.fillRect(food.x * TILE_SIZE, food.y * TILE_SIZE, TILE_SIZE, TILE_SIZE);

        // Draw snake
        g.setColor(Color.black);
        for (Point p : snake) {
            g.fillRect(p.x * TILE_SIZE, p.y * TILE_SIZE, TILE_SIZE, TILE_SIZE);
            
            // Draw your name
            g.setColor(Color.orange);
            Font font = new Font("Arial", Font.BOLD, 20);
            g.setFont(font);
            String yourName = "Nitin Ghorpade ";
            g.drawString(yourName, GRID_SIZE * TILE_SIZE / 2 - g.getFontMetrics().stringWidth(yourName) / 2,
                    GRID_SIZE * TILE_SIZE / 2);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new SnakeGame3D().setVisible(true);
        });
    }
}
