package mx.itesm.dragon.Objetos;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

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

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
