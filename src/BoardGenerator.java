import java.awt.*;
import java.util.Scanner;

public class BoardGenerator {
    public void paint(Graphics g) {

        // borders
        g.setColor(Color.black);
        g.fillRect(0, 0, 1000, 800);

        // background
        g.setColor(Color.white);
        g.fillRect(8, 8, 985, 750);

        // draw name
        g.setColor(Color.blue);
        g.setFont(new Font("serif", Font.BOLD, 40));
        g.drawString("Memory Game", 375, 75);


    }

}
