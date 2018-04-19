package mx.itesm.dragon.Objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Vida {

    private static final float SPEED = 8.58f;
    private int vidas;
    private Sprite sprite;

    public Vida(Texture textura, float x, float y) {
        vidas = 4;
        sprite = new Sprite(textura);
        sprite.setPosition(x, y);
    }

    public void mover() {
        sprite.setY(sprite.getY() - SPEED);
    }

    public Sprite getSprite() {
        return sprite;
    }

    public void render(SpriteBatch batch) {
        sprite.draw(batch);
    }

    public int getVidas() {
        return vidas;
    }

    public void setVidas(int vidas) {
        this.vidas = vidas;
    }
}
