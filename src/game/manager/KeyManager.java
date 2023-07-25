package game.manager;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyManager implements KeyListener {
    
    public boolean arriba, abajo, derecha, izquierda;
    private int i;
    
    public KeyManager(int i){
        this.i = i;
    }
    
    @Override
    public void keyTyped(KeyEvent e){
        
    }
    
    @Override
    public void keyPressed(KeyEvent e){
        int codigo = e.getKeyCode();
        
        switch(i){
            case 1:
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
                break;
            
            case 2:
                if (codigo == KeyEvent.VK_UP) {
                    arriba = true;
                }
                if (codigo == KeyEvent.VK_LEFT) {
                    izquierda = true;
                }
                if (codigo == KeyEvent.VK_DOWN) {
                    abajo = true;
                }
                if (codigo == KeyEvent.VK_RIGHT) {
                    derecha = true;
                }
                break;
        }
    }
    
    @Override
    public void keyReleased(KeyEvent e){
        int codigo = e.getKeyCode();
        
        switch(i){
            case 1:
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
                break;
            
            case 2:
                if (codigo == KeyEvent.VK_UP) {
                    arriba = false;
                }
                if (codigo == KeyEvent.VK_LEFT) {
                    izquierda = false;
                }
                if (codigo == KeyEvent.VK_DOWN) {
                    abajo = false;
                }
                if (codigo == KeyEvent.VK_RIGHT) {
                    derecha = false;
                }
                break;
        }
    }
}
