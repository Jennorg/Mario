package game.manager;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyManager2 implements KeyListener {
    
    public boolean arriba, abajo, derecha, izquierda;
    
    @Override
    public void keyTyped(KeyEvent e){
        
    }
    
    @Override
    public void keyPressed(KeyEvent e){
        int codigo = e.getKeyCode();
        
        if(codigo == KeyEvent.VK_I){
            arriba = true;
        }
        if(codigo == KeyEvent.VK_J){
            izquierda = true;
        }
        if(codigo == KeyEvent.VK_K){
            abajo = true;
        }
        if(codigo == KeyEvent.VK_L){
            derecha = true;
        }
    }
    
    @Override
    public void keyReleased(KeyEvent e){
        int codigo = e.getKeyCode();
        
        if(codigo == KeyEvent.VK_I){
            arriba = false;
        }
        if(codigo == KeyEvent.VK_J){
            izquierda = false;
        }
        if(codigo == KeyEvent.VK_K){
            abajo = false;
        }
        if(codigo == KeyEvent.VK_L){
            derecha = false;
        }
    }
}
