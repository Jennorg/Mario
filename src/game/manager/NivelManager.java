package game.manager;

import game.Entidades.Enemy;
import game.Entidades.Player;
import game.Graficos.CargarBufferedImage;
import game.Graficos.Textura;
import game.Objeto.Bloques.Bloque;
import game.Objeto.Bloques.ItemBloque;
import game.Objeto.Bloques.LadrilloBloque;
import game.Objeto.Items.Hongo;
import game.Objeto.Items.Item;
import game.Objeto.Objeto;
import game.Objeto.Tubo;
import java.awt.image.BufferedImage;

public class NivelManager {
    
    private CargarBufferedImage loader;
    private BufferedImage texturaNivel;
    private ObjetoManager manager;    
    private KeyManager control1, control2;
    private Textura textura;
    private Reproductor reproductor;
    
    boolean multiplayer;
    
    public NivelManager(ObjetoManager manager, KeyManager key1, KeyManager key2, Textura textura, Reproductor reproductor, boolean multiplayer){
        this.manager = manager;
        this.textura = textura;
        this.reproductor = reproductor;
        loader = new CargarBufferedImage();
        control1 = key1;
        control2 = key2;
        this.multiplayer = multiplayer;
    }
    
    public void cargar(){
        if(!multiplayer){
            setNivel("/res/images/niveles/1 - sin luigi.png");
        } else {
            setNivel("/res/images/niveles/1.png");
        }
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
                    manager.agregarObj(new Bloque(i*16, j*16, 16, 16, 0, 2, textura.getCasilla1()));
                } else if(rojo == 255 && verde == 242 && azul == 0){//ItemBloque
                    manager.agregarObj(
                            new ItemBloque(i*16, j*16, 16, 16, 24,
                                2, textura.getCasilla1(), 27, 
                                new Hongo(i*16, j*16, 16, 16,
                                    2, textura.getHongo()[0],manager)));
                } else if(rojo == 130 & verde == 255 && azul == 255){
                    manager.agregarObj(
                            new ItemBloque(i*16, j*16, 16, 16, 24,
                                2, textura.getCasilla1(), 27, 
                                new Item(i*16, j*16, 16, 16,
                                    2, manager, textura.getMoneda())));
                }else if(rojo == 255 && verde == 127 && azul == 39){// Ladrilllo
                    manager.agregarObj(new LadrilloBloque(i*16, j*16, 16, 16,2, 2, textura.getCasilla1(), textura.getEscombros1()));
                } else if(rojo == 34 && verde == 177 && azul == 76){
                    manager.agregarObj(new Tubo(i*16, j*16, 16, 16, 0, 2, textura));
                } else if(rojo == 181 && verde == 230 && azul == 29){
                    manager.agregarObj(new Tubo(i*16, j*16, 16, 16, 1, 2, textura));
                } else if(rojo == 255 && verde == 201 && azul == 14){
                    manager.agregarObj(new Bloque(i*16, j*16, 16, 16, 28, 2, textura.getCasilla1()));
                } else if(rojo == 255 && verde == 0 && azul == 0){
                    manager.setPlayer(new Player(control1, manager, i*16, j*16, 3, textura, 1, reproductor), 1);
                } else if(rojo == 0 && verde == 255 && azul == 0){
                    manager.setPlayer(new Player(control2, manager, i*16, j*16, 3, textura,2, reproductor), 2);
                } else if(rojo == 230 && verde == 140 && azul == 0){
                    manager.agregarObj(new Enemy(i*16, j*16, Objeto.Enemigo, 16, 16, 2, textura.getKoopa1(), manager));
                } else if(rojo == 255 && verde == 255 && azul == 0){
                    manager.agregarObj(new Item(i*16, j*16, 16, 16, 2, manager, textura.getEstrella()));
                } else if(rojo == 180 && verde == 230 && azul == 30){
                    manager.agregarObj(new Enemy(i*16, j*16, Objeto.Enemigo, 16, 16, 2, textura.getTortuga1(), manager));
                }
                
            }
        }
    }
    
    
    
}
