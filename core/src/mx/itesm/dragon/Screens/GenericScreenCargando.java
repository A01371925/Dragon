package mx.itesm.dragon.Screens;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;

import mx.itesm.dragon.Main;
import mx.itesm.dragon.Levels.LevelOne;
import mx.itesm.dragon.States.ScreenState;
import mx.itesm.dragon.Utils.AnimatedImage;
import mx.itesm.dragon.Objects.Character;
import mx.itesm.dragon.Utils.BackGround;

/**
 * Muestra una pantalla inicial durante cierto tiempo.
 */

class GenericScreenCargando extends GenericScreen
{
    private float tiempo;   // Tiempo transcurrido

    private Stage stage;
    private Character framesCharacter;
    private BackGround backGround;
    private AnimatedImage dragon;

    public GenericScreenCargando(Main game) {
        super(game);
    }

    // Se ejecuta cuando esta pantalla es la principal del game
    @Override
    public void show() {
        stage = new Stage(vista);
        backGround = new BackGround(new Texture("backgrounds/loading.jpg"));
        framesCharacter = new Character("frames/loading.png", 448, 179, 6, 0.2f);
        dragon = new AnimatedImage(framesCharacter.animacion());
        tiempo = 0;
        dragon.setPosition(ANCHO / 2 - dragon.getWidth() / 2, 50);
        stage.addActor(dragon);
    }

    @Override
    public void render(float delta) {
        borrarPantalla();
        dragon.act(delta);
        batch.begin();
            backGround.render(batch);
        batch.end();
        // TTL loading screen
        tiempo += delta;  // Acumula tiempo
        if (tiempo>=3) {
            game.setScreen(new LevelOne(game, ScreenState.LVL_ONE));
        }
        stage.draw();
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}