/**
 * @brief Memory Game is an interactive game where there are four rows of four cars. There are pairs of matching cards
 * within this game that all have different dogs on them. The goal of the game is to find all of the matches. Once the
 * user has done so, they can either exit the game by entering n or play again by entering y.
 *
 * @author: Meg Messinger and Alex Chen
 */
import javax.swing.*;


public class Main {
    public static void main(String[] args) {
        // creates an object using JFrame
        JFrame obj = new JFrame();
        // creates an object using MemoryGame
        MemoryGame memoryGame = new MemoryGame();
        // sets the bounds of the frame
        obj.setBounds(0, 0, 1000, 800);
        // creates the title of the frame
        obj.setTitle("Memory Game");
        // makes sure the user cannot resize the frame
        obj.setResizable(false);
        obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // creates the object memoryGame in the frame
        obj.add(memoryGame);
        // makes sure memoryGame is visible
        obj.setVisible(true);

    }

}