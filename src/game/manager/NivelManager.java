package game.manager;

import game.Entidades.Player;
import game.Graficos.CargarBufferedImage;
import game.Graficos.Textura;
import game.Objeto.Bloque;
import game.Objeto.Tubo;
import java.awt.image.BufferedImage;

public class NivelManager {
    
    private CargarBufferedImage loader;
    private BufferedImage texturaNivel;
    private ObjetoManager manager;    
    private KeyManager control;
    private Textura textura;
    
    public NivelManager(ObjetoManager manager, KeyManager key, Textura textura){
        this.manager = manager;
        this.textura = textura;
        loader = new CargarBufferedImage();
        control = key;
    }
    
    public void cargar(){
        setNivel("/res/images/niveles/1.png");
    }
    
    public void setNivel(String rutaNivel){
        this.texturaNivel = loader.cargarImagen(rutaNivel);
        
        int ancho = this.texturaNivel.getWidth();
        int alto = this.texturaNivel.getHeight();
        
        for(int i=0; i<ancho; i++){
            for(int j=0; j<alto; j++){
                int pixel = this.texturaNivel.getRGB(i, j);
                int rojo = (pixel >> 16) & 0xff;
                int verde = (pixel >> 8) & 0xff;
                int azul = pixel & 0xff;
                
                
                if(rojo == 255 && verde == 255 && azul == 255) continue;
                if(rojo == 0 && verde == 0 && azul == 0) continue;
                
                if(rojo == 185 && verde == 122 && azul == 87){
                    manager.agregarObj(new Bloque(i*16, j*16, 16, 16, 0, 2, textura));
                } else if(rojo == 255 && verde == 242 && azul == 0){
                    manager.agregarObj(new Bloque(i*16, j*16, 16, 16, 24, 2, textura));
                } else if(rojo == 255 && verde == 127 && azul == 39){
                    manager.agregarObj(new Bloque(i*16, j*16, 16, 16,2, 2, textura));
                } else if(rojo == 34 && verde == 177 && azul == 76){
                    manager.agregarObj(new Tubo(i*16, j*16, 16, 16, 0, 2, textura));
                } else if(rojo == 181 && verde == 230 && azul == 29){
                    manager.agregarObj(new Tubo(i*16, j*16, 16, 16, 1, 2, textura));
                } else if(rojo == 255 && verde == 201 && azul == 14){
                    manager.agregarObj(new Bloque(i*16, j*16, 16, 16, 28, 2, textura));
                } else if(rojo == 255 && verde == 0 && azul == 0){
                    manager.setPlayer(new Player(control, manager, i*16, j*16, 3, textura));
                }
                
            }
        }
    }
    
    
    
}
