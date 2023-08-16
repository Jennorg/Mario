/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game.Objeto.Bloques;

import game.Graficos.Animacion;
import game.Objeto.Bloques.Bloque;
import game.Graficos.Textura;
import game.Objeto.Items.Hongo;
import game.Objeto.Items.Item;
import game.manager.ObjetoManager;
import game.manager.TimeManager;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

/**
 *
 * @author user
 */
public class ItemBloque extends Bloque{
    
    Item item;
    int indexAccion;
    Animacion animacion;
    TimeManager timer;
    ObjetoManager manager;

    public ItemBloque(float X, float Y, float ancho, float alto, int index, int escala, BufferedImage[] sprite, int indexAccion, Item item) {
        super(X, Y, ancho, alto, index, escala, sprite);
        this.item = item;
        this.indexAccion = indexAccion;
        animacion = new Animacion(12, sprite[index+2], sprite[index], sprite[index+1]);
        timer = new TimeManager();

    }    
    
    @Override
    public void pos(){           
        if(isGolpeado()){            
            if(item.getY() > getY() - getAlto()) item.setFuera(true);
            else item.setFuera(false);
            animacion.anima();
        }
                
        if (animacion.getContador() == 2) {
        // Si es cero, detener la animación durante 1 segundo
            if (timer.HapasadoElTiempo(50)) {
                timer.reset();
                animacion.anima();
            }
        } else {
            // Si el contador no es cero, continuar la animación normalmente
            animacion.anima();
        }
                
    }
    
    @Override
    public void mostrar(Graphics2D g){
        if(golpeado){
            g.drawImage(sprite[indexAccion], (int) getX(), (int) getY(), (int) getAncho(), (int) getAlto(), null);
            item.mostrar(g);
            item.setFuera(true);
        } else {
           animacion.dibujarSprite(g, (int) getX(), (int) getY(), (int) getAncho(), (int) getAlto());
        }
    }

    public Item getItem() {
        return item;
    }
    
}
