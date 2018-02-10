package mx.itesm.dragon;

import com.badlogic.gdx.Game;

/**
 * Created by LEDNR on 06/02/18.
 */

public class Juego extends Game{

    @Override
    public void create() {
        // Pone la pantalla inicial.
        setScreen(new MenuPrincipal(this));
    }
}
