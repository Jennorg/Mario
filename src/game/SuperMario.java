package game;

import game.manager.GamePanel;
import game.manager.Reproductor;
import java.awt.Color;
import javax.swing.JFrame;

public class SuperMario {

    public static void main(String[] args) {
        
        /*
        //login
        Reproductor reproductor = new Reproductor();
        
        reproductor.openFile("src/res/audios/Mario Bros - Log in Menu.mp3");
        reproductor.play();
        
        MarioLogin MarioLog = new MarioLogin();
        
        MarioLog.setVisible(true);
        MarioLog.setLocationRelativeTo(null);
        MarioLog.setResizable(false);
        */
        
        //game
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        window.setResizable(false);
        window.setTitle("Super Mairo!");
        
        GamePanel game = new GamePanel(true);
        window.add(game);
        window.pack();
        
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        
        
        game.startGameThread();
      
    }
}

