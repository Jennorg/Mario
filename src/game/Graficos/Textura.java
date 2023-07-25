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
    private BufferedImage playerSheet, enemySheet, casillaSheet, bloqueSheet, itemsSheet;
    private BufferedImage[] marioPeque, marioGrande, luigiPeque, luigiGrande;
    private BufferedImage[] koopa_1, koopa_2, koopa_3;
    private BufferedImage[] tortuga_1, tortuga_2, tortuga_3;
    private BufferedImage[] casilla_1, casilla_2, casilla_3, casilla_4, tubo_1;
    private BufferedImage[] escombros_1;
    private BufferedImage[] hongo, hongoConCarita, flor, estrella;    
    
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
        koopa_1 = new BufferedImage[3];
        koopa_2 = new BufferedImage[3];
        koopa_3 = new BufferedImage[3];
               
        tortuga_1 = new BufferedImage[2];
        tortuga_2 = new BufferedImage[2];
        tortuga_3 = new BufferedImage[2];
        
        hongo = new BufferedImage[3];
        hongoConCarita = new BufferedImage[3];
        flor = new BufferedImage[4];
        estrella = new BufferedImage[4];
        
        loader = new CargarBufferedImage();
        playerSheet = loader.cargarImagen("/res/images/sheets/mario-luigi-sheet.png");
        enemySheet = loader.cargarImagen("/res/images/sheets/enemy-sheet.png");
        casillaSheet = loader.cargarImagen("/res/images/sheets/tiles-sheet.png");
        bloqueSheet = loader.cargarImagen("/res/images/sheets/blocks-sheet.png");
        itemsSheet = loader.cargarImagen("/res/images/sheets/items-sheet.png");
        getPlayerTextura();
        getEnemyTextura();
        getCasillaTextura();
        getTuboTextura();
        getEscombroTextura();
        getItemTextura();
    }
    
    private void getPlayerTextura(){
        int xImage = 80;
        
        int yImage = 1;
        int ancho = 16;
        int alto = 32;
        
        for(int i=0; i<NUMBER_PLAYER_G_SPRITES; i++){
            marioGrande[i] = playerSheet.getSubimage(xImage + i*(ancho+1), yImage, ancho, alto);
            luigiGrande[i] = playerSheet.getSubimage(xImage + i*(ancho+1), yImage + 65, ancho, alto);
        }
        
        yImage += alto+1;
        alto = 16;
        
        for(int i=0; i < NUMBER_PLAYER_P_SPRITES; i++){
            marioPeque[i] = playerSheet.getSubimage(xImage + i*(ancho+1), yImage, ancho, alto);
            luigiPeque[i] = playerSheet.getSubimage(xImage + i*(ancho+1), yImage + 65, ancho, alto);
        }
        
        
    }
    
    private void getEnemyTextura(){        
        int xImage = 0;
        
        int yImage = 16;
        int ancho = 16;
        int alto = 16;
        
        for(int j = 0; j < 2; j++){
            
            switch(j){
                case 0:
                    for(int i=0; i<3; i++){
                        koopa_1[i] = enemySheet.getSubimage(xImage + (ancho*i), yImage, ancho, alto);
                    }
                    yImage += 2*alto;

                    for(int i=0; i < 3; i++){
                        koopa_2[i] = enemySheet.getSubimage(xImage + (ancho*i), yImage, ancho, alto);
                    }
                    
                    yImage += 2*alto;
                    
                    for(int i=0; i < 3; i++){
                        koopa_3[i] = enemySheet.getSubimage(xImage + (ancho*i), yImage, ancho, alto);
                    }                    
                    break;
                    
                case 1:
                    xImage = 96;
                    yImage = 9;
                    alto = 22;
                    
                    
                    for(int i=0; i<2; i++){
                        tortuga_1[i] = enemySheet.getSubimage(xImage + (ancho*i), yImage, ancho, alto);
                    }
                    
                    yImage += alto+16;

                    for(int i=0; i<2; i++){
                        tortuga_2[i] = enemySheet.getSubimage(xImage + (ancho*i), yImage, ancho, alto);
                    }
                    
                    yImage += alto+16;

                    for(int i=0; i<2; i++){
                        tortuga_3[i] = enemySheet.getSubimage(xImage + (ancho*i), yImage, ancho, alto);
                    }                    
                    break;

            }
                        
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
    
    private void getItemTextura(){
        int xImage = 0;
        int yImage = 0;
        int alto = 16;
        int ancho = 16;
        for(int i = 0; i<4; i++){
            for(int j = 0; j<3; j++){
                switch(j){
                    case 0:
                        hongo[j] = itemsSheet.getSubimage(xImage + (j*ancho), yImage, ancho, alto);
                        hongoConCarita[j] = itemsSheet.getSubimage(xImage + j*ancho, yImage + alto, ancho, alto);
                        break;
                        
                    case 1:
                        hongo[j] = itemsSheet.getSubimage(xImage + (j*ancho), yImage, ancho, alto);
                        hongoConCarita[j] = itemsSheet.getSubimage(xImage + j*ancho, yImage + alto, ancho, alto);
                        break;
                    
                    case 2:
                        hongo[j] = itemsSheet.getSubimage(xImage + (j*ancho), yImage, ancho, alto);
                        hongoConCarita[j] = itemsSheet.getSubimage(xImage + j*ancho, yImage + alto, ancho, alto);
                        break;
                }
            }
            
            
            flor[i] = itemsSheet.getSubimage(xImage + i*ancho, yImage + 2*alto, ancho, alto);
            estrella[i] = itemsSheet.getSubimage(xImage + i*ancho, yImage + 3*alto, ancho, alto);
            
            
        }
        yImage += alto;
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
    
    public BufferedImage[] getKoopa1(){
        return koopa_1;
    }
    
    public BufferedImage[] getKoopa2(){
        return koopa_2;
    }
    
    public BufferedImage[] getKoopa3(){
        return koopa_3;
    }
    
    public BufferedImage[] getTortuga1(){
        return tortuga_1;
    }
    
    public BufferedImage[] getTortuga2(){
        return tortuga_2;
    }
    
    public BufferedImage[] getTortuga3(){
        return tortuga_3;
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
    
    public BufferedImage[] getHongo(){
        return hongo;
    }
    
    public BufferedImage[] getHongoConCarita(){
        return hongoConCarita;
    }
    
    public BufferedImage[] getFlor(){
        return flor;
    }
    
    public BufferedImage[] getEstrella(){
        return estrella;
    }
}