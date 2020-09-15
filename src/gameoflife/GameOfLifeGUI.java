/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameoflife;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author Andrey
 */
public class GameOfLifeGUI {

    JFrame LifeFrame;
    JLabel GridLabel;
    JPanel LifePanel;
    JButton StepButton, RunButton, viewButton, replayButton;
    boolean isRun = false;
    boolean darkMode = false;
    public static Life game = new Life();
    Timer timer = new Timer(1, new timerListener());

    public GameOfLifeGUI() {
        //creates the frame
        this.LifeFrame = new JFrame("The Game of Life (Dark Mode)");
        LifeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.LifePanel = new JPanel();
        this.GridLabel = new JLabel(game.toHTML());
        LifePanel.add(GridLabel);
        LifePanel.setBackground(Color.white);

        //a button that performs only one step at a time when clicked
        this.StepButton = new JButton("Run a Step");
        StepButton.setForeground(Color.WHITE);
        StepButton.setBackground(Color.BLACK);
        StepButton.setActionCommand("step");
        StepButton.addActionListener(new btnListener());
        LifePanel.add(StepButton);

        //a button that runs the game on a timer, click to run and click again to stop
        this.RunButton = new JButton("Run");
        RunButton.setForeground(Color.WHITE);
        RunButton.setBackground(Color.BLACK);
        RunButton.setActionCommand("run");
        RunButton.addActionListener(new btnListener());
        LifePanel.add(RunButton);

        //a button that lets the user switch themes
        this.viewButton = new JButton("Dark Theme");
        viewButton.setForeground(Color.WHITE);
        viewButton.setBackground(Color.BLUE);
        viewButton.setActionCommand("colour");
        viewButton.addActionListener(new btnListener());
        LifePanel.add(viewButton);

        //a button that resets the board
        this.replayButton = new JButton("Replay");
        replayButton.setForeground(Color.WHITE);
        replayButton.setBackground(Color.BLACK);
        replayButton.setActionCommand("replay");
        replayButton.addActionListener(new btnListener());
        LifePanel.add(replayButton);

        LifeFrame.add(LifePanel);
        //set size of the frame
        LifeFrame.setSize(800, 880);
        LifeFrame.setVisible(true);

    }

    /**
     * Dictates the events that occur when the timer runs
     */
    class timerListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            game.takeStep();
            GridLabel.setText(game.toHTML());
        }
    }

    class btnListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String actcmd = e.getActionCommand();
            if (actcmd.equals("step")) {
                game.takeStep();
                GridLabel.setText(game.toHTML());
            }
            if (actcmd.equals("run")) {
                if (isRun == false) {
                    timer.start();
                    isRun = true;
                    RunButton.setText("Stop");
                } else {
                    timer.stop();
                    isRun = false;
                    RunButton.setText("Run");
                }
            }
            if (actcmd.equals("colour")) {
                if (darkMode == false) {
                    darkMode = true;
                    viewButton.setText("Light Theme");
                    LifePanel.setBackground(Color.BLACK);
                    viewButton.setBackground(Color.BLACK);
                } else {
                    darkMode = false;
                    viewButton.setText("Dark Theme");
                    LifePanel.setBackground(Color.WHITE);
                    viewButton.setBackground(Color.BLUE);
                }
            }
            if (actcmd.equals("replay")) {
                LifeFrame.dispose();
                runGame();
            } // replay button kills the active window and makes a new one
        }
    }

    public static void runGUI() {
        GameOfLifeGUI foo = new GameOfLifeGUI();
    }

    /**
     * runs the game of life
     */
    public static void runGame() {
        game.setPattern(game.grid);
        javax.swing.SwingUtilities.invokeLater(
                new Runnable() {
            @Override
            public void run() {
                runGUI();
            }
        });
    }

    public static void main(String[] args) {
        runGame();
    }
}
