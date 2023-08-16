/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game.Objeto.Bloques;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class LadrilloBloque extends Bloque{
    
    Escombro escombro;
    
    public LadrilloBloque(int x, int y, int ancho, int alto, int index, int escala, BufferedImage[] sprite, BufferedImage[] spriteEscombro){
        super(x, y, ancho, alto, index, escala, sprite);
        escombro = new Escombro(getX(), getY(), getAncho(), getAlto(), getEscala(), spriteEscombro);
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
    }

    @Override
    public Rectangle getBorde() {
        return new Rectangle((int) getX(), (int) getY(), (int) getAncho(), (int) getAlto());
    }
}
