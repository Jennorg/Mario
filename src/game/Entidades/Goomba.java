package game.Entidades;

import game.Graficos.Animacion;
import game.Graficos.Textura;
import game.Objeto.Bloque;
import game.Objeto.Objeto;
import game.Objeto.Objeto_Juego;
import game.manager.ObjetoManager;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Goomba extends Objeto_Juego {

    private Textura textura;

    private int index;
    private BufferedImage[] sprite;
    private Animacion animacion;
    ObjetoManager objManager;
    
    public Goomba(float X, float Y, Objeto ID, float ancho, float alto, int scale, Textura textura, ObjetoManager objManager) {
        super(X, Y, ID, ancho, alto, scale);
        this.textura = textura;
        sprite = textura.getKoopa1();
        animacion = new Animacion(5,sprite[0],sprite[1]);
        this.objManager = objManager;
        setVelocidadX(-1);
        setVelocidadY(1);
    }

    @Override
    public void pos() {
        setX(getVelocidadX() + getX());
        setY(getVelocidadY() + getY());
        
        animacion.anima();
        aplicarGravedad();
        colision();
    }

    @Override
    public void mostrar(Graphics2D g) {
        g.drawImage(sprite[index], (int) getX(), (int) getY(), (int) getAncho(), (int) getAlto(), null);
        
        mostrarBordes(g);
    }

    @Override
    public Rectangle getBorde() {
        return new Rectangle((int) getX(), (int) getY(), (int) getAncho(), (int) getAlto());
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
        
        g.setColor(Color.CYAN);
        g2.draw(getBorde());
        g2.draw(getBordeDerecha());
        g.setColor(Color.GREEN);
        g2.draw(getBordeIzquierda());
        g.setColor(Color.blue);
        g2.draw(getBordeTop());
        g.setColor(Color.MAGENTA);
        g2.draw(getBordeBot());
    }  
    
    public void colision(){
        for(int i = 0; i < objManager.getLista().size(); i++){
            Objeto_Juego temporal = objManager.getLista().get(i);
            if(temporal == this) continue;   
            if(temporal.getId() == Objeto.Jugador && getBorde().intersects(temporal.getBorde())){
                //codigo de muerte de uno u otro
                
            } else { //Si choca con cualquier otra cosa, que camine a la otra direccion
                
                if(getBordeBot().intersects(temporal.getBorde())){                    
                    setY(temporal.getY() - temporal.getAlto());
                }
                
//                if(getBordeIzquierda().intersects(temporal.getBorde())){
//                    if(temporal.getId() == Objeto.Tubo){
//                        setX(temporal.getX() + getAncho()*2); 
//                        
//                    } else {                        
//                        setX(temporal.getX() + getAncho()*0.5f);                          
//                    }   
//                    setVelocidadX(-getVelocidadX());                    
//                }
//                                
//                if(getBordeDerecha().intersects(temporal.getBorde())){
//                    setVelocidadX(-getVelocidadX());
//                    setX(temporal.getX() - temporal.getAncho()*2);                    
//                    
//                }                                                
               
            } 

        }
    }
}