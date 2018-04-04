package mx.itesm.dragon.Objetos;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Dragon {

    private static final int TILEWIDTH = 384;
    private static final int TILEHEIGHT = 202;

    private Texture dragon;
    private TextureRegion[] frames;
    private Animation animacion;

    public Dragon(String textura) {
        dragon = new Texture(textura);
    }

    public Animation animacion() {
        TextureRegion[][] tmp = TextureRegion.split(dragon, TILEWIDTH, TILEHEIGHT);
        frames = new TextureRegion[9];
        for (int i = 0; i < frames.length; i++) {
            frames[i] = tmp[0][i];
        }
        animacion = new Animation(0.125f, frames);
        return animacion;
    }

}