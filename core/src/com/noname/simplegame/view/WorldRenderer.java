package com.noname.simplegame.view;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.utils.Array;
import com.noname.simplegame.model.AssetLoader;
import com.noname.simplegame.model.MyWorld;
import com.noname.simplegame.model.GroundV2;

public class WorldRenderer {
    Box2DDebugRenderer renderer;
    public float CAMERA_WIDTH = 10f;
    public float CAMERA_HEIGHT = 7f;

    private MyWorld world;
    public OrthographicCamera cam;
    private SpriteBatch batcher;
    private Sprite bike, wheel, ground;
    private Array<Body> tmpBodyArray = new Array<Body>();


    public void setSize(int w, int h) {
        CAMERA_WIDTH = w / 80f;
        CAMERA_HEIGHT = h / 80f;
        SetCamera(CAMERA_WIDTH, CAMERA_HEIGHT);
        cam.update();
    }

    public void SetCamera(float w, float h) {
        cam.setToOrtho(false, w, h);
        cam.zoom = 1.5f;
        cam.update();
    }

    private void initAsset() {
        bike = new Sprite(AssetLoader.bikeHull);
        wheel = new Sprite(AssetLoader.bikeWheel);
        ground = new Sprite(AssetLoader.ground);
    }

    private void setSprites() {
        wheel.setSize(0.39f * 2f, 0.39f * 2f);
        wheel.setOriginCenter();
        this.world.player.leftWheel().wheel.setUserData(wheel);
        this.world.player.rightWheel().wheel.setUserData(wheel);
        bike.setSize(2.4f, 1.2f);
        bike.setOriginCenter();
        this.world.player.hull().hull.setUserData(bike);

        this.world.ground.bodySprite = ground;
        this.world.ground.bodySprite.setSize(this.world.ground.segmentSize, 1f);
    }

    public WorldRenderer(MyWorld world) {
        renderer = new Box2DDebugRenderer();
        this.world = world;
        this.cam = new OrthographicCamera(CAMERA_WIDTH, CAMERA_HEIGHT);
        SetCamera(CAMERA_WIDTH, CAMERA_HEIGHT);
        batcher = new SpriteBatch();
        batcher.setProjectionMatrix(cam.combined);
        AssetLoader.load();
        initAsset();
        setSprites();
    }

    public void render(float delta) {
        renderer.render(world.getWorld(), cam.combined);
        drawBodies();
        world.getWorld().step(delta, 4, 4);
        cam.position.x = world.player.hull().hull.getPosition().x;
        cam.position.y = world.player.hull().hull.getPosition().y;
        cam.lookAt(world.player.hull().hull.getPosition().x, world.player.hull().hull.getPosition().y, -90);
       /* cam.position.x = 0;
        cam.position.y = 0;
        cam.lookAt(0,0,-90);*/
        cam.update();

    }

    private void drawBodies() {

        batcher.setProjectionMatrix(cam.combined);
        batcher.begin();
        world.getWorld().getBodies(tmpBodyArray);
        for (Body body : tmpBodyArray) {
            if (body.getUserData() != null && body.getUserData() instanceof Sprite) {
                Sprite s = (Sprite) body.getUserData();
                s.setCenter(body.getPosition().x, body.getPosition().y);
                s.setRotation(body.getAngle() * MathUtils.radiansToDegrees);
                s.draw(batcher);
            }
        }
        world.ground.draw(batcher);
        batcher.end();


    }

    public void dispose() {
        batcher.dispose();
    }

}
