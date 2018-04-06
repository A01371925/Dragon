package mx.itesm.dragon.Objetos;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;

public class Dragon {

    private static final int TILEWIDTH = 384;
    private static final int TILEHEIGHT = 202;
    private static final int TEXTURE_REGION = 8;
    private static final float SPEED = 0.125f;

    private Texture dragon;
    private Animacion animacion;

    public Dragon(String textura) {
        dragon = new Texture(textura);
        animacion = new Animacion(dragon, TILEWIDTH, TILEHEIGHT, TEXTURE_REGION, SPEED);
    }

    public Dragon(String textura, int tileWidth, int tileHeight, int textureRegion, float speed) {
        dragon = new Texture(textura);
        animacion = new Animacion(dragon, tileWidth, tileHeight, textureRegion, speed);

    }
    public Animation animacion() {
        return animacion.animacionHorizontal();
    }

}