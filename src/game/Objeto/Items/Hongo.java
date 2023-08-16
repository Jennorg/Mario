package game.Objeto.Items;

import game.Objeto.Objeto;
import game.Objeto.Objeto_Juego;
import game.manager.ObjetoManager;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Hongo extends Item {
    
    ObjetoManager objManager;
    
    boolean chocando = false;
    
    public Hongo(float X, float Y, float ancho, float alto, int scale, BufferedImage sprite, ObjetoManager objManager) {
        super(X, Y, ancho, alto, scale, objManager, sprite);
        setVelocidadX(0);
        setVelocidadY(-1);
    }

      
    
    public boolean isChocando() {
        return chocando;
    }
}
