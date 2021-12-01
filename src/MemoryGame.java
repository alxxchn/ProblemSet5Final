import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

public class MemoryGame extends JPanel implements MouseListener {
    private int[] position = {0, 0, 1, 1, 2, 2, 3, 3, 4, 4, 5, 5, 6, 6, 7, 7};
    private String[] filenames = {"dog1.jpg", "dog2.jpg", "dog3.jpg", "dog4.jpg", "dog5.jpg", "dog6.jpg", "dog7.jpg", "dog8.jpg"};
    private int box1, box2;
    private boolean isfirstClick = true;
    private int width = 200;
    private int height = 150;



    public static void swap(int[] position){
        Random rand = new Random();
        for (int i = 0; i < 16; i++){
            position[i] = rand.nextInt(8);
            // need to figure out how to make this only two, he told us but I can't remember :/
        }
    }

    public void paint(Graphics g) {
        BoardGenerator board = new BoardGenerator();
        board.paint((Graphics2D) g);
        swap(position);
        for (int i = 0; i < 16; i++){
            int x = width * (i % 4) + 125;
            int y = height * (i / 4) + 110;

            g.setColor(Color.BLUE);
            g.drawRect(x, y, width - 50, height - 50);
            g.setColor(Color.cyan);
            g.fillRect(x, y, width - 50, height - 50); // not sure how to put the image in
            g.setColor(Color.black);
            g.drawString(filenames[position[i]], x + 50, y + 50);
        }

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // not sure if it's registering the mouse clicks
        int x = e.getX();
        int y = e.getY();
        int i = (int)(x / width);
        int j = (int)(y / height);
        int p = i + j * 4;

        if (isfirstClick){
            box1 = p;
            //show box 1, trouble with this
            isfirstClick = false;
        }
        else {
            box2 = p;
            // show box 2, trouble with this
            isfirstClick = true;
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
}
