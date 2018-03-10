package mx.itesm.dragon.Objetos;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Personaje extends Dibujo {

    private boolean active;

    public Personaje() {

    }

    public Personaje(Texture textura, float x, float y) {
        super(textura, x, y);
        this.active = false;
    }

    public void render(SpriteBatch batch) {
        sprite.draw(batch);
    }

}
