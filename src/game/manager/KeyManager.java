package game.manager;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyManager implements KeyListener {
    
    public boolean arriba, abajo, derecha, izquierda;
    
    @Override
    public void keyTyped(KeyEvent e){
        
    }
    
    @Override
    public void keyPressed(KeyEvent e){
        int codigo = e.getKeyCode();
        
        if(codigo == KeyEvent.VK_W){
            arriba = true;
        }
        if(codigo == KeyEvent.VK_A){
            izquierda = true;
        }
        if(codigo == KeyEvent.VK_S){
            abajo = true;
        }
        if(codigo == KeyEvent.VK_D){
            derecha = true;
        }
    }
    
    @Override
    public void keyReleased(KeyEvent e){
        int codigo = e.getKeyCode();
        
        if(codigo == KeyEvent.VK_W){
            arriba = false;
        }
        if(codigo == KeyEvent.VK_A){
            izquierda = false;
        }
        if(codigo == KeyEvent.VK_S){
            abajo = false;
        }
        if(codigo == KeyEvent.VK_D){
            derecha = false;
        }
    }
}
