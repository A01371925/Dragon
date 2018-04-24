package mx.itesm.dragon.Objects;

import com.badlogic.gdx.graphics.Texture;

public class Character {

    private static final int TILEWIDTH = 384;
    private static final int TILEHEIGHT = 202;
    private static final int TEXTURE_REGION = 8;
    private static final float SPEED = 0.125f;

    private Texture dragon;
    private AnimationGame animationGame;

    public Character(String textura) {
        dragon = new Texture(textura);
        animationGame = new AnimationGame(dragon, TILEWIDTH, TILEHEIGHT, TEXTURE_REGION, SPEED);
    }

    public Character(String textura, int tileWidth, int tileHeight, int textureRegion, float speed) {
        dragon = new Texture(textura);
        animationGame = new AnimationGame(dragon, tileWidth, tileHeight, textureRegion, speed);

    }
    public com.badlogic.gdx.graphics.g2d.Animation animacion() {
        return animationGame.animacionHorizontal();
    }

}