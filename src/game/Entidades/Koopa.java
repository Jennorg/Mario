package game.Entidades;

import game.Graficos.Textura;
import game.Objeto.Objeto;
import game.Objeto.Objeto_Juego;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Koopa extends Objeto_Juego {

    private Textura textura;

    private int index;
    private BufferedImage[] sprite;
    
    int velocidadX = -5;
    int velocidadY = 0;
    
    public Koopa(float X, float Y, Objeto ID, float ancho, float alto, int scale, Textura textura) {
        super(X, Y, ID, ancho, alto, scale);
        this.textura = textura;
        sprite = textura.getKoopa1();
    }

    @Override
    public void pos() {
        setX(getVelocidadX() + getX());
        setY(getVelocidadY() + getY());
    }

    @Override
    public void mostrar(Graphics2D g) {
        g.drawImage(sprite[index], (int) getX(), (int) getY(), (int) getAncho(), (int) getAlto(), null);
        
        g.setColor(Color.red);
        g.draw(getBorde());
    }

    @Override
    public Rectangle getBorde() {
        return new Rectangle((int) getX(), (int) getY(), (int) getAncho(), (int) getAlto());
    }
    
    
    
}
