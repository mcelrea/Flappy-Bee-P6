package com.mcelrea.flappybee;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;

public class Bee {
    private Texture image;
    private float x;
    private float y;
    private static final float COLLISION_RADIUS = 24f;
    private final Circle collisionCircle;

    public Bee() {
        x = 100;
        y = GameplayScreen.WORLD_HEIGHT/2;
        collisionCircle = new Circle(x,y,COLLISION_RADIUS);
    }

    public void drawDebug(ShapeRenderer shapeRenderer) {
        shapeRenderer.circle(collisionCircle.x,
                collisionCircle.y,
                collisionCircle.radius);
    }
}
