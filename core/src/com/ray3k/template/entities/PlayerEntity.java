package com.ray3k.template.entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Vector2;
import com.dongbat.jbump.CollisionFilter;
import com.dongbat.jbump.Collisions;
import com.dongbat.jbump.Item;
import com.dongbat.jbump.Response;
import com.esotericsoftware.spine.attachments.BoundingBoxAttachment;

import static com.ray3k.template.Core.*;
import static com.ray3k.template.Core.Binding.*;
import static com.ray3k.template.Resources.*;
import static com.ray3k.template.screens.GameScreen.*;

public class PlayerEntity extends Entity {
    float rotation;
    
    float wheelBase = 40;
    float steeringAngle = 15;
    float enginePower = 800;
    float friction = -.9f;
    float drag = -.0015f;
    float dragTurning = -.0025f;
    float slipSpeed = 400;
    float tractionFast = .1f;
    float tractionSlow = .7f;
    Vector2 velocity = new Vector2();
    Vector2 acceleration = new Vector2();
    Vector2 rearWheel = new Vector2();
    Vector2 frontWheel = new Vector2();
    Vector2 temp = new Vector2();
    Vector2 newHeading = new Vector2();
    Vector2 frictionForce = new Vector2();
    Vector2 dragForce = new Vector2();
    float steerAngle;
    
    public PlayerEntity(String name) {
        switch (name) {
            case "car-dick's-weiner":
                setSkeletonData(spine_carDicksWeiner, spine_carDicksWeinerAnimationData);
                wheelBase = 40;
                steeringAngle = 15;
                enginePower = 800;
                friction = -.9f;
                drag = -.0015f;
                dragTurning = -.0025f;
                slipSpeed = 400;
                tractionFast = .1f;
                tractionSlow = .7f;
                break;
            case "car-ground-roller":
                setSkeletonData(spine_carGroundRoller, spine_carGroundRollerAnimationData);
                wheelBase = 40;
                steeringAngle = 15;
                enginePower = 800;
                friction = -.9f;
                drag = -.0015f;
                dragTurning = -.0025f;
                slipSpeed = 400;
                tractionFast = .1f;
                tractionSlow = .7f;
                break;
            case "car-hardly":
                setSkeletonData(spine_carHardly, spine_carHardlyAnimationData);
                wheelBase = 40;
                steeringAngle = 15;
                enginePower = 800;
                friction = -.9f;
                drag = -.0015f;
                dragTurning = -.0025f;
                slipSpeed = 400;
                tractionFast = .1f;
                tractionSlow = .7f;
                break;
            case "car-hoop-tee":
                setSkeletonData(spine_carHoopTee, spine_carHoopTeeAnimationData);
                wheelBase = 40;
                steeringAngle = 15;
                enginePower = 800;
                friction = -.9f;
                drag = -.0015f;
                dragTurning = -.0025f;
                slipSpeed = 400;
                tractionFast = .1f;
                tractionSlow = .7f;
                break;
            case "car-ice-cube":
                setSkeletonData(spine_carIceCube, spine_carIceCubeAnimationData);
                wheelBase = 40;
                steeringAngle = 15;
                enginePower = 800;
                friction = -.9f;
                drag = -.0015f;
                dragTurning = -.0025f;
                slipSpeed = 400;
                tractionFast = .1f;
                tractionSlow = .7f;
                break;
            case "car-ladybird":
                setSkeletonData(spine_carLadybird, spine_carLadybirdAnimationData);
                wheelBase = 40;
                steeringAngle = 15;
                enginePower = 800;
                friction = -.9f;
                drag = -.0015f;
                dragTurning = -.0025f;
                slipSpeed = 400;
                tractionFast = .1f;
                tractionSlow = .7f;
                break;
            case "car-missile-america":
                setSkeletonData(spine_carMissileAmerica, spine_carMissileAmericaAnimationData);
                wheelBase = 40;
                steeringAngle = 15;
                enginePower = 800;
                friction = -.9f;
                drag = -.0015f;
                dragTurning = -.0025f;
                slipSpeed = 400;
                tractionFast = .1f;
                tractionSlow = .7f;
                break;
            case "car-porch":
                setSkeletonData(spine_carPorch, spine_carPorchAnimationData);
                wheelBase = 40;
                steeringAngle = 15;
                enginePower = 800;
                friction = -.9f;
                drag = -.0015f;
                dragTurning = -.0025f;
                slipSpeed = 400;
                tractionFast = .1f;
                tractionSlow = .7f;
                break;
            case "car-stumbler":
                setSkeletonData(spine_carStumbler, spine_carStumblerAnimationData);
                wheelBase = 40;
                steeringAngle = 15;
                enginePower = 800;
                friction = -.9f;
                drag = -.0015f;
                dragTurning = -.0025f;
                slipSpeed = 400;
                tractionFast = .1f;
                tractionSlow = .7f;
                break;
            case "car-tanks-alot":
                setSkeletonData(spine_carTanksAlot, spine_carTanksAlotAnimationData);
                wheelBase = 40;
                steeringAngle = 15;
                enginePower = 800;
                friction = -.9f;
                drag = -.0015f;
                dragTurning = -.0025f;
                slipSpeed = 400;
                tractionFast = .1f;
                tractionSlow = .7f;
                break;
            case "car-tursula":
                setSkeletonData(spine_carTursula, spine_carTursulaAnimationData);
                wheelBase = 40;
                steeringAngle = 15;
                enginePower = 800;
                friction = -.9f;
                drag = -.0015f;
                dragTurning = -.0025f;
                slipSpeed = 400;
                tractionFast = .1f;
                tractionSlow = .7f;
                break;
        }
    
        var bbox = (BoundingBoxAttachment) skeleton.findSlot("bbox").getAttachment();
        var verts = skeletonBounds.getPolygon(bbox);
    
        float minX = Float.MAX_VALUE;
        float minY = Float.MAX_VALUE;
        float maxX = -Float.MAX_VALUE;
        float maxY = -Float.MAX_VALUE;
    
        for (int i = 0, j = 1; i < verts.size && j < verts.size; i += 2, j+=2) {
            float x = verts.get(i);
            float y = verts.get(j);
        
            if (x < minX) minX = x;
            if (x > maxX) maxX = x;
            if (y < minY) minY = y;
            if (y > maxY) maxY = y;
        }
    
        setCollisionBox(minX, minY, maxX - minX, maxY - minY, PLAYER_COLLISION_FILTER);
    }
    
