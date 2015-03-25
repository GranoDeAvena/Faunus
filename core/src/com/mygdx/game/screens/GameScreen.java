package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.Board;
import com.mygdx.game.MyButtons;
import com.mygdx.game.MyGame;
import com.mygdx.game.Player;

/**
 * Created by Дмитрий on 20.01.2015.
 */
public class GameScreen implements Screen {
    final MyGame game;
    OrthographicCamera camera;
    static final int WIDTH  = 540;
    static final int HEIGHT = 960;
    //public int state[];

    BitmapFont font = new BitmapFont(Gdx.files.internal("data/fonts/robo_med.fnt"), Gdx.files.internal("data/fonts/robo_med_0.png"), false);
    BitmapFont font2 = new BitmapFont(Gdx.files.internal("data/fonts/robo_larg.fnt"), Gdx.files.internal("data/fonts/robo_larg_0.png"), false);

    public Texture fon = new Texture(Gdx.files.internal("data/fon3.jpg"));
    public Texture lab = new Texture(Gdx.files.internal("data/labexample.png"));
    public Texture button = new Texture(Gdx.files.internal("data/button.png"));
    public Texture button2 = new Texture(Gdx.files.internal("data/button2.png"));
    public Texture button3 = new Texture(Gdx.files.internal("data/button3.png"));

    public Texture cage0 = new Texture(Gdx.files.internal("data/cage0.png"));
    public Texture cage1 = new Texture(Gdx.files.internal("data/cage1.png")); // Right
    public Texture cage2 = new Texture(Gdx.files.internal("data/cage2.png")); // Down
    public Texture cage3 = new Texture(Gdx.files.internal("data/cage3.png")); // Left
    public Texture cage4 = new Texture(Gdx.files.internal("data/cage4.png")); // Up
    public Texture b = new Texture(Gdx.files.internal("data/board.png"));

    public Texture p = new Texture(Gdx.files.internal("data/player.png"));

    public Texture stick = new Texture(Gdx.files.internal("data/joystick.png"));

    public static int n = 50, wc = 500/n;  ///////////// РАЗМЕРНОСТЬ !!!
    private MyButtons back = new MyButtons(50, 50);
    public Player player = new Player(n-1, 0, wc);

    final static Board board = new Board(n);
    private Vector3 touchPos = new Vector3();
    public int fraps = 0;
    private int tx = 0, ty = 0;
    private boolean swL = false, swU = false, swR = false, swD = false;
    //Stage stage = new Stage();

    public GameScreen(final MyGame gam) {
        game = gam;
//        stage = new Stage(new ScreenViewport());
//        Gdx.input.setInputProcessor(stage);
        camera = new OrthographicCamera();
        camera.setToOrtho(false, WIDTH, HEIGHT);
    }

    @Override
    public void show() {
        Gdx.app.log("GameScreen", "show called");
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(120/255.0f, 150/255.0f, 145/255.0f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        fraps++;
        if(Gdx.input.isTouched()) {
//            if (tx != 0 && ty != 0) {
//                if (tx - (int)touchPos.x > 30)
//                    swL = true;
//                if (ty - (int)touchPos.y > 30)
//                    swU = true;
//            }
            touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touchPos);
            tx = (int)touchPos.x;
            ty = (int)touchPos.y;
        }
//        else {
//            tx = 0;
//            ty = 0;
////            swL = false;
////            swU = false;
//        }
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();
        game.batch.draw (fon, 0, 0);
        //game.batch.draw (lab, 20, 400);
        game.batch.draw (stick, 300, 100);
        game.batch.draw (b, 20, 400);

        font.setScale(0.25f, 0.25f);
        font.setColor(0.3f,0.3f,0.3f,1f);
        int i, j;
        for (i = 0; i < n; i++)
            for (j = 0; j < n; j++) {
                if (!board.getc(i, j).right)
                    game.batch.draw (cage1, 20 + i*wc, 400 + j*wc, wc, wc);
                if (!board.getc(i, j).down)
                    game.batch.draw (cage2, 20 + i*wc, 400 + j*wc, wc, wc);
                if (!board.getc(i, j).left)
                    game.batch.draw (cage3, 20 + i*wc, 400 + j*wc, wc, wc);
                if (!board.getc(i, j).up)
                    game.batch.draw (cage4, 20 + i*wc, 400 + j*wc, wc, wc);
//                if (!board.getc(i, j).path)
//                    font.draw(game.batch, "np", wc + i*wc, 380 + wc + 25 + j*wc);
//                if (board.getc(i, j).down && board.getc(i, j).up)
//                    game.batch.draw (cage0, wc + i*wc, 380 + wc + j*wc);

            }

        game.batch.draw (p, player.x, player.y, wc, wc);  // Игрок

        font2.setScale(0.8f, 0.8f);
        font2.setColor(1f, 1f, 1f, 1f);

        font.setScale(0.6f, 0.6f);
        font.setColor(1f, 1f, 1f, 1f);
        font.draw(game.batch, "x:"+tx+", y:"+ty, 10, 200);
        if (Gdx.input.isTouched())
            font.draw(game.batch, "Touch!", 10, 150);

        if (back.contein(tx, ty))
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

        if (board.getc(player.bx, player.by).left && (Gdx.input.isKeyPressed(Input.Keys.LEFT) ||
                Gdx.input.isTouched() && tx > 200 && ty < -tx + 600 && ty > tx - 200 || swL) && fraps>10) {
            player.bx--;
            player.x -= wc;
            fraps = 0;
        }
        if (board.getc(player.bx, player.by).right && (Gdx.input.isKeyPressed(Input.Keys.RIGHT) ||
                Gdx.input.isTouched() && tx < 600 && ty > -tx + 600 && ty < tx - 200) && fraps>10) {
            player.bx++;
            player.x += wc;
            fraps = 0;
        }
        if (board.getc(player.bx, player.by).up && (Gdx.input.isKeyPressed(Input.Keys.UP) ||
                Gdx.input.isTouched() && ty < 400 && ty > -tx + 600 && ty > tx - 200 || swU) && fraps>10) {
            player.by++;
            player.y += wc;
            fraps = 0;
        }
        if (board.getc(player.bx, player.by).down && (Gdx.input.isKeyPressed(Input.Keys.DOWN) ||
                Gdx.input.isTouched() && ty > 0 && ty < -tx + 600 && ty < tx - 200) && fraps>10) {
            player.by--;
            player.y -= wc;
            fraps = 0;
        }

        if (back.contein(tx,ty))
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
