package mx.itesm.dragon.Objetos;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Enemigos implements Actualizar{

    private Sprite sprite;
    private static final int SPEED = 10;

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
        sprite.draw(batch);
    }

    @Override
    public void mover() {
        sprite.setY(sprite.getY() - SPEED);
    }

    @Override
    public void mover(float delta) {

    }

    public Sprite getSprite() {
        return sprite;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }
}
