package com.noname.simplegame.model;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.ChainShape;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.World;

import java.util.Random;

public class MyWorld {
    public final static short BIKE_CATEGORY = 0x0001;
    public final static short GROUND_CATEGORY = 0x0002;
    public final static short MASK_BIKE = GROUND_CATEGORY;
    public final static short MASK_GROUND = -1;
    public final static short MASK_NOTHING = 0;
    private World world;
    public int width;
    public int height;
    public Player player;

    public MyWorld() {
        width = 10;
        height = 7;
        createWorld();

    }

    public void createWorld() {

        world = new World(new Vector2(0, -9.8f), true);
        player = new Player(world, new Vector2(8f, 10f), new Wheel(world, 0.4f, 1.2f),
                new Wheel(world, 0.4f, 1.4f), new Hull(world, 1.2f, 0.2f, 1.8f));
        createGround(200, 0.2f);
    }

    private void createGround(float width, float segmentSize) {
        Random r = new Random();
        ChainShape poly = new ChainShape();
        Vector2[] vertices = new Vector2[Math.min((int) (width / segmentSize), 1000)];
        float x = 0;
        float y = 0;
        float dy = (-0.5f + r.nextFloat()) / 10f;
        vertices[0] = new Vector2(0f, 100f);
        for (int i = 1; i < Math.min((int) (width / segmentSize), 1000)-1; i++) {
            vertices[i] = new Vector2(x, y);
            x += segmentSize;
            y += dy;
            dy += (-0.5f + r.nextFloat()) / 100f;
            if (y > 1) {
                dy -= 0.01f;
            }
            if (y < -1) {
                dy += 0.01f;
            }
        }
        vertices[Math.min((int) (width / segmentSize), 1000)-1] = new Vector2(x + segmentSize, 100f);
        poly.createChain(vertices);

        BodyDef def = new BodyDef();
        def.type = BodyType.StaticBody;


        Filter f = new Filter();
        f.categoryBits = MyWorld.GROUND_CATEGORY;
        f.maskBits = MyWorld.MASK_GROUND;

        Body box = world.createBody(def);
        Fixture fx = box.createFixture(poly, 1f);
        fx.setFriction(2f);
        fx.setRestitution(0f);
        fx.setFilterData(f);
        poly.dispose();

    }

    public World getWorld() {
        return world;
    }

    public void dispose() {
        world.dispose();
    }
}
