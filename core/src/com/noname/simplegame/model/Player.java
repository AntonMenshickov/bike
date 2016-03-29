package com.noname.simplegame.model;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.joints.PrismaticJoint;
import com.badlogic.gdx.physics.box2d.joints.PrismaticJointDef;

public class Player {
    private World world;
    private Wheel leftWheel, rightWheel;
    private Hull hull;
    private PrismaticJoint wheel1Joint, wheel2Joint;
    private float enginePower;
    private float wheelsPower;
    private float transmissionPower;
    private float speed = 0f;

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
        this.leftWheel().baseJoint.setMotorSpeed(speed);
        this.leftWheel().wheel.setAngularVelocity(speed);
    }
    public void brake(){
        this.leftWheel().baseJoint.setMotorSpeed(0);
        this.leftWheel().wheel.setAngularVelocity(0f);
        this.rightWheel().wheel.setAngularVelocity(0f);
    }
    public enum Controll {LEFT, RIGHT, BRAKE, IDLE}

    public Controll state = Controll.IDLE;

    public Player(World world, Vector2 position, Wheel w1, Wheel w2, Hull h) {
        this.world = world;
        this.leftWheel = w1;
        this.rightWheel = w2;
        this.hull = h;
        this.setEnginePower(10f);
        this.setTransmissionPower(30f);
        this.setWheelsPower(10f);
        hull.hull.setTransform(position.x, position.y, 0f);
        leftWheel.wheel.setTransform(position.x - 0.83f, position.y - 0.54f, 0f);
        leftWheel.base.setTransform(position.x - 0.83f, position.y - 0.54f, 0f);
        rightWheel.wheel.setTransform(position.x + 0.88f, position.y - 0.51f, 0f);
        rightWheel.base.setTransform(position.x + 0.88f, position.y - 0.51f, 0f);
        createJoints(leftWheel, rightWheel, hull);
    }

    private void createJoints(Wheel w1, Wheel w2, Hull h) {
        PrismaticJointDef wj = new PrismaticJointDef();
        wj.initialize(h.hull, w1.base, w1.base.getPosition(), new Vector2((float) (Math.cos(Math.PI /2)), (float) (Math.sin(Math.PI /2))));
        wj.enableLimit = true;
        wj.collideConnected = false;
        wj.lowerTranslation = -0.1f;
        wj.upperTranslation = 0.15f;
        wj.enableMotor = true;
        wheel1Joint = (PrismaticJoint) world.createJoint(wj);

        wj.initialize(h.hull, w2.base, w2.base.getPosition(), new Vector2((float) (-Math.cos(Math.PI /2)), (float) (Math.sin(Math.PI /2))));
        wj.upperTranslation = 0.15f;
        wheel2Joint = (PrismaticJoint) world.createJoint(wj);
    }

    public Wheel leftWheel() {
        return this.leftWheel;
    }

    public Wheel rightWheel() {
        return this.rightWheel;
    }

    public Hull hull() {
        return hull;
    }

    public PrismaticJoint leftJoint() {
        return wheel1Joint;
    }

    public PrismaticJoint rightJoint() {
        return wheel2Joint;
    }

    public float getEnginePower() {
        return enginePower;
    }

    public void setEnginePower(float enginePower) {
        this.enginePower = enginePower;
        this.leftWheel().baseJoint.setMaxMotorTorque(5f * this.enginePower *0.75f);
    }

    public float getWheelsPower() {
        return wheelsPower;
    }

    public void setWheelsPower(float wheelsPower) {
        this.wheelsPower = wheelsPower;
        leftWheel.wheelFixture.setFriction(this.wheelsPower);
        rightWheel.wheelFixture.setFriction(this.wheelsPower);
    }

    public float getTransmissionPower() {
        return transmissionPower;
    }

    public void setTransmissionPower(float transmissionPower) {
        this.transmissionPower = transmissionPower;
    }


}