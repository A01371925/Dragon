package mx.itesm.dragon.Objetos;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;


public class Proyectil {

    private static final int TILEWIDTH = 38;
    private static final int TILEHEIGHT = 76;
    private Sprite sprite;
    private static final int SPEED = 20;
    private TextureRegion[] frames;
    private Animation animacion;
    private float elapsedTime;

    public Proyectil() {
        sprite = new Sprite();
    }

    public Proyectil(Texture textura) {
        sprite = new Sprite(textura);
    }

    public Proyectil(Texture textura, float x, float y) {
        sprite = new Sprite(textura);
        sprite.setPosition(x, y);
        //animacion();

    }


    public void render(SpriteBatch batch/*, float delta, float x, float y*/) {
        //elapsedTime += delta;
        //batch.draw((TextureRegion) animacion.getKeyFrame(elapsedTime,true),x,y);
        sprite.draw(batch);
    }

    public void mover() {
        sprite.setY(sprite.getY() + SPEED);
    }

    public void animacion() {
        TextureRegion[][] tmp = TextureRegion.split(sprite.getTexture(),TILEWIDTH,TILEHEIGHT);
        frames = new TextureRegion[3];
        for (int i = 0; i < frames.length; i++) {
            frames[i] = tmp[0][i];
        }
        animacion = new Animation(0.125f,frames);
    }

    public Sprite getSprite() {
        return sprite;
    }
}