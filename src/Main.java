import javax.swing.*;

// TODO: keep the game going if user chooses to do so


public class Main {
    public static void main(String[] args) {
        JFrame obj = new JFrame();
        MemoryGame memoryGame = new MemoryGame();
        obj.setBounds(0, 0, 1000, 800);
        obj.setTitle("Memory Game");
        obj.setResizable(false);
        obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        obj.add(memoryGame);
        obj.setVisible(true);

    }

}
