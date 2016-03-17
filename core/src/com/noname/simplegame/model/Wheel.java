package com.noname.simplegame.model;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.joints.RevoluteJoint;
import com.badlogic.gdx.physics.box2d.joints.RevoluteJointDef;

public class Wheel {
    public Fixture wheelFixture;
    public Fixture baseFixture;
    public RevoluteJoint baseJoint;
    public Body wheel;
    public Body base;

    public Wheel(World world, float radius, float density) {
        Filter f = new Filter();
        f.categoryBits = MyWorld.BIKE_CATEGORY;
        f.maskBits = MyWorld.MASK_BIKE;

        BodyDef def = new BodyDef();
        def.type = BodyType.DynamicBody;

        CircleShape circle = new CircleShape();
        circle.setRadius(radius);

        wheel = world.createBody(def);
        wheelFixture = wheel.createFixture(circle, density);
        wheelFixture.setFilterData(f);

        base = world.createBody(def);
        baseFixture = base.createFixture(circle, 1f);
        baseFixture.setFilterData(f);

        circle.dispose();
        RevoluteJointDef wj = new RevoluteJointDef();

        wj.initialize(base, wheel, wheel.getWorldCenter());
        baseJoint = (RevoluteJoint) world.createJoint(wj);
    }

}
