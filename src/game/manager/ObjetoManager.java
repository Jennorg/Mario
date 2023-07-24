package game.manager;

import game.Entidades.Player;
import game.Objeto.Bloque;
import game.Objeto.Objeto_Juego;
import java.awt.Graphics2D;
import java.util.LinkedList;
import java.util.List;

public class ObjetoManager {
    private List<Objeto_Juego> objetos;
    private Player player;
    
    public ObjetoManager(){
        objetos = new LinkedList<Objeto_Juego>();
    }
    
    public void enlazar(){
        for(Objeto_Juego objeto: objetos){
            objeto.pos();
        }
        
        LinkedList<Bloque> bloquesEliminados = player.getYResetLinkedListBloqueEliminado();
        for(Bloque bloqueEliminado : bloquesEliminados){
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
    
    public int setPlayer(Player player){
        if(this.player != null){
            return -1;
        }
                
        this.player = player;
        objetos.add(player);
        return 0;
    }
    
    public int elimnarPlayer(Player player){
        if(this.player == null){
            return -1;
        }
        
        objetos.remove(player);
        this.player = null;
        return 0;
    }
    
    public Player getPlayer(){
        return player;
    }
    
}
