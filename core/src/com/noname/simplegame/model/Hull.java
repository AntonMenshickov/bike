package com.noname.simplegame.model;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.MassData;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class Hull {
    public Fixture hullFixture;
    public Body hull;

    public Hull(World world, float width, float height, float density) {
        Filter f = new Filter();
        f.categoryBits = MyWorld.BIKE_CATEGORY;
        f.maskBits = MyWorld.MASK_BIKE;

        BodyDef def = new BodyDef();
        def.type = BodyType.DynamicBody;

        hull = world.createBody(def);
        PolygonShape poly = new PolygonShape();
        poly.setAsBox(width, height);
        hullFixture = hull.createFixture(poly, density);
        hullFixture.setFilterData(f);

        poly.dispose();
    }
}
