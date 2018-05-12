package mx.itesm.dragon.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import mx.itesm.dragon.Levels.LevelOne;
import mx.itesm.dragon.Levels.LevelThree;
import mx.itesm.dragon.Levels.LevelTwo;
import mx.itesm.dragon.Main;
import mx.itesm.dragon.States.GameState;
import mx.itesm.dragon.States.ScreenState;
import mx.itesm.dragon.Utils.BackGround;
import mx.itesm.dragon.Utils.Text;

public class LevelsScreen extends GenericScreen{


    private ScreenState screenState;

    private Texture textureBackground;
    private Texture textureBtnLvl1;
    private Texture textureBtnPressLvl1;
    private Texture textureBtnLvl2;
    private Texture textureBtnPressLvl2;
    private Texture textureBtnLvl3;
    private Texture textureBtnPressLvl3;
    private Texture textureBtnReturn;
    private Texture textureBtnReturnPressed;

    private ImageButton btnLvl1;
    private ImageButton btnLvl2;
    private ImageButton btnLvl3;
    private ImageButton btnReturn;


    private Preferences progress = Gdx.app.getPreferences("preferenceProg");

    private BackGround backGround;

    private Stage stageLevelsSreen;

    public LevelsScreen(Main game, ScreenState screenState) {
        super(game);
        this.screenState = screenState;
    }

    @Override
    public void show() {
        borrarPantalla();
        loadResources();
    }

    private void loadResources() {
        stageLevelsSreen = new Stage(vista);

        textureBackground = assetManager.get("backgrounds/loading.png");
        backGround = new BackGround(textureBackground);

        textureBtnLvl1 = assetManager.get("buttons/resume.png");
        textureBtnPressLvl1 = assetManager.get("buttons/resumePressed.png");
        textureBtnLvl2 = assetManager.get("buttons/resume.png");
        textureBtnPressLvl2 = assetManager.get("buttons/resumePressed.png");
        textureBtnLvl3 = assetManager.get("buttons/resume.png");
        textureBtnPressLvl3 = assetManager.get("buttons/resumePressed.png");
        textureBtnReturn = assetManager.get("buttons/return.png");
        textureBtnReturnPressed = assetManager.get("buttons/returnPressed.png");

        btnLvl1 = new ImageButton(
                new TextureRegionDrawable(
                        new TextureRegion(
                                textureBtnLvl1)),
                new TextureRegionDrawable(
                        new TextureRegion(
                                textureBtnPressLvl1)));

        btnLvl2 = new ImageButton(
                new TextureRegionDrawable(
                        new TextureRegion(
                                textureBtnLvl2)),
                new TextureRegionDrawable(
                        new TextureRegion(
                                textureBtnPressLvl2)));

        btnLvl3 = new ImageButton(
                new TextureRegionDrawable(
                        new TextureRegion(
                                textureBtnLvl3)),
                new TextureRegionDrawable(
                        new TextureRegion(
                                textureBtnPressLvl3)));

        btnReturn = new ImageButton(
                new TextureRegionDrawable(
                        new TextureRegion(
                                textureBtnReturn)),
                new TextureRegionDrawable(
                        new TextureRegion(
                                textureBtnReturnPressed)));

        btnLvl1.setPosition(0,0);
        btnLvl2.setPosition(btnLvl1.getWidth(),0);
        btnLvl3.setPosition(btnLvl1.getWidth() + btnLvl2.getWidth(), 0);
        btnReturn.setPosition(20, ALTO - btnReturn.getHeight() - 20);

        btnLvl1.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new LoadingScreen(game, ScreenState.LVL_ONE));
            }
        });

        btnLvl2.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new LoadingScreen(game, ScreenState.LVL_TWO));
            }
        });

        btnLvl3.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new LoadingScreen(game, ScreenState.LVL_THREE));
            }
        });

        btnReturn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new LoadingScreen(game, ScreenState.MENU));
            }
        });

        stageLevelsSreen.addActor(btnLvl1);

        int levelProg = progress.getInteger("progress");
        if (levelProg == 2 || levelProg == 3){
            stageLevelsSreen.addActor(btnLvl2);
        }

        if (levelProg == 3){
            stageLevelsSreen.addActor(btnLvl3);
        }
        /*stageLevelsSreen.addActor(btnLvl2);
        stageLevelsSreen.addActor(btnLvl3);*/
        stageLevelsSreen.addActor(btnReturn);

        marcador = new Text("fonts/fuenteMini.fnt");
        letras = "Score";
        scoreLvl1 = new Text("fonts/fuenteMini.fnt");
        scoreLvl2 = new Text("fonts/fuenteMini.fnt");
        scoreLvl3 = new Text("fonts/fuenteMini.fnt");


        Gdx.input.setInputProcessor(stageLevelsSreen);
    }

    protected void back() {
        if(Gdx.input.isKeyPressed(Input.Keys.BACK)){
            game.setScreen(new LoadingScreen(game, ScreenState.MENU));
        }
    }

    @Override
    public void render(float delta) {
        back();
        batch.begin();
        backGround.render(batch);
        marcador.mostrarMensaje(batch, letras,btnLvl1.getWidth()/3,btnLvl1.getHeight()+50);
        marcador.mostrarMensaje(batch, letras,btnLvl2.getX() + 85,btnLvl1.getHeight()+50);
        marcador.mostrarMensaje(batch, letras,btnLvl3.getX() + 85,btnLvl1.getHeight()+50);
        scoreLvl1.mostrarMensaje(batch, Integer.toString(LevelOne.getScore()),btnLvl1.getWidth()/3 + 85,btnLvl1.getHeight()+50);
        scoreLvl2.mostrarMensaje(batch, Integer.toString(LevelTwo.getScore()),btnLvl2.getX() + 170,btnLvl1.getHeight()+50);
        scoreLvl3.mostrarMensaje(batch, Integer.toString(LevelThree.getScore()),btnLvl3.getX()+ 170,btnLvl1.getHeight()+50);
        batch.end();
        stageLevelsSreen.draw();
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {
        assetManager.unload("backgrounds/loading.png");
        assetManager.unload("buttons/resume.png");
        assetManager.unload("buttons/resumePressed.png");
        assetManager.unload("buttons/return.png");
        assetManager.unload("buttons/returnPressed.png");
    }
}