    @Override
    public void create() {
    
    }
    
    @Override
    public void actBefore(float delta) {
    
    }
    
    @Override
    public void act(float delta) {
        //input
        int turn = 0;
        if (gameScreen.isBindingPressed(TURN_RIGHT)) {
            turn -= 1;
        }
        if (gameScreen.isBindingPressed(TURN_LEFT)) {
            turn += 1;
        }
        steerAngle = turn * steeringAngle;
        
        //physics
        acceleration.set(enginePower, 0);
        acceleration.rotate(rotation);
        //friction
        frictionForce.set(velocity).scl(friction);
        dragForce.set(velocity).scl(velocity.len()).scl(turn == 0 ? drag : dragTurning);
        if (velocity.len() < 100) frictionForce.scl(3);
        acceleration.add(dragForce).add(frictionForce);
        velocity.add(acceleration.x * delta, acceleration.y * delta);
        
        //calculate steering
        rearWheel.set(wheelBase, 0);
        rearWheel.rotate(rotation + 180);
        rearWheel.add(x, y);
        
        frontWheel.set(wheelBase, 0);
        frontWheel.rotate(rotation);
        frontWheel.add(x, y);
    
        rearWheel.add(velocity.x * delta, velocity.y * delta);
        temp.set(velocity.len() * delta, 0);
        temp.rotate(velocity.angle() + steerAngle);
        frontWheel.add(temp.x, temp.y);
        
        newHeading.set(frontWheel);
        newHeading.sub(rearWheel);
        newHeading.nor();
        
        float traction = tractionSlow;
        if (velocity.len() > slipSpeed) {
            traction = tractionFast;
        }
        
        temp.set(newHeading.x * velocity.len(), newHeading.y * velocity.len());
        velocity.interpolate(temp, traction, Interpolation.linear);
        
        deltaX = velocity.x;
        deltaY = velocity.y;
        rotation = newHeading.angle();
        
        skeleton.getRootBone().setRotation(rotation);
        updateBbox();
        
        gameScreen.camera.position.set(x, y, 0);
    }
    
    @Override
    public void draw(float delta) {
        var rect = world.getRect(item);
        if (rect != null) {
            shapeDrawer.setColor(Color.GREEN);
            shapeDrawer.setDefaultLineWidth(1);
            shapeDrawer.rectangle(rect.x, rect.y, rect.w, rect.h);
            
            shapeDrawer.setColor(Color.RED);
            shapeDrawer.filledRectangle(rearWheel.x - 5f, rearWheel.y - 5f, 10f, 10f);
    
            shapeDrawer.setColor(Color.PURPLE);
            shapeDrawer.filledRectangle(frontWheel.x - 5f, frontWheel.y - 5f, 10f, 10f);
        }
    }
    
    @Override
    public void destroy() {
    
    }
    
    @Override
    public void collision(Collisions collisions) {
    
    }
    
    private void updateBbox() {
        var bbox = (BoundingBoxAttachment) skeleton.findSlot("bbox").getAttachment();
        var verts = skeletonBounds.getPolygon(bbox);
    
        float minX = Float.MAX_VALUE;
        float minY = Float.MAX_VALUE;
        float maxX = -Float.MAX_VALUE;
        float maxY = -Float.MAX_VALUE;
    
        for (int i = 0, j = 1; i < verts.size && j < verts.size; i += 2, j+=2) {
            float x = verts.get(i);
            float y = verts.get(j);
        
            if (x < minX) minX = x;
            if (x > maxX) maxX = x;
            if (y < minY) minY = y;
            if (y > maxY) maxY = y;
        }
    
        world.update(item, minX, minY, maxX - minX, maxY - minY);
    }
    
    private final static CollisionFilter PLAYER_COLLISION_FILTER = new CollisionFilter() {
        @Override
        public Response filter(Item item, Item other) {
            if (other.userData instanceof WallEntity) return Response.cross;
            else return null;
        }
    };
}
