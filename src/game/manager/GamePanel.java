package game.manager;

import game.Entidades.Player;
import game.Graficos.Camara;
import game.Graficos.Textura;
import game.Objeto.Bloque;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;


import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable {
    public final int tamanioBaseCasilla = 13;
    public final int escala = 2;
    
    public final int tamanioCasilla = tamanioBaseCasilla * escala;
    
    public final int maxCasillasHorizontales = 28;
    public final int maxCasillasVerticales = 24;
    public final int FPS = 60;
    
    public final int anchoMaximo = tamanioCasilla * maxCasillasHorizontales; //896
    public final int altoMaximo = tamanioCasilla * maxCasillasVerticales;   //768
    
    public final int anchoCamara = anchoMaximo;
    public final int altoCamara = altoMaximo;
    public final int movimientoCamara = tamanioBaseCasilla*3;
    
    private Player mario;
    private NivelManager nivelManager;
    private BufferedImage backgroundSpriteSheet;
    
    Camara cam;
    Textura textura;
    Thread gameThread;
    ObjetoManager objetoManager;
    KeyManager controlManager = new KeyManager();
    
    public GamePanel(){
        this.setPreferredSize(new Dimension(anchoMaximo, altoMaximo));
        this.setBackground(new java.awt.Color(92, 148, 252));
        this.setDoubleBuffered(true);
        
        this.addKeyListener(controlManager);
        this.setFocusable(true);
        
        textura = new Textura();
        objetoManager = new ObjetoManager();
        cam = new Camara(0, movimientoCamara, this);
        
        nivelManager = new NivelManager(objetoManager, controlManager, textura);
        nivelManager.cargar();
        mario = objetoManager.getPlayer();

        objetoManager.enlazar();
    }
    
    public void startGameThread(){
        gameThread = new Thread(this);        
        gameThread.start();
        
        
     // Load the sprite sheet image from the file
    try {
        URL imageUrl = getClass().getResource("/res/images/backgrounds/background.png");
        backgroundSpriteSheet = ImageIO.read(imageUrl);
    } catch (IOException e) {
        e.printStackTrace();
    }    
        
    }
    
    @Override
    public void run(){
        double drawIntervalo = 1000000000/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        
        long timer = 0;
        int drawCount = 0;
        
        while(gameThread != null){
            currentTime = System.nanoTime();
            
            delta += (currentTime - lastTime)/drawIntervalo;
            timer += (currentTime - lastTime);
            lastTime = currentTime;
            
            if(delta >= 1){
                update();
                repaint();
                delta--;
                drawCount++;
            }
            
            if(timer >= 1000000000){
                System.out.println("FPS: " + drawCount);
                drawCount = 0;
                timer = 0;
            }
        }
    }

    
    public void update(){
        objetoManager.enlazar();
        mario.update();
        cam.pos(mario);
        
    }
    
@Override
public void paintComponent(Graphics g) {
    super.paintComponent(g);

    Graphics2D g2 = (Graphics2D) g;

    // Draw the background sprite sheet frame
    if (backgroundSpriteSheet != null) {
        int frameWidth = 10000; // Replace with the actual width of one frame
        int frameHeight = 660; // Replace with the actual height of one frame

        // Calculate the scale factors to fit the height of the GamePanel
        double scaleY = (double) altoMaximo / frameHeight;
        double scaleX = scaleY; // Keep the same aspect ratio for width

        // Calculate the width based on the scale factor
        int scaledWidth = (int) (frameWidth * scaleX);
        int scaledHeight = (int) (frameHeight * scaleY);

        // Calculate the position to draw the frame on the screen (centered)
        int drawX = (anchoMaximo - scaledWidth) / 2 - cam.getX(); // Adjust the X position based on camera position
        int drawY = (altoMaximo - scaledHeight) / cam.getY(); // Adjust the Y position based on camera position

        // Draw the scaled background image to fit the height of the GamePanel
        g2.drawImage(backgroundSpriteSheet, drawX, drawY, drawX + scaledWidth, drawY + scaledHeight,
                0, 0, frameWidth, frameHeight, null);
    }

    g2.translate(cam.getX(), cam.getY());
    mario.mostrar(g2);
    objetoManager.mostrar(g2);
    g2.translate(-cam.getX(), -cam.getY());

    g2.dispose();
}



    
    public Textura getTextura(){
        return textura;
    }
    
    public int getAnchoPantalla(){
        return anchoMaximo;
    }
    
    public int getAltoPantalla(){
        return altoMaximo;
    }
}
