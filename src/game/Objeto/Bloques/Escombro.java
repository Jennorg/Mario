package game.Objeto.Bloques;

import game.Graficos.Textura;
import game.manager.GamePanel;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Escombro {
    private BufferedImage[] escombros;
    private int escala;
    private float ancho, alto, velocidadX, velocidadY;
    float[] x, y;
    
    public Escombro(float x, float y, float ancho, float alto, int escala, BufferedImage[] sprite){
        
        this.x = new float[4];
        this.y = new float[4];
        
        this.x[0] = (float) (x - (0.5 * ancho));
        this.x[1] = (float) (x - (0.5 * ancho));
        this.x[2] = (float) (x + (0.5 * ancho));
        this.x[3] = (float) (x + (0.5 * ancho));
        
        this.y[0] = (float) (y + (0.5 * alto));
        this.y[1] = (float) (y - (0.5 * alto));
        this.y[2] = (float) (y + (0.5 * alto));
        this.y[3] = (float) (y - (0.5 * alto));
        
        this.ancho = ancho/2;
        this.alto = alto/2;
        escombros = sprite;
        
        velocidadX = 2f;
        velocidadY = -5f;
    }
    
    public void aplicarGravedad(){
        velocidadY += 0.4f;
    }
    
    public void pos(){
        x[0] = -velocidadX + x[0];
        x[1] = -velocidadX + x[1];
        x[2] = velocidadX + x[2];
        x[3] = velocidadX + x[3];
        
        y[0] = velocidadY + y[0];
        y[1] = (float) (velocidadY + y[1] - 2);
        y[2] = velocidadY + y[2];
        y[3] = (float) (velocidadY + y[3] - 2);
        
        aplicarGravedad();
    }
    
    public boolean tocaDesaparecer(){
        return (y[1] > 201);//alto de pantalla                    
    }
    
    public void mostrar(Graphics g){
        for(int i = 0; i<4; i++){
            g.drawImage(escombros[i], (int) x[i], (int) y[i], (int) ancho, (int) alto, null);
        }
    }
}
