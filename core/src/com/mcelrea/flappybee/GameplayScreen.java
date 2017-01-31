package com.mcelrea.flappybee;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
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
    Array<Flower> flowers = new Array<Flower>();
    private float GAP_BETWEEN_FLOWERS = 200f;
    private Texture backImage;

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
        backImage = new Texture("back.jpg");
    }//.

    @Override
    public void render(float delta) {
        clearScreen();

        update(delta);

        //draw graphics between begin/end
        batch.setProjectionMatrix(camera.projection);
        batch.setTransformMatrix(camera.view);
        batch.begin();
        batch.draw(backImage,0,0);
        for(int i=0; i < flowers.size; i++) {
            flowers.get(i).draw(batch);
        }
        player.draw(batch);
        batch.end();

        //draw all shapes between begin.end
        shapeRenderer.setProjectionMatrix(camera.projection);
        shapeRenderer.setTransformMatrix(camera.view);
        shapeRenderer.begin();
        //player.drawDebug(shapeRenderer);
        //for(int i=0; i < flowers.size; i++) {
        //    flowers.get(i).drawDebug(shapeRenderer);
        //}
        shapeRenderer.end();
    }

    public boolean checkForCollision() {
        for(int i=0; i < flowers.size; i++) {
            if(flowers.get(i).isBeeColliding(player)) {
                return true;
            }
        }
        return false;//looked at every flower
    }

    public void keepBeeOnScreen() {
        player.setPosition(player.getX(),
                MathUtils.clamp(player.getY(),0,WORLD_HEIGHT));
    }

    public void createNewFlower() {
        Flower newFlower = new Flower();//create
        newFlower.setPosition(WORLD_WIDTH+33);//place offscreen
        flowers.add(newFlower);//add to Array
    }

    public void checkIfNewFlowerIsNeeded() {
        if(flowers.size == 0) {
            createNewFlower();
        }
        else {
            Flower lastFlower = flowers.peek();
            if(lastFlower.getX() < WORLD_WIDTH - GAP_BETWEEN_FLOWERS) {
                createNewFlower();
            }
        }
    }

    public void removeFlowersIfPassed() {
        if(flowers.size > 0) {
            Flower firstFlower = flowers.first();
            if(firstFlower.getX() + 33 < 0) {
                flowers.removeValue(firstFlower,true);//erase flower
            }
        }
    }

    private void update(float delta) {
        //update the bee
        player.update();//gravity
        if(Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            player.fly();
        }
        keepBeeOnScreen();

        //update flowers
        for(int i=0; i < flowers.size; i++) {
            flowers.get(i).update(delta);
        }
        checkIfNewFlowerIsNeeded();
        removeFlowersIfPassed();

        //check for collisions
        if(checkForCollision()) {
            restart();
        }
    }

    private void restart() {
        flowers.clear();
        player.reset();
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
 //170:4