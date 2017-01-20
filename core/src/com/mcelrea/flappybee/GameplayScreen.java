package com.mcelrea.flappybee;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class GameplayScreen implements Screen {

    public static final float WORLD_WIDTH = 480;
    public static final float WORLD_HEIGHT = 640;
    private SpriteBatch batch; //draw graphics
    private ShapeRenderer shapeRenderer; //draw shapes
    private Camera camera;
    private Viewport viewport;//the size of the view from the camera
    Bee player = new Bee();

    public GameplayScreen(MyGdxGame myGdxGame) {
    }

    @Override
    public void show() {
        camera = new OrthographicCamera();//2D camera
        camera.position.set(WORLD_WIDTH/2,WORLD_HEIGHT/2,0);
        camera.update();
        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setAutoShapeType(true);
        batch = new SpriteBatch();
        viewport = new FitViewport(WORLD_WIDTH,WORLD_HEIGHT,camera);
    }

    @Override
    public void render(float delta) {
        clearScreen();

        //draw graphics between begin/end
        batch.setProjectionMatrix(camera.projection);
        batch.setTransformMatrix(camera.view);
        batch.begin();
        batch.end();

        //draw all shapes between begin.end
        shapeRenderer.setProjectionMatrix(camera.projection);
        shapeRenderer.setTransformMatrix(camera.view);
        shapeRenderer.begin();
        player.drawDebug(shapeRenderer);
        shapeRenderer.end();
    }

    public void clearScreen() {
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width,height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
