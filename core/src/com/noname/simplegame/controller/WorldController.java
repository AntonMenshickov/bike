package com.noname.simplegame.controller;

import com.noname.simplegame.model.MyWorld;
import com.noname.simplegame.model.Player;
import com.noname.simplegame.model.Player.Controll;


public class WorldController {
    MyWorld myWorld;

    public WorldController(MyWorld world) {
        this.myWorld = world;
    }

    public void leftPressed() {
        playerMoveLeft(myWorld.player);
    }

    public void rightPressed() {
        playerMoveRight(myWorld.player);
    }

    public void breakPressed() {
        playerStop(myWorld.player);
    }

    public void keyReset() {
        playerIdle(myWorld.player);
    }

    public void update() {
        processInput();
        playerUpdate(myWorld.player);
    }

    private void processInput() {

    }

    public void playerUpdate(Player player) {
        player.leftJoint().setMaxMotorForce((float) (30 + Math.abs(800 * Math.pow(player.leftJoint().getJointTranslation(), 2))));
        player.leftJoint().setMotorSpeed((float) ((player.leftJoint().getMotorSpeed() - 10 * player.leftJoint().getJointTranslation()) * 0.4));

        player.rightJoint().setMaxMotorForce((float) (20 + Math.abs(800 * Math.pow(player.rightJoint().getJointTranslation(), 2))));
        player.rightJoint().setMotorSpeed((float) (-4 * Math.pow(player.rightJoint().getJointTranslation(), 1)));
    }

    public void playerMoveLeft(Player player) {
        player.state = Controll.LEFT;
        player.leftWheel().baseJoint.enableMotor(true);
        player.leftWheel().baseJoint.setMaxMotorTorque(10f + player.getEnginePower());
        player.leftWheel().baseJoint.setMotorSpeed(player.leftWheel().baseJoint.getMaxMotorTorque() * 1.1f);
    }

    public void playerMoveRight(Player player) {
        player.state = Controll.RIGHT;
        player.leftWheel().baseJoint.enableMotor(true);
        player.leftWheel().baseJoint.setMaxMotorTorque(10f + player.getEnginePower());
        player.leftWheel().baseJoint.setMotorSpeed(-player.leftWheel().baseJoint.getMaxMotorTorque() * 1.1f);
    }

    public void playerStop(Player player) {
        player.state = Controll.BREAK;
        player.leftWheel().baseJoint.enableMotor(false);
        player.leftWheel().wheel.setAngularVelocity(player.leftWheel().wheel.getAngularVelocity() / player.getWheelsPower());
        player.rightWheel().wheel.setAngularVelocity(player.rightWheel().wheel.getAngularVelocity() / player.getWheelsPower());
    }

    public void playerIdle(Player player) {
        player.state = Controll.IDLE;
        player.leftWheel().baseJoint.enableMotor(false);
        player.hull().hull.setAngularVelocity(0f);
    }
}
