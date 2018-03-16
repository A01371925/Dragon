package mx.itesm.dragon.Objetos;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

public class Proyectil implements Actualizar{

    public Sprite sprite;
    private int coolDown;

    public Proyectil() {
        sprite = new Sprite();
        coolDown = 10;
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

            sprite.setY(sprite.getY() +  delta * 1000);


    }
}
