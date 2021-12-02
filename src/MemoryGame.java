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
import java.util.Random;
import java.util.Scanner;

public class MemoryGame extends JPanel implements ActionListener, MouseListener {
    private int[] position = {0, 0, 1, 1, 2, 2, 3, 3, 4, 4, 5, 5, 6, 6, 7, 7};
    private String[] filenames = {"dog1.jpg", "dog2.jpg", "dog3.jpg", "dog4.jpg", "dog5.jpg", "dog6.jpg", "dog7.jpg", "dog8.jpg"};
    private int box1, box2;
    private boolean isfirstClick = true;
    private int width = 200;
    private int height = 150;
    private int numMatches = 0;
    private BufferedImage[] images;
    private boolean[] show = new boolean[position.length];
    private Timer delayTimer;

    public MemoryGame(){
        addMouseListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        images = new BufferedImage[filenames.length];
        for(int i = 0; i < filenames.length; i++){
            try {
                images[i] = ImageIO.read(new File(filenames[i]));
            }
            catch (IOException ex){
                System.out.println("Failed to load image");
                System.exit(0);
            }
        }
        swap(position);

        delayTimer = new Timer(2000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                show[box1] = false;
                show[box2] = false;
                repaint();
            }
        });
        delayTimer.setRepeats(false);


    }

    public static void swap(int[] position){
        for (int i = 0; i < position.length - 1; i++){
            int tmp = position[i];
            int rand = (int)(Math.random() * (position.length - i - 1)) + i + 1;
            position[i] = position[rand];
            position[rand] = tmp;

        }
    }

    public void paint(Graphics g) {
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
        // not sure if it's registering the mouse clicks
        Scanner scnr = new Scanner(System.in);
        int x = e.getX() - 125;
        int y = e.getY() - 110;
        int i = (int) (x / width);
        int j = (int) (y / height);
        int p = i + j * 4;
        System.out.println(p);

        if (isfirstClick) {
            box1 = p;
            show[box1] = true;
            repaint();
            isfirstClick = false;
        } else {
            box2 = p;
            show[box2] = true;
            isfirstClick = true;
            repaint();
            if (position[box1] == position[box2]) {
                show[box1] = true;
                show[box2] = true;
                repaint();
                numMatches++;
                if (numMatches == 8){
                    System.out.println("You win! Continue? (y/n)");
                    repaint();
                    String answer = scnr.next();
                    if (answer.equals("y")){
                        numMatches = 0;
                        swap(position);
                        for (int k = 0; k < position.length; k++){
                            show[k] = false;
                        }
                        repaint();
                    }
                    else{
                        System.exit(0);
                    }
                }
            } else {
                delayTimer.start();
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
