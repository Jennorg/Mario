package game.Objeto;

import game.Graficos.Animacion;
import game.Graficos.Textura;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;


public class Estrella extends Objeto_Juego {

    Textura textura;
    
    private BufferedImage[] sprite;
    private Animacion animacion;
    
    public Estrella(float X, float Y, Objeto ID, float ancho, float alto, int scale, Textura textura) {
        super(X, Y, ID, ancho, alto, scale);
        
        sprite = textura.getEstrella();
        animacion = new Animacion(4, sprite);        
    }

    @Override
    public void pos() {
        animacion.anima();
    }

    @Override
    public void mostrar(Graphics2D g) {
        animacion.dibujarSprite(g, (int) getX(), (int) getY(), (int) getAncho(), (int) getAlto());
    }

    @Override
    public Rectangle getBorde() {
        return new Rectangle((int) getX(), (int) getY(), (int) getAncho(), (int) getAlto());
    }
    
}
