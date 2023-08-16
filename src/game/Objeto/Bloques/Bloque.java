package game.Objeto.Bloques;

import game.Graficos.Textura;
import game.Objeto.Objeto;
import game.Objeto.Objeto_Juego;
import game.manager.GamePanel;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Bloque extends Objeto_Juego{
    

    protected int index;
    protected BufferedImage[] sprite;
    protected boolean golpeado = false;
    
    public Bloque(float X, float Y, float ancho, float alto,  int index, int escala, BufferedImage[] sprite) {
        super(X, Y, Objeto.Bloque, ancho, alto, escala);
        this.index = index;
        this.sprite = sprite;
    }
    
    @Override
    public void pos(){
        
    }
    
    @Override
    public void mostrar(Graphics2D g) {
        g.drawImage(sprite[index], (int) getX(), (int) getY(), (int) getAncho(), (int) getAlto(), null);
    }

    @Override
    public Rectangle getBorde() {
        return new Rectangle((int) getX(), (int) getY(), (int) getAncho(), (int) getAlto());
    }
    
    
    public void golpea(){
        golpeado = true;
    }

    public boolean isGolpeado() {
        return golpeado;
    }
    
    
}
