package game.manager;

import game.Entidades.Player;
import game.Objeto.Bloque;
import game.Objeto.Objeto_Juego;
import java.awt.Graphics2D;
import java.util.LinkedList;
import java.util.List;

public class ObjetoManager {
    private List<Objeto_Juego> objetos;
    private Player player1;
    private Player player2;
    
    public ObjetoManager(){
        objetos = new LinkedList<Objeto_Juego>();
    }
    
    public void enlazar(){
        for(Objeto_Juego objeto: objetos){
            objeto.pos();
        }
        
        LinkedList<Objeto_Juego> bloquesEliminados = player1.getYResetLinkedListBloqueEliminado();
        for(Objeto_Juego bloqueEliminado : bloquesEliminados){
            eliminarObj(bloqueEliminado);
        }
    }
    
    public void mostrar(Graphics2D g2){
        for(Objeto_Juego objeto: objetos){
            objeto.mostrar(g2);
        }
    }
    
    public void agregarObj(Objeto_Juego obj){
        objetos.add(obj);
    }
    
    public void eliminarObj(Objeto_Juego obj){
        objetos.remove(obj);
    }
    
    public List<Objeto_Juego> getLista(){
        return objetos;
    }
    
    public int setPlayer(Player player, int i){
        switch (i) {
            case 1:
                if(this.player1 != null){
                    return -1;
                }   this.player1 = player;
                objetos.add(player);
                break;
            case 2:
                if(this.player2 != null){
                    return -1;
                }   this.player2 = player;
                objetos.add(player);
                break;
            default:
                return -1;
        }
        
        return 0;
    }
    
    public int elimnarPlayer1(Player player){
        if(this.player1 == null){
            return -1;
        }
        
        objetos.remove(player);
        this.player1 = null;
        return 0;
    }
    
    public Player getPlayer(int i){
        switch (i) {
            case 1:
                return player1;
                
            case 2:
                return player2;
        }
        
        return null;
    }
    
    
}
