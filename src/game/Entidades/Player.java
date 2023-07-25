package game.Entidades;

import game.Graficos.Animacion;
import game.Graficos.Textura;
import game.Objeto.Bloque;
import game.Objeto.Estrella;
import game.Objeto.Objeto;
import static game.Objeto.Objeto.Bloque;
import static game.Objeto.Objeto.Tubo;
import game.Objeto.Objeto_Juego;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import game.manager.GamePanel;
import game.manager.KeyManager;
import game.manager.ObjetoManager;
import game.manager.Reproductor;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

public class Player extends Objeto_Juego {
    public static final float ANCHO = 16;
    public static final float ALTO = 16;  
    int n_Golpes = 1;
    GamePanel game;
    KeyManager controlManager;
   
    private boolean haciaDelante;
    private boolean salta = false;
    private boolean enElAire = false;
    private boolean haGanado = false;    
    Reproductor reproductor = new Reproductor();

    Accion accion;
    
    Textura textura;
    ObjetoManager objManager;
    BufferedImage[] playerGrande, playerPeque, actualSprite;
    Animacion caminaEnGrande, caminaEnPeque, actualAnimacion;
    EstadoPlayer estado;
    
    LinkedList<Bloque> bloquesEliminados;
    LinkedList<Goomba> goombasEliminados;
    LinkedList<Objeto_Juego> items;
    
    public Player(KeyManager controlManager, ObjetoManager objetoManager, float x, float y,int escala, Textura textura){      
        super(x, y, Objeto.Jugador, ANCHO, ALTO, escala);
        
        this.textura = textura;
        this.controlManager = controlManager;
        
        haciaDelante = false;
        
        objManager = objetoManager;
        
        bloquesEliminados = new LinkedList<Bloque>();
        goombasEliminados = new LinkedList<Goomba>();
        items = new LinkedList<Objeto_Juego>();
        
        playerGrande = textura.getMarioGrande();
        playerPeque = textura.getMarioPeque();
        
        accion = Accion.quieto;
        estado = EstadoPlayer.Peque;
        actualSprite = playerPeque;
        caminaEnPeque = new Animacion(5, playerPeque[1], playerPeque[2], playerPeque[3]);
        caminaEnGrande = new Animacion(1, playerGrande[1], playerGrande[2], playerGrande[3]);
        actualAnimacion = caminaEnPeque;
        
    }
    
    public LinkedList<Objeto_Juego> getYResetLinkedListBloqueEliminado(){
        LinkedList<Objeto_Juego> lista = new LinkedList<Objeto_Juego>();
        
        for(Goomba goomba: goombasEliminados){

            lista.add(goomba);
        }
        
        for(Objeto_Juego obj: items){
            lista.add(obj);
        }
        
        for(Bloque bloqueEliminado: bloquesEliminados){
            if(!bloqueEliminado.tocaDesaparecer()) continue;
            lista.add(bloqueEliminado);
             
        }
        
        return lista;
    }
    
    @Override
    public void pos() {
        
        if (seMuere()) {
            accion = Accion.muerto;
            setVelocidadX(0);
            setVelocidadY(0);
        }
        
        if(haGanado){
            setVelocidadX(0);
            setVelocidadY(0);
        }
        
        setX(getVelocidadX() + getX());
        setY(getVelocidadY() + getY());
        
        aplicarGravedad();
        actualAnimacion.anima();
        colision();
    }
    
    
    public void update() {  
        
        if(!seMuere()){
        
            if (controlManager.derecha) {
                setVelocidadX(3);
                if(!salta){
                    accion = Accion.caminando;
                }
                haciaDelante = true;
            } else if (controlManager.izquierda) {
                setVelocidadX(-3);
                if(!salta){
                    accion = Accion.caminando;
                }
                haciaDelante = false;

            } else {
                setVelocidadX(0);
                if(!salta){
                    accion = Accion.quieto;
                }
            }

            if (controlManager.arriba) {
               if(!salta){
                   setVelocidadY(-10);
                   salta = true;     
                   accion = Accion.saltando;
                   reproductor.openFile("src/res/audios/jump.wav");
                   reproductor.play();

               }
               enElAire = salta;
            } 
        }                  
    }


    
    
