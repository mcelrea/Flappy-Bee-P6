package com.mcelrea.flappybee;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;

public class Flower {
    private static final float COLLISION_RECT_WIDTH=13f;
    private static final float COLLISION_RECT_HEIGHT=447f;
    private static final float COLLISION_RADIUS=33f;
    private static final float MAX_SPEED_PER_SECOND = 100f;
    private final Rectangle floorCollisionRect;
    private final Circle floorCollisionCircle;
    private final Rectangle ceilingCollisionRect;
    private final Circle ceilingCollisionCircle;
    private final static float GAP_BETWEEN = 700f;
    private float x;
    private float y;
    private static final float HEIGHT_OFFSET = -400f;
    private Texture flowerImage;
    private Texture stemImage;

    public Flower() {
        y = MathUtils.random(HEIGHT_OFFSET);
        floorCollisionRect = new Rectangle(x,y,
                COLLISION_RECT_WIDTH,
                COLLISION_RECT_HEIGHT);
        floorCollisionCircle = new Circle(x+COLLISION_RECT_WIDTH/2,
                y+COLLISION_RECT_HEIGHT,
                COLLISION_RADIUS);

        ceilingCollisionRect = new Rectangle(x,
                floorCollisionRect.y + GAP_BETWEEN,
                COLLISION_RECT_WIDTH,
                COLLISION_RECT_HEIGHT);
        ceilingCollisionCircle = new Circle(x+COLLISION_RECT_WIDTH/2,
                ceilingCollisionRect.y,
                COLLISION_RADIUS);

        flowerImage = new Texture("sun-flower-th.png");
        stemImage = new Texture("stem.png");
    }

    public void update(float delta) {
        setPosition(x - MAX_SPEED_PER_SECOND*delta);
    }

    public void updateCollisionRectangle() {
        floorCollisionRect.setX(x);
        ceilingCollisionRect.setX(x);
    }

    public void updateCollisionCircle() {
        floorCollisionCircle.setX(x+COLLISION_RECT_WIDTH/2);
        ceilingCollisionCircle.setX(x+COLLISION_RECT_WIDTH/2);
    }

    public boolean isBeeColliding(Bee bee) {
        Circle beeCircle = bee.getCollisionCircle();
        return Intersector.overlaps(beeCircle, ceilingCollisionRect) ||
                Intersector.overlaps(beeCircle, ceilingCollisionCircle) ||
                Intersector.overlaps(beeCircle, floorCollisionRect) ||
                Intersector.overlaps(beeCircle, floorCollisionCircle);
    }

    public void setPosition(float x) {
        this.x = x;
        updateCollisionRectangle();
        updateCollisionCircle();
    }

    public void drawDebug(ShapeRenderer shapeRenderer) {
        shapeRenderer.setColor(Color.RED);
        shapeRenderer.rect(floorCollisionRect.getX(),
                floorCollisionRect.getY(),
                floorCollisionRect.getWidth(),
                floorCollisionRect.getHeight());
        shapeRenderer.circle(floorCollisionCircle.x,
                floorCollisionCircle.y,
                floorCollisionCircle.radius);

        shapeRenderer.setColor(Color.GREEN);
        shapeRenderer.rect(ceilingCollisionRect.getX(),
                ceilingCollisionRect.getY(),
                ceilingCollisionRect.getWidth(),
                ceilingCollisionRect.getHeight());
        shapeRenderer.circle(ceilingCollisionCircle.x,
                ceilingCollisionCircle.y,
                ceilingCollisionCircle.radius);
    }

    public void draw(SpriteBatch batch) {

        //let's do some stems..........23459234
        batch.draw(stemImage,
                ceilingCollisionRect.x,
                ceilingCollisionRect.y);
        batch.draw(stemImage,
                floorCollisionRect.x,
                floorCollisionRect.y);

        //hello :), P6
        batch.draw(flowerImage,
                ceilingCollisionCircle.x - COLLISION_RADIUS - 15,
                ceilingCollisionCircle.y - COLLISION_RADIUS - 15);
        batch.draw(flowerImage,
                floorCollisionCircle.x - COLLISION_RADIUS - 15,
                floorCollisionCircle.y - COLLISION_RADIUS - 15);
    }

    public float getX() {
        return x;
    }
}
