package game.Objeto;

import game.Graficos.Textura;
import game.manager.GamePanel;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Bloque extends Objeto_Juego{
    
    private Textura textura;

    private int index;
    private BufferedImage[] sprite;
    private boolean golpeado;
    private Escombro escombro;
    
    public Bloque(float X, float Y, float ancho, float alto, int index, int escala, Textura textura) {
        super(X, Y, Objeto.Bloque, ancho, alto, escala);
        this.textura = textura;
        this.index = index;
        sprite = textura.getCasilla1();
    }

    @Override
    public void pos() {
        if(golpeado){
            escombro.pos();
        }
    }

    public boolean tocaDesaparecer(){
        return escombro.tocaDesaparecer();
    }    
    
    @Override
    public void mostrar(Graphics2D g) {
        if(!golpeado){
            g.drawImage(sprite[index], (int) getX(), (int) getY(), (int) getAncho(), (int) getAlto(), null);
        } else {
            escombro.mostrar(g);
        }
        
        
        g.setColor(Color.red);
        g.draw(getBounds());
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int) getX(), (int) getY(), (int) getAncho(), (int) getAlto());
    }
    
    public void golpea(){
        golpeado = true;
        escombro = new Escombro(getX(), getY(), getAncho(), getAlto(), getEscala(), textura);
    }
}
