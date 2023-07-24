/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game.Objeto;

import java.awt.Graphics2D;
import java.awt.Rectangle;

/**
 *
 * @author user
 */
public abstract class Objeto_Juego {
   private float x;
   private float y;
   private Objeto id;
   private float velocidadX,velocidadY;
   private float ancho,alto;
   private final int escala;
   
   public Objeto_Juego(float X, float Y, Objeto ID, float ancho, float alto, int scale ){
        x = X*scale;
        y = Y*scale;
        id = ID;
        this.ancho = ancho*scale;
        this.alto = alto*scale;
        escala=scale;
   }
   
   public abstract void pos();
   public abstract void mostrar(Graphics2D g);
   public abstract Rectangle getBounds();
   
   public void aplicarGravedad(){
       velocidadY += 0.3f;
   }
   
   public void setX(float h){
       x = h;
      }
   
   public void setY(float n){
       y = n;
      }
   
   public void setId(Objeto i){
       id = i;
      }
   
   public void setVelocidadX(float velx){
       velocidadX = velx;
      }
   
   public void setVelocidadY(float vely){
       velocidadY = vely;
      }
   
   public void setAncho(float anch){
       ancho=anch*escala;
      }
   
   public void setAlto(float alt){
       alto=alt*escala;
      }
   
   public float getX(){
       return x;
   }
   
    public float getY(){
       return y;
   }
    
    public Objeto getId(){
        return id;
    }
    
    public float getVelocidadX(){
        return velocidadX;
    }
    
    public float getVelocidadY(){
        return velocidadY;
    }
    
    public float getAncho(){
        return ancho;
    }
    
     public float getAlto(){
        return alto;
    }
   
    public int getEscala(){
        return escala;
    }
   
}

