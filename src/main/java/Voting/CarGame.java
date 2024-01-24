package Voting;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CarGame extends JFrame {
    private static final long serialVersionUID = 1L;

    private int playerPosition = 0;
    private int opponentPosition = 0;
    private Timer timer;

    public CarGame() {
        setTitle("Swing Car Racing Game");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JButton accelerateButton = new JButton("Accelerate");
        JButton brakeButton = new JButton("Brake");

        accelerateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                playerPosition++;
                opponentPosition += Math.random() > 0.5 ? 2 : 1;
                checkRaceResult();
                repaint();
            }
        });

        brakeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                playerPosition = Math.max(0, playerPosition - 2);
                opponentPosition += Math.random() > 0.5 ? 2 : 1;
                checkRaceResult();
                repaint();
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(accelerateButton);
        buttonPanel.add(brakeButton);

        add(buttonPanel);

        timer = new Timer(500, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                opponentPosition += Math.random() > 0.5 ? 2 : 1;
                checkRaceResult();
                repaint();
            }
        });

        timer.start();
    }

    private void checkRaceResult() {
        if (playerPosition >= 100) {
            JOptionPane.showMessageDialog(this, "You won!");
            timer.stop();
        } else if (opponentPosition >= 50) {
            JOptionPane.showMessageDialog(this, "Opponent won. Better luck next time!");
            timer.stop();
        }
    }

    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.BLUE);
        g.fillRect(10 + playerPosition * 20, 100, 20, 10);

        g.setColor(Color.RED);
        g.fillRect(10 + opponentPosition * 20, 120, 20, 10);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new CarGame().setVisible(true);
        });
    }
}
