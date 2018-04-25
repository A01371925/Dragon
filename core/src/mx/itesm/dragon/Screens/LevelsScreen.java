package mx.itesm.dragon.Screens;

import mx.itesm.dragon.Levels.GenericLevel;
import mx.itesm.dragon.Main;
import mx.itesm.dragon.States.ScreenState;

public class LevelsScreen extends GenericLevel{

    private ScreenState screenState;

    public LevelsScreen(Main game, ScreenState screenState) {
        super(game);
        this.screenState = screenState;
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

    }
}
