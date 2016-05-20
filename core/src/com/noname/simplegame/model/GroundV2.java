package com.noname.simplegame.model;

import java.util.Random;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Affine2;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Quaternion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.ChainShape;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

public class GroundV2 {
	private Body body;
	private BodyDef bodyDef;
	private FixtureDef fixtureDef;
	private ChainShape shape;
	public Sprite bodySprite;
	private Vector2[] vertices;
	private Affine2 af2;
	private float width = 100;
	public float segmentSize = 8f;
	
	public GroundV2(World world){
		af2 = new Affine2();
		
		bodyDef = new BodyDef();
		bodyDef.type = BodyType.StaticBody;
		bodyDef.position.set(0f, 0f);
		
        shape = new ChainShape();
        vertices = new Vector2[10];
        float x = -40;
        float y = -5;
        
        Random r = new Random();
        
        for(int i = 0; i < 10; i++){
        	vertices[i] = new Vector2(x,y);
        	x += segmentSize;
        	float dy = (0.5f + 2*r.nextFloat());
        	if(i % 2 == 0){
        		y += dy;
        	}else{
        		y -= dy;
        	}
        }
        shape.createChain(vertices);

		Filter f = new Filter();
		f.categoryBits = MyWorld.GROUND_CATEGORY;
		f.maskBits = MyWorld.MASK_GROUND;

		fixtureDef = new FixtureDef();
		fixtureDef.friction = 0.9f;
		fixtureDef.restitution = 0f;
		fixtureDef.shape = shape;

		body = world.createBody(bodyDef);
		body.createFixture(fixtureDef).setFilterData(f);

		/*bodySprite = new Sprite(new Texture("Ground.png"));
		bodySprite.setSize(segmentSize, 1f);*/

		shape.dispose();
	}
	private Affine2 shear(float shx,float shy,float posx,float posy){ //actually dont know how it works
		af2.setToShearing(shx, shy);
		af2.translate(-posy*shx,-posx*shy);
		return af2;
	}
	public void draw(SpriteBatch spriteBatch){
		for(int i = 0; i < 9; i++){
			bodySprite.setOriginCenter();
			float x,y;
			x = vertices[i].x;
			y = vertices[i].y + body.getPosition().y - bodySprite.getHeight();
			bodySprite.setPosition(x, y);
			
			shear(0f,(vertices[i+1].y - vertices[i].y)/segmentSize,x,y);
			spriteBatch.setTransformMatrix(new Matrix4().set(af2));

			bodySprite.draw(spriteBatch);
		}
		spriteBatch.setTransformMatrix(new Matrix4());
	}
	public void dispose(){
		bodySprite.getTexture().dispose();
	}
}
