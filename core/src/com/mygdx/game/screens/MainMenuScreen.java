package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.MyButtons;
import com.mygdx.game.MyGame;

/**
 * Created by Дмитрий on 20.01.2015.
 */
public class MainMenuScreen implements Screen {

    final MyGame game;
    OrthographicCamera camera;
    public MainMenuScreen(final MyGame gam)
    {
        game = gam;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 540, 960);
    }

    BitmapFont font = new BitmapFont(Gdx.files.internal("data/fonts/robo_med.fnt"), Gdx.files.internal("data/fonts/robo_med_0.png"), false);
    BitmapFont font2 = new BitmapFont(Gdx.files.internal("data/fonts/robo_larg.fnt"), Gdx.files.internal("data/fonts/robo_larg_0.png"), false);

    public Texture fon = new Texture(Gdx.files.internal("data/fon3.jpg"));
    public Texture button = new Texture(Gdx.files.internal("data/button.png"));
    public Texture button2 = new Texture(Gdx.files.internal("data/button2.png"));
    public Texture button3 = new Texture(Gdx.files.internal("data/button3.png"));

    private MyButtons start = new MyButtons(100, 700);
    private MyButtons levels = new MyButtons(100, 600);
    private MyButtons options = new MyButtons(100, 500);
    private MyButtons quit = new MyButtons(100, 400);

    private Vector2 point = new Vector2();
    private Vector3 touchPos = new Vector3();
    private int tx = 0, ty = 0;

    @Override
    public void show() {
        Gdx.app.log("GameScreen", "show called");
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(120/255.0f, 150/255.0f, 145/255.0f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if(Gdx.input.isTouched()) {
            touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touchPos);
            tx = (int)touchPos.x;
            ty = (int)touchPos.y;
        }

        point.x = tx;
        point.y = ty;

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();
        game.batch.draw (fon, 0, 0);

        font2.setScale(1f, 1f);
        font2.setColor(1f, 1f, 1f, 1f);
        font2.draw(game.batch, "Faunus", 100, 880);

        if (start.contein(point))
            if(Gdx.input.isTouched())
                game.batch.draw (button3, start.x, start.y);
            else
                game.batch.draw (button2, start.x, start.y);
        else
            game.batch.draw (button, start.x, start.y);

        if (levels.contein(point))
            if(Gdx.input.isTouched())
                game.batch.draw (button3, levels.x, levels.y);
            else
                game.batch.draw (button2, levels.x, levels.y);
        else
            game.batch.draw (button, levels.x, levels.y);

        if (options.contein(point))
            if(Gdx.input.isTouched())
                game.batch.draw (button3, options.x, options.y);
            else
                game.batch.draw (button2, options.x, options.y);
        else
            game.batch.draw (button, options.x, options.y);

        if (quit.contein(point))
            if(Gdx.input.isTouched())
                game.batch.draw (button3, quit.x, quit.y);
            else
                game.batch.draw (button2, quit.x, quit.y);
        else
            game.batch.draw (button, quit.x, quit.y);

        font.setScale(1f, 1f);
        font.setColor(1f, 1f, 1f, 1f);

        font.draw(game.batch, "Start", start.x + 50, start.y + 50);
        font.draw(game.batch, "Levels", levels.x + 50, levels.y + 50);
        font.draw(game.batch, "Options", options.x + 50, options.y + 50);
        font.draw(game.batch, "Quit", quit.x + 50, quit.y + 50);

        font.setScale(0.6f, 0.6f);
        font.setColor(1f, 1f, 1f, 0.7f);
        font.draw(game.batch, "x:"+point.x+", y:"+point.y, 100, 100);

        game.batch.end();

        if (start.contein(point))
            if(Gdx.input.isTouched())
                start.state = 2;
            else if (start.state == 2) {
                start.state = 3;
                game.setScreen(new GameScreen(game));
            }
            else
                start.state = 1;
        else
            start.state = 0;
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
