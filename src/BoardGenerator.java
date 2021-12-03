/**
 * @brief The BoardGenerator class creates the background of the Memory Game.
 *
 * @author: Meg Messinger and Alex Chen
 */
import java.awt.*;

public class BoardGenerator {
    public void paint(Graphics g) {
        // creates the background of the game

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
