import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame jFrame= new JFrame();
        PlayGame playGame= new PlayGame();
        jFrame.setBounds(10,10,700,600);
        jFrame.setTitle("Brick Buster");
        jFrame.setResizable(false);
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(jFrame.EXIT_ON_CLOSE);
        jFrame.add(playGame);
    }

}
