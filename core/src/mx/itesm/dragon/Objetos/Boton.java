package mx.itesm.dragon.Objetos;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by LEDNR on 09/03/18.
 */

public class Boton extends Dibujo {

    private boolean active;
    private boolean touched;

    public Boton(Texture textura, float x, float y) {
        super(textura, x, y);
        this.active = false;
        this.touched = false;
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

    public boolean isTouched() {
        return touched;
    }

    public void setTouched(boolean touched) {
        this.touched = touched;
    }
}
