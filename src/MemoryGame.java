import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MemoryGame extends JPanel implements MouseListener {
    private int[] position = new int[16];
    private String[] filenames = {"dog1.jpg", "dog2.jpg", "dog3.jpg", "dog4.jpg", "dog5.jpg", "dog6.jpg", "dog7.jpg", "dog8.jpg"};
    private int box1, box2;
    private boolean isfirstClick = true;
    private int width = 200;
    private int height = 150;


    public void paint(Graphics g) {
        for (int i = 0; i < 16; i++){
            int x = width * (i % 4);
            int y = height * (i / 4);

            g.setColor(Color.BLUE);
            g.drawRect(x, y, width, height);
            g.drawString(filenames[position[i]], x, y);
        }

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        int i = (int)(x / width);
        int j = (int)(y / height);
        int p = i + j * 4;

        if (isfirstClick){
            box1 = p;
            //show box 1
            isfirstClick = false;
        }
        else {
            box2 = p;
            // show box 2
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
