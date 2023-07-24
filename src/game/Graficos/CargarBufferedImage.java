package game.Graficos;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class CargarBufferedImage{
    private BufferedImage imagen;
    
    public BufferedImage cargarImagen(String rutaArchivo){
        try{
           imagen = ImageIO.read(getClass().getResourceAsStream(rutaArchivo));
        }catch(IOException e){
            e.getStackTrace();
        }
        
        return imagen;
    }
}
