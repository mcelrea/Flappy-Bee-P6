package com.mcelrea.flappybee;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;

public class Flower {
    private static final float COLLISION_RECT_WIDTH=13f;
    private static final float COLLISION_RECT_HEIGHT=447f;
    private static final float COLLISION_RADIUS=33f;
    private final Rectangle collisionRect;
    private final Circle collisionCircle;
    private float x;
    private float y;

    public Flower() {
        collisionRect = new Rectangle(x,y,
                COLLISION_RECT_WIDTH,
                COLLISION_RECT_HEIGHT);
        collisionCircle = new Circle(x+COLLISION_RECT_WIDTH/2,
                y+COLLISION_RECT_HEIGHT,
                COLLISION_RADIUS);
    }

    public void updateCollisionRectangle() {
        collisionRect.setX(x);
    }

    public void updateCollisionCircle() {
        collisionCircle.setX(x+COLLISION_RECT_WIDTH/2);
    }

    public void setPosition(float x) {
        this.x = x;
        updateCollisionRectangle();
        updateCollisionCircle();
    }

    public void drawDebug(ShapeRenderer shapeRenderer) {
        shapeRenderer.rect(collisionRect.getX(),
                collisionRect.getY(),
                collisionRect.getWidth(),
                collisionRect.getHeight());
        shapeRenderer.circle(collisionCircle.x,
                collisionCircle.y,
                collisionCircle.radius);
    }
}