    @Override
    public void mostrar(Graphics2D g2) {
        switch (accion) {
            case quieto: 
                g2.drawImage(actualSprite[0], (int) getX(), (int) getY(), (int) getAncho(), (int) getAlto(), null);
                break;
            case caminando: 
                if (haciaDelante) {
                    actualAnimacion.dibujarSprite(g2, (int) getX(), (int) getY(), (int) getAncho(), (int) getAlto());
                } else {
                    actualAnimacion.dibujarSprite(g2, (int) (getX() + getAncho()), (int) getY(), (int) -getAncho(), (int) getAlto()); 
                }
                break;
            case saltando: 
                if (haciaDelante) {
                    g2.drawImage(actualSprite[5], (int) getX(), (int) getY(), (int) getAncho(), (int) getAlto(), null);
                } else {
                    g2.drawImage(actualSprite[5], (int) (getX() + getAncho()), (int) getY(), (int) -getAncho(), (int) getAlto(), null);
                }
                break;
            case muerto:
                g2.drawImage(actualSprite[6], (int) (getX() + getAncho()), (int) getY(), (int) -getAncho(), (int) getAlto(), null);
        }

        //mostrarBordes(g2);
    }

    
    public void colision(){
        for(int i = 0; i < objManager.getLista().size(); i++){
            Objeto_Juego temporal = objManager.getLista().get(i);
            if(temporal == this) continue;
            if(temporal.getId() == Objeto.Bloque && getBordeTop().intersects(temporal.getBorde())){                                
                setY(temporal.getY() + temporal.getAlto());
                setVelocidadY(0);
                ((Bloque) temporal).golpea();
                bloquesEliminados.add((Bloque) temporal);
                
                
            }   else if (temporal.getId() == Objeto.Enemigo && temporal instanceof Goomba goomba) {
                if (getBordeBot().intersects(temporal.getBorde()) && goomba.esPisado()) {
                    goombasEliminados.add(goomba);
                    setVelocidadY(-5); // Impulso hacia arriba cuando el jugador salta sobre el Goomba
                } else if (getBorde().intersects(temporal.getBorde())) {
                    n_Golpes--; // Reduce el número de golpes del jugador cuando choca con un Goomba
                }
                
            } else if(temporal.getId() == Objeto.Item) {
                
                if(temporal instanceof Estrella && getBorde().intersects(temporal.getBorde())){
                    items.add(temporal);
                    haGanado = true;
                }
                
            } else {
                
                if(getBordeBot().intersects(temporal.getBorde())){                    
                    setVelocidadY(0);
                    setVelocidadX(0);
                    setY(temporal.getY() - temporal.getAlto()*1.5f);
                    enElAire = false;
                    salta = false;
                } 

                
                if(getBordeIzquierda().intersects(temporal.getBorde())){
                    if(temporal.getId() == Objeto.Tubo){
                        setX(temporal.getX() + getAncho()*1.2f); // Establece la posición x del jugador al lado izquierdo del bloque
                        setVelocidadX(0);
                        
                    } else {                        
                        setX(temporal.getX() + getAncho()*0.5f);  
                        setVelocidadX(0);
                    }               
                    
                }
                
                if(getBordeTop().intersects(temporal.getBorde())){
                    setVelocidadY(0);
                    setY(temporal.getY() + temporal.getAlto());
                                       
                }
                if(getBordeDerecha().intersects(temporal.getBorde())){
                    setVelocidadX(0);
                    setX(temporal.getX() - temporal.getAncho()*1.2f);                    
                    
                }                                                
               
            } 

        }
    }

    @Override
    public Rectangle getBorde() {
        return new Rectangle((int) (getX() + getAncho()/2 - getAncho()/4),
                            (int) (getY() + getAlto()/2),
                            (int) (getAncho()/2),
                            (int) (getAlto()/2)); 
    }
    
    public Rectangle getBordeTop(){
        return new Rectangle((int) (getX() + (getAncho()/2) - (getAncho()/4)),
                            (int) (getY()),
                            (int) (getAncho()/2),
                            (int) (getAlto()/2));               
    }
    public Rectangle getBordeBot(){
        return new Rectangle((int) (getX() + getAncho()/2 - getAncho()/4),
                            (int) (getY() + getAlto()/2),
                            (int) (getAncho()/2),
                            (int) getAlto()/2);               
    }
    
    public Rectangle getBordeDerecha(){
        return new Rectangle((int) (getX() + getAncho()/2),
                            (int) (getY() + 10),
                            (int) getAncho()/4 + 5,
                            (int) (getAlto()/2)); 
    }
    
    public Rectangle getBordeIzquierda(){
        return new Rectangle((int) (getX() + 5),
                            (int) (getY() + 10),
                            (int) getAncho()/4 + 5,
                            (int) (getAlto()/2));
    }
    
    public void mostrarBordes(Graphics g){
        Graphics2D g2 = (Graphics2D) g;
        
        g.setColor(Color.red);
        //g2.draw(getBounds());
        g2.draw(getBordeDerecha());
        g.setColor(Color.GREEN);
        g2.draw(getBordeIzquierda());
        g.setColor(Color.blue);
        g2.draw(getBordeTop());
        g.setColor(Color.MAGENTA);
        g2.draw(getBordeBot());
    }

    public boolean seMuere(){
        return n_Golpes <= 0;
    }
}
