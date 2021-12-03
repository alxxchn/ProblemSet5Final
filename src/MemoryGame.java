/**
 * @brief The MemoryGame class is a class that creates many methods and variables that are integral to creating the
 * memory game. It creates the images, the cards, checks if the cards have been clicked on, checks for matches, and
 * much more.
 *
 * @author: Meg Messinger and Alex Chen
 */
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class MemoryGame extends JPanel implements ActionListener, MouseListener {
    // creates local variables
    private int[] position = {0, 0, 1, 1, 2, 2, 3, 3, 4, 4, 5, 5, 6, 6, 7, 7}; // array of positions, makes sure there are two of each
    private String[] filenames = {"dog1.jpg", "dog2.jpg", "dog3.jpg", "dog4.jpg", "dog5.jpg", "dog6.jpg", "dog7.jpg", "dog8.jpg"};
    private int box1, box2; // two boxes will be clicked on
    private boolean isfirstClick = true; // checks if it is the first or second click
    private int width = 200; // set width
    private int height = 150; // set height
    private int numMatches = 0; // checks the amount of matches there are
    private BufferedImage[] images; // array of images
    private boolean[] show = new boolean[position.length]; // array that will tell if the images will be shown
    private Timer delayTimer; // helps to stop code from running too fast

    public MemoryGame(){
        addMouseListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        images = new BufferedImage[filenames.length]; // images array created
        // calls images
        for(int i = 0; i < filenames.length; i++){
            // uses try and catch because otherwise the computer will think that this is not possible in every case
            try {
                images[i] = ImageIO.read(new File(filenames[i]));
            }
            catch (IOException ex){
                System.out.println("Failed to load image");
                System.exit(0);
            }
        }
        // swaps the positions around in the array
        swap(position);
        // delay to make sure that both boxes are flipped back over slowly if they are not equal
        delayTimer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                show[box1] = false;
                show[box2] = false;
                repaint();
            }
        });
        // makes sure that not all of the values in the show array are set as false
        delayTimer.setRepeats(false);


    }

    public static void swap(int[] position){
        // swaps the values in the position array
        for (int i = 0; i < position.length - 1; i++){
            int tmp = position[i];
            // ensures that the new random value will be at a position in the position array after the current position(i)
            int rand = (int)(Math.random() * (position.length - i - 1)) + i + 1;
            // flips the value of position[i] with the value of position[rand]
            position[i] = position[rand];
            position[rand] = tmp;

        }
    }

    public void paint(Graphics g) {
        // draws the cards
        BoardGenerator board = new BoardGenerator();
        board.paint((Graphics2D) g);
        for (int i = 0; i < 16; i++){
            int x = width * (i % 4) + 125;
            int y = height * (i / 4) + 110;

            g.setColor(Color.BLUE);
            g.drawRect(x, y, width - 50, height - 50);
            g.setColor(Color.cyan);
            g.fillRect(x, y, width - 50, height - 50); // not sure how to put the image in
            g.setColor(Color.black);
            if (show[i] == true) {
                g.drawImage(images[position[i]], x, y, width - 50, height - 50, this);
            }
        }
    }


    @Override
    public void mouseClicked(MouseEvent e) {
        // registers the mouse clicks
        Scanner scnr = new Scanner(System.in);
        // gets the x and y value of the box that is clicked
        int x = e.getX() - 125;
        int y = e.getY() - 110;
        // finds the row and column of the box clicked
        int i = (int) (x / width);
        int j = (int) (y / height);
        // finds the position of box in the show array
        int p = i + j * 4;

        // sets box1 to the position found and flips over the image
        if (isfirstClick) {
            box1 = p;
            show[box1] = true;
            repaint();
            isfirstClick = false; // now second click
        }
        // sets box2 to the position found and flips over the image
        else {
            box2 = p;
            show[box2] = true;
            isfirstClick = true; // back to first click
            repaint();
            // checks if the images are the same / values in the position array
            if (position[box1] == position[box2]) {
                show[box1] = true;
                show[box2] = true;
                repaint();
                numMatches++; // adds to the number of matches
            }
            // goes to the delayTimer method
            else {
                delayTimer.start();
            }
        }
        // ends the game
        if (numMatches == 8){
            repaint();
            System.out.println("You win! Continue? (y/n)");
            String answer = scnr.next();
            // keeps game going
            if (answer.equals("y")){
                numMatches = 0;
                swap(position);
                for (int k = 0; k < position.length; k++){
                    show[k] = false;
                }
                repaint();
            }
            // stops the game
            else{
                System.exit(0);
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
