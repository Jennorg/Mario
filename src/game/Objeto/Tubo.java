package game.Objeto;

import game.Graficos.Textura;
import game.manager.GamePanel;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;


    
public class Tubo extends Objeto_Juego{
    
    private Textura textura;

    private int index;
    private BufferedImage[] sprite;
    
    public Tubo(float X, float Y, float ancho, float alto, int index, int escala, Textura textura) {
        super(X, Y, Objeto.Tubo, ancho, alto, escala);
        this.textura = textura;
        this.index = index;
        sprite = textura.getTubo1();
    }

    @Override
    public void pos() {
        
    }

    @Override
    public void mostrar(Graphics2D g) {
        g.drawImage(sprite[index], (int) getX(), (int) getY(), (int) getAncho()*2, (int) getAlto(), null);
        
        g.setColor(Color.red);
        g.draw(getBounds());
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int) getX(), (int) getY(), (int) getAncho()*2, (int) getAlto());
    }
    
}