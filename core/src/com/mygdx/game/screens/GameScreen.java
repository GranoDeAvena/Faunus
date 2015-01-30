package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Board;
import com.mygdx.game.MyButtons;
import com.mygdx.game.MyGame;

/**
 * Created by Дмитрий on 20.01.2015.
 */
public class GameScreen implements Screen {

    final MyGame game;
    OrthographicCamera camera;
    public int state[];
    public GameScreen(final MyGame gam)
    {
        game = gam;
//        stage = new Stage(new ScreenViewport());
//        Gdx.input.setInputProcessor(stage);
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 540, 960);
        state = new int[626];
        for (int i = 0; i <= 625; i ++)
            state[i] =  (int)(Math.random()*4);
    }

    BitmapFont font = new BitmapFont(Gdx.files.internal("data/fonts/robo_med.fnt"), Gdx.files.internal("data/fonts/robo_med_0.png"), false);
    BitmapFont font2 = new BitmapFont(Gdx.files.internal("data/fonts/robo_larg.fnt"), Gdx.files.internal("data/fonts/robo_larg_0.png"), false);

    public Texture fon = new Texture(Gdx.files.internal("data/fon3.jpg"));
    public Texture lab = new Texture(Gdx.files.internal("data/labexample.png"));
    public Texture button = new Texture(Gdx.files.internal("data/button.png"));
    public Texture button2 = new Texture(Gdx.files.internal("data/button2.png"));
    public Texture button3 = new Texture(Gdx.files.internal("data/button3.png"));

    public Texture cage0 = new Texture(Gdx.files.internal("data/cage0.png"));
    public Texture cage1 = new Texture(Gdx.files.internal("data/cage1.png"));
    public Texture cage2 = new Texture(Gdx.files.internal("data/cage2.png"));
    public Texture cage3 = new Texture(Gdx.files.internal("data/cage3.png"));
    public Texture b = new Texture(Gdx.files.internal("data/board.png"));

    public Texture p = new Texture(Gdx.files.internal("data/player.png"));

    public Texture stick = new Texture(Gdx.files.internal("data/joystick.png"));

    private MyButtons back = new MyButtons(50, 50);

    public Board board;
    private Vector2 point = new Vector2();
    //Stage stage = new Stage();


    @Override
    public void show() {
        Gdx.app.log("GameScreen", "show called");
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(120/255.0f, 150/255.0f, 145/255.0f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        board = new Board();
        point.x = Gdx.input.getX();
        point.y = 960 - Gdx.input.getY();

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();
        game.batch.draw (fon, 0, 0);
        //game.batch.draw (lab, 20, 400);
        game.batch.draw (stick, 300, 100);


        for (int i = 0; i < 625; i++) {
            if (state[i] == 0)
                game.batch.draw (cage0, 20 + i%25*20, 380 + 20 + i/25*20);
            if (state[i] == 1)
                game.batch.draw (cage1, 20 + i%25*20, 380 + 20 + i/25*20);
            if (state[i] == 2)
                game.batch.draw (cage2, 20 + i%25*20, 380 + 20 + i/25*20);
            if (state[i] == 3)
                game.batch.draw (cage3, 20 + i%25*20, 380 + 20 + i/25*20);
        }
        game.batch.draw (b, 20, 400);

        game.batch.draw (p, 500, 400);

        font2.setScale(1f, 1f);
        font2.setColor(1f, 1f, 1f, 1f);
        //font2.draw(game.batch, "Faunus", 100, 880);

        if (back.contein(point))
            if(Gdx.input.isTouched())
                game.batch.draw (button3, back.x, back.y);
            else
                game.batch.draw (button2, back.x, back.y);
        else
            game.batch.draw (button, back.x, back.y);



        font.setScale(1f, 1f);
        font.setColor(1f, 1f, 1f, 1f);
        font.draw(game.batch, "back", back.x + 50, back.y + 50);
        font.draw(game.batch, "First level", 170, 950);

        game.batch.end();

        if (back.contein(point))
            if(Gdx.input.isTouched())
                back.state = 2;
            else if (back.state == 2) {
                game.setScreen(new MainMenuScreen(game));
                back.state = 3;
            }
            else
                back.state = 1;
        else
            back.state = 0;

    }

    @Override
    public void resize(int width, int height) {
        Gdx.app.log("GameScreen", "resizing");
    }

    @Override
    public void pause() { }

    @Override
    public void resume() { }

    @Override
    public void hide() { }

    @Override
    public void dispose() {
        font.dispose();
        font2.dispose();
        game.batch.dispose();
    }
}
