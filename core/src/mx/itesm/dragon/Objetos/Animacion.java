package mx.itesm.dragon.Objetos;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Animacion {

    private Texture textura;
    private TextureRegion[] frames;
    private Animation animacion;
    private int tileWidth, tileHeight, textureRegion;
    private float speed;

    public Animacion(Texture textura, int tileWidth, int tileHeight, int textureRegion, float speed) {
        this.textura = textura;
        this.tileWidth = tileWidth;
        this.tileHeight = tileHeight;
        this.textureRegion = textureRegion;
        this.speed = speed;
    }

    public Animation animacion() {
        TextureRegion[][] tmp = TextureRegion.split(textura, tileWidth, tileHeight);
        frames = new TextureRegion[textureRegion];
        for (int i = 0; i < frames.length; i++) {
            frames[i] = tmp[0][i];
        }
        animacion = new Animation(speed, frames);
        return animacion;
    }
}
