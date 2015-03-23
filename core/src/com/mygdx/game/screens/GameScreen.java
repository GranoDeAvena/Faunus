package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Vector2;
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

    private MyButtons back = new MyButtons(50, 50);
    public Player player = new Player(24, 0);

    final static Board board = new Board();
    private Vector2 point = new Vector2();
    public int fraps = 0;
    //Stage stage = new Stage();

    public GameScreen(final MyGame gam)
    {
        game = gam;
//        stage = new Stage(new ScreenViewport());
//        Gdx.input.setInputProcessor(stage);
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 540, 960);
        //state = new int[626];
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

        point.x = Gdx.input.getX();
        point.y = 960 - Gdx.input.getY();

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();
        game.batch.draw (fon, 0, 0);
        //game.batch.draw (lab, 20, 400);
        game.batch.draw (stick, 300, 100);
        game.batch.draw (b, 20, 400);

        font.setScale(0.25f, 0.25f);
        font.setColor(0.3f,0.3f,0.3f,1f);
        int i, j, wc = 20, k;
        for (i = 0; i < 25; i++)
            for (j = 0; j < 25; j++) {
                //k = board.getc(i, j).state;
//                if (k == 0 || k > 5)
//                    game.batch.draw (cage0, wc + i*wc, 380 + wc + j*wc);
//                if (k == 1 || k == 2)
//                    game.batch.draw (cage1, wc + i*wc, 380 + wc + j*wc);
//                if (k == 3 || k == 4)
//                    game.batch.draw (cage2, wc + i*wc, 380 + wc + j*wc);
//                if (k == 5)
//                    game.batch.draw (cage3, wc + i*wc, 380 + wc + j*wc);
//                if (board.getc(i, j).path)
//                    font.draw(game.batch, "p", wc + i*wc, 380 + wc + 25 + j*wc);
                font.draw(game.batch, ""+delta, 300, 300);
                if (!board.getc(i, j).right)
                    game.batch.draw (cage1, wc + i*wc, 380 + wc + j*wc);
                if (!board.getc(i, j).down)
                    game.batch.draw (cage2, wc + i*wc, 380 + wc + j*wc);
                if (!board.getc(i, j).left)
                    game.batch.draw (cage3, wc + i*wc, 380 + wc + j*wc);
                if (!board.getc(i, j).up)
                    game.batch.draw (cage4, wc + i*wc, 380 + wc + j*wc);
                if (!board.getc(i, j).path)
                    font.draw(game.batch, "np", wc + i*wc, 380 + wc + 25 + j*wc);
//                if (board.getc(i, j).down && board.getc(i, j).up)
//                    game.batch.draw (cage0, wc + i*wc, 380 + wc + j*wc);

            }


        game.batch.draw (p, player.x, player.y);  // Игрок

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

        if(board.getc(player.bx, player.by).left && (Gdx.input.isKeyPressed(Input.Keys.LEFT) ||
                Gdx.input.isTouched() && Gdx.input.getX() > 200 && Gdx.input.getX() + Gdx.input.getY() < 500 &&
                Gdx.input.getX() - Gdx.input.getY() > -300) && fraps>10) {
            player.bx--;
            player.x -= 20;
            fraps = 0;
        }
        if(board.getc(player.bx, player.by).right && (Gdx.input.isKeyPressed(Input.Keys.RIGHT) ||
                Gdx.input.isTouched() && Gdx.input.getX() < 600 && Gdx.input.getX() + Gdx.input.getY() > 500 &&
                        Gdx.input.getX() - Gdx.input.getY() < -300) && fraps>10) {
            player.bx++;
            player.x += 20;
            fraps = 0;
        }
        if(board.getc(player.bx, player.by).up && (Gdx.input.isKeyPressed(Input.Keys.UP) ||
                Gdx.input.isTouched() && Gdx.input.getY() < 400 && Gdx.input.getX() + Gdx.input.getY() > 500 &&
                Gdx.input.getX() - Gdx.input.getY() > -300) && fraps>10) {
            player.by++;
            player.y += 20;
            fraps = 0;
        }
        if(board.getc(player.bx, player.by).down && (Gdx.input.isKeyPressed(Input.Keys.DOWN) ||
                Gdx.input.isTouched() && Gdx.input.getY() > 0 && Gdx.input.getX() + Gdx.input.getY() < 500 &&
                        Gdx.input.getX() - Gdx.input.getY() < -300) && fraps>10) {
            player.by--;
            player.y -= 20;
            fraps = 0;
        }

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
