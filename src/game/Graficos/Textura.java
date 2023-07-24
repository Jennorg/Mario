package game.Graficos;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Textura {
    private final int NUMBER_PLAYER_G_SPRITES = 21;
    private final int NUMBER_PLAYER_P_SPRITES = 14;
    
    private final int NUMBER_CASILLA_1 = 28;
    private final int NUMBER_CASILLA_2 = 33;
    
    private CargarBufferedImage loader;
    private BufferedImage playerSheet, enemySheet, casillaSheet, bloqueSheet;
    private BufferedImage[] marioPeque, marioGrande, luigiPeque, luigiGrande;
    private BufferedImage[] casilla_1, casilla_2, casilla_3, casilla_4, tubo_1;
    private BufferedImage[] escombros_1;
    
    public Textura(){
        marioPeque = new BufferedImage[NUMBER_PLAYER_P_SPRITES];
        luigiPeque = new BufferedImage[NUMBER_PLAYER_P_SPRITES];
        marioGrande = new BufferedImage[NUMBER_PLAYER_G_SPRITES];
        luigiGrande = new BufferedImage[NUMBER_PLAYER_G_SPRITES];
        
        casilla_1 = new BufferedImage[NUMBER_CASILLA_1 + NUMBER_CASILLA_2];
        casilla_2 = new BufferedImage[NUMBER_CASILLA_1 + NUMBER_CASILLA_2];
        casilla_3 = new BufferedImage[NUMBER_CASILLA_1 + NUMBER_CASILLA_2];
        casilla_4 = new BufferedImage[NUMBER_CASILLA_1 + NUMBER_CASILLA_2];
        tubo_1 = new BufferedImage[4];
        escombros_1 = new BufferedImage[4];
               
        loader = new CargarBufferedImage();
        playerSheet = loader.cargarImagen("/res/images/sheets/mario-luigi-sheet.png");
        casillaSheet = loader.cargarImagen("/res/images/sheets/tiles-sheet.png");
        bloqueSheet = loader.cargarImagen("/res/images/sheets/blocks-sheet.png");
        getPlayerTextura();
        getCasillaTextura();
        getTuboTextura();
        getEscombroTextura();
    }
    
    private void getPlayerTextura(){
        int xImage = 80;
        
        int yImage = 1;
        int ancho = 16;
        int alto = 32;
        
        for(int i=0; i<NUMBER_PLAYER_G_SPRITES; i++){
            marioGrande[i] = playerSheet.getSubimage(xImage + i*(ancho+1), yImage, ancho, alto);
        }
        
        yImage += alto+1;
        alto = 16;
        
        for(int i=0; i < NUMBER_PLAYER_P_SPRITES; i++){
            marioPeque[i] = playerSheet.getSubimage(xImage + i*(ancho+1), yImage, ancho, alto);
        }
    }
    
    private void getCasillaTextura(){
        int xImage = 0;
        int yImage = 0;
        int alto = 16;
        int ancho = 16;
        
        for(int i=0; i<4; i++){
            for(int j=0; j<NUMBER_CASILLA_1; j++){
                switch (i) {
                    case 0:
                        casilla_1[j] = casillaSheet.getSubimage(xImage + j*ancho, yImage + i*alto*2,       ancho, alto);
                        break;
                    case 1:
                        casilla_2[j] = casillaSheet.getSubimage(xImage + j*ancho, yImage + i*alto*2,       ancho, alto);
                        break;
                    case 2:
                        casilla_3[j] = casillaSheet.getSubimage(xImage + j*ancho, yImage + i*alto*2,       ancho, alto);
                        break;
                    case 3:
                        casilla_4[j] = casillaSheet.getSubimage(xImage + j*ancho, yImage + i*alto*2,       ancho, alto);
                        break;
                    default:
                        break;
                }
            }
        }
        
        yImage += alto;
        
        for(int i=0; i<4; i++){
            for(int j=0; j<NUMBER_CASILLA_2; j++){
                switch (i) {
                    case 0:
                        casilla_1[j + NUMBER_CASILLA_1] = casillaSheet.getSubimage(xImage + j*ancho, yImage,       ancho, alto);
                        break;
                    case 1:
                        casilla_2[j + NUMBER_CASILLA_1] = casillaSheet.getSubimage(xImage + j*ancho, yImage,       ancho, alto);
                        break;
                    case 2:
                        casilla_3[j + NUMBER_CASILLA_1] = casillaSheet.getSubimage(xImage + j*ancho, yImage,       ancho, alto);
                        break;
                    case 3:
                        casilla_4[j + NUMBER_CASILLA_1] = casillaSheet.getSubimage(xImage + j*ancho, yImage,       ancho, alto);
                        break;
                    default:
                        break;
                }
            }
        }
    }
    
    private void getTuboTextura(){
        int xImage = 0;
        int yImage = 16*8;
        int alto = 16;
        int ancho = 32;
        
        tubo_1[0] = casillaSheet.getSubimage(xImage, yImage, ancho, alto);
        tubo_1[1] = casillaSheet.getSubimage(xImage, yImage + alto, ancho, alto);
        tubo_1[2] = casillaSheet.getSubimage(xImage + ancho, yImage, ancho, alto);
        tubo_1[3] = casillaSheet.getSubimage(xImage + ancho, yImage + alto, ancho, alto);
        
    }
    
    private void getEscombroTextura(){
        int xImage = 304;
        int yImage = 112;
        int alto = 8;
        int ancho = 8;
        
        escombros_1[0] = bloqueSheet.getSubimage(xImage, yImage, ancho, alto);
        escombros_1[1] = bloqueSheet.getSubimage(xImage, yImage + alto, ancho, alto);
        escombros_1[2] = bloqueSheet.getSubimage(xImage + ancho, yImage, ancho, alto);
        escombros_1[3] = bloqueSheet.getSubimage(xImage + ancho, yImage + alto, ancho, alto);
    }
    
    public BufferedImage[] getMarioPeque(){
        return marioPeque;
    }
    
    public BufferedImage[] getMarioGrande(){
        return marioGrande;
    }
    
    public BufferedImage[] getLuigiPeque(){
        return luigiPeque;
    }
    
    public BufferedImage[] getLuigiGrande(){
        return luigiGrande;
    }
    
    public BufferedImage[] getCasilla1(){
        return casilla_1;
    }
    
    public BufferedImage[] getCasilla2(){
        return casilla_2;
    }
    
    public BufferedImage[] getCasilla3(){
        return casilla_3;
    }
    
    public BufferedImage[] getCasilla4(){
        return casilla_4;
    }
    
    public BufferedImage[] getTubo1(){
        return tubo_1;
    }
    
    public BufferedImage[] getEscombros1(){
        return escombros_1;
    }
}
