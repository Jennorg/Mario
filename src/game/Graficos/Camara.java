package game.Graficos;

import game.Objeto.Objeto_Juego;
import game.manager.GamePanel;

public class Camara {
    private int x, y;
    GamePanel gamePanel;
    
    public Camara(int x, int y, GamePanel gamePanel){
        this.x = x;
        this.y = y;
        this.gamePanel = gamePanel;
    }
    
    public void pos(Objeto_Juego player) {
        x = (int) (-player.getX() + gamePanel.anchoMaximo / 2);
        y = (int) (gamePanel.altoMaximo - gamePanel.altoCamara*0.6f);
    }

    
    public void setX(int x){
        this.x = x;
    }
    
    public void setY(int y){
        this.y = y;
    }
    
    public int getX(){
        return x;
    }
    
    public int getY(){
        return y;
    }
}
