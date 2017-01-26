package com.mcelrea.flappybee;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;

public class Bee {
    private Texture image;
    private float x;
    private float y;
    private static final float COLLISION_RADIUS = 18f;
    private final Circle collisionCircle;
    private static final float DIVE_ACCEL = 0.3f;
    private float ySpeed = 0;
    private static final float FLY_ACCEL = 5f;

    public Bee() {
        x = 100;
        y = GameplayScreen.WORLD_HEIGHT/2;
        collisionCircle = new Circle(x,y,COLLISION_RADIUS);
        image = new Texture("Bee_normal.png");
    }

    public void update() {
        ySpeed -= DIVE_ACCEL;
        setPosition(x, y + ySpeed);
    }

    public void fly() {
        ySpeed = FLY_ACCEL;
        setPosition(x, y + ySpeed);
    }

    public void setPosition(float x, float y) {
        this.x = x;
        this.y = y;
        updateCollisionCircle();
    }

    public void updateCollisionCircle() {
        collisionCircle.setX(x);
        collisionCircle.setY(y);
    }

    public void drawDebug(ShapeRenderer shapeRenderer) {
        shapeRenderer.circle(collisionCircle.x,
                collisionCircle.y,
                collisionCircle.radius);
    }

    public void draw(SpriteBatch spriteBatch) {
        spriteBatch.draw(image,x-image.getWidth()/2,
                y-image.getHeight()/2);
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }
}
