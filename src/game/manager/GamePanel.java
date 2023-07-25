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
    
    private Player mario, luigi;
    private NivelManager nivelManager;
    private BufferedImage fondo;
    private boolean multiplayer;
    private boolean juegoEnEjecucion = true;
    
    Reproductor reproductor = new Reproductor();
    Camara cam;
    Textura textura;
    Thread gameThread;
    ObjetoManager objetoManager;
    KeyManager controlManager1 = new KeyManager(1), controlManager2 = new KeyManager(2);
    
    public GamePanel(boolean multiplayer){
        this.setPreferredSize(new Dimension(anchoMaximo, altoMaximo));
        this.setBackground(new java.awt.Color(92, 148, 252));
        this.setDoubleBuffered(true);
        
        this.addKeyListener(controlManager1);
        this.addKeyListener(controlManager2);
        this.setFocusable(true);
       
        
        textura = new Textura();
        objetoManager = new ObjetoManager();
        cam = new Camara(0, movimientoCamara, this);
        
        this.multiplayer = multiplayer;
        
        nivelManager = new NivelManager(objetoManager, controlManager1, controlManager2, textura, reproductor);
        nivelManager.cargar();
        mario = objetoManager.getPlayer(1);
        luigi = objetoManager.getPlayer(2);

        objetoManager.enlazar();
        reproductor.openFile("background.wav","src/res/audios/background.wav");
        reproductor.play("background.wav");
    }
    
    public void startGameThread(){
        gameThread = new Thread(this);        
        gameThread.start();
        
        
        // Load the sprite sheet image from the file
       try {
           URL imageUrl = getClass().getResource("/res/images/backgrounds/background.png");
           fondo = ImageIO.read(imageUrl);
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
            
            if(!juegoEnEjecucion){
                gameThread = null;
                reproductor.stop("background.wav");
            }
        }
    }

    
    public void update(){
        if(!multiplayer){
            objetoManager.enlazar();
            mario.update();
            cam.pos(mario);
        } else {
            objetoManager.enlazar();
            mario.update();
            luigi.update();
            cam.pos(mario);
        }


        if(mario.getSeMurio() || luigi.getSeMurio()){
            detenerJuego();
        }
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        if (fondo != null) {
            int anchoFrame = 10000;
            int altoFrame = 660; 

            double escalaY = (double) altoMaximo / altoFrame;
            double escalaX = escalaY;

            int scaledWidth = (int) (anchoFrame * escalaX);
            int scaledHeight = (int) (altoFrame * escalaY);

            int x = (anchoMaximo - scaledWidth) / 2 - cam.getX(); 
            int y = (altoMaximo - scaledHeight) / cam.getY();

            g2.drawImage(fondo, x, y, x + scaledWidth, y + scaledHeight,
                    0, 0, anchoFrame, altoFrame, null);
        }

        g2.translate(cam.getX(), cam.getY());
        mario.mostrar(g2);
        luigi.mostrar(g2);
        objetoManager.mostrar(g2);
        g2.translate(-cam.getX(), -cam.getY());

        g2.dispose();
    }

    
    public void detenerJuego() {
        juegoEnEjecucion = false;
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
