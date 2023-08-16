package game.Entidades;

import game.Graficos.Animacion;
import game.Graficos.Textura;
import game.Objeto.Bloques.Bloque;
import game.Objeto.Objeto;
import game.Objeto.Objeto_Juego;
import game.manager.ObjetoManager;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Enemy extends Objeto_Juego {

    private BufferedImage[] sprite;
    private Animacion animacion;
    private ObjetoManager objManager;
    
    private boolean pisado = false;
    private boolean haciaDelante = true;
    
    public Enemy(float X, float Y, Objeto ID, float ancho, float alto, int scale, BufferedImage[] sprite, ObjetoManager objManager) {
        super(X, Y, ID, ancho, alto, scale);
        this.sprite = sprite;
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
        if(!pisado){            
            if(haciaDelante){
                animacion.dibujarSprite(g, (int) getX(), (int) getY(), (int) getAncho(), (int) getAlto());
            } else {
                animacion.dibujarSprite(g, (int) (getX() + getAncho()), (int) getY(), (int) -getAncho(), (int) getAlto());
            }
            
        } else {
            g.drawImage(sprite[2], (int) (getX() + getAncho()), (int) getY(), (int) -getAncho(), (int) getAlto(), null);
        }
        
        //mostrarBordes(g);
    }

    @Override
    public Rectangle getBorde() {
        return new Rectangle((int) getX(), (int) getY(), (int) getAncho(), (int) getAlto());
    }
    
    public Rectangle getBordeTop(){
        return new Rectangle((int) (getX()),
                            (int) (getY() - 5),
                            (int) (getAncho()),
                            (int) (getAlto()/2));               
    }
    public Rectangle getBordeBot(){
        return new Rectangle((int) (getX() + getAncho()/2 - getAncho()/4),
                            (int) (getY() + getAlto()/2),
                            (int) (getAncho()/2),
                            (int) getAlto()/2);               
    }
    
    public Rectangle getBordeDerecha(){
        return new Rectangle((int) (getX() + getAncho() - getAncho()/4),
                            (int) (getY() + 5),
                            (int) getAncho()/4 + 5,
                            (int) (getAlto()/2)); 
    }
    
    public Rectangle getBordeIzquierda(){
        return new Rectangle((int) (getX() - 5),
                            (int) (getY() + 5),
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
            if(temporal.getId() == Objeto.Jugador){
                if (getBordeTop().intersects(temporal.getBorde())) {  
                    pisado = true;
                    setVelocidadX(0);
                    setVelocidadY(0);                    
                }
                
            } else { //Si choca con cualquier otra cosa, que camine a la otra dirección
                if (getBordeBot().intersects(temporal.getBorde())) {
                    setY(temporal.getY() - getAlto());
                    setVelocidadY(0); // Detener el movimiento vertical si choca con algo en la parte inferior
                }

                if (getBordeIzquierda().intersects(temporal.getBorde())) {
                    // Cambiar la dirección del Goomba cuando choca con algo en la parte izquierda
                    setVelocidadX(Math.abs(getVelocidadX())); // Valor absoluto para asegurarse de que la velocidad sea positiva
                    haciaDelante = false;
                }

                if (getBordeDerecha().intersects(temporal.getBorde())) {
                    // Cambiar la dirección del Goomba cuando choca con algo en la parte derecha
                    setVelocidadX(-Math.abs(getVelocidadX())); // Valor absoluto para asegurarse de que la velocidad sea negativa
                    haciaDelante = true;
                }
            }


        }
    }
    public boolean esPisado(){
        return pisado;
    }
}