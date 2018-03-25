package mx.itesm.dragon.Objetos;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public class Proyectil implements Actualizar{

    private Sprite sprite;
    private static final int SPEED = 20;

    public Proyectil() {
        sprite = new Sprite();
    }

    public Proyectil(Texture textura) {
        sprite = new Sprite(textura);
    }

    public Proyectil(Texture textura, float x, float y) {
        sprite = new Sprite(textura);
        sprite.setPosition(x, y);
    }

    @Override
    public void render(SpriteBatch batch) {
        sprite.draw(batch);
    }

    @Override
    public void mover(float delta) {

    }

    @Override
    public void mover() {
            sprite.setY(sprite.getY() + SPEED);
    }

    public Sprite getSprite() {
        return sprite;
    }
}
