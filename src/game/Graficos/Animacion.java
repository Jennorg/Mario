package game.Graficos;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class Animacion {
    private int velocidadFrame;
    
    private BufferedImage[] sprite;
    public BufferedImage spriteActual;
    
    private int contador = 0;
    private int index = 0;
    
    private int frames;
    
    public Animacion(int velocidad, BufferedImage... args){
        velocidadFrame = velocidad;
        sprite = new BufferedImage[args.length];
        
        System.arraycopy(args, 0, sprite, 0, args.length);
        
        frames = args.length;
    }
    
    public void anima(){
        index++;
        if(index > velocidadFrame){
            index = 0;
            siguienteFrame();
        }
    }
    
    
    private void siguienteFrame(){
        spriteActual = sprite[contador];
        contador++;
        
        if(contador >= frames){
            contador = 0;
        }
    }
    
    public void dibujarSprite(Graphics2D g2, int x, int y, int ancho, int alto){
        g2.drawImage(spriteActual, x, y, ancho, alto, null);
    }

    public int getContador() {
        return contador;
    }
    
    
}

