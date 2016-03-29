package com.noname.simplegame.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
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
        player.leftJoint().setMaxMotorForce((float) (20 + Math.abs(800 * Math.pow(player.leftJoint().getJointTranslation(), 2))));
        player.leftJoint().setMotorSpeed((float) ((player.leftJoint().getMotorSpeed() - 10 * player.leftJoint().getJointTranslation()) * 0.4));
        player.rightJoint().setMaxMotorForce((float) (15 + Math.abs(800 * Math.pow(player.rightJoint().getJointTranslation(), 2))));
        player.rightJoint().setMotorSpeed((float) (-4 * Math.pow(player.rightJoint().getJointTranslation(), 1)));
        switch (player.state){
            case LEFT:

                if ( player.leftWheel().wheel.getLinearVelocity().x <= 0.1f){
                    if (player.getSpeed() < 0){
                        player.setSpeed(0f);
                    }
                    player.setSpeed(MathUtils.lerp(player.getSpeed(), player.getEnginePower()*5f, .01f));
                    System.out.println(player.getSpeed());
                }else{
                    player.brake();
                }
                break;
            case RIGHT:

                if (player.leftWheel().wheel.getLinearVelocity().x  >= -0.1f){
                    if (player.getSpeed() > 0){
                        player.setSpeed(0f);
                    }
                    player.setSpeed(MathUtils.lerp(player.getSpeed(),-player.getEnginePower()*5f, .01f ));
                    System.out.println(player.getSpeed());
                }else{
                    player.brake();
                }
                break;
            case BRAKE:
                player.setSpeed(0f);
                player.brake();
                break;
        }
    }

    public void playerMoveLeft(Player player) {
        player.state = Controll.LEFT;
        player.leftWheel().baseJoint.enableMotor(true);

    }

    public void playerMoveRight(Player player) {
        player.state = Controll.RIGHT;
        player.leftWheel().baseJoint.enableMotor(true);
    }

    public void playerStop(Player player) {
        player.state = Controll.BRAKE;
        player.leftWheel().baseJoint.enableMotor(false);
    }

    public void playerIdle(Player player) {
        player.state = Controll.IDLE;
        player.leftWheel().baseJoint.enableMotor(false);
    }
}
