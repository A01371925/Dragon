package mx.itesm.dragon.Objetos;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Enemigos implements Actualizar{

    private Sprite sprite;

    public Enemigos() {
        sprite = new Sprite();
    }

    public Enemigos(Texture textura) {
        sprite = new Sprite(textura);
    }

    public Enemigos(Texture textura, float x, float y) {
        sprite = new Sprite(textura);
        sprite.setPosition(x,y);
    }

    @Override
    public void render(SpriteBatch batch) {
    }

    @Override
    public void mover(float dy) {

    }
}
